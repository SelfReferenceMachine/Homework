package com.lofter.nonalaberin_rosettastone.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class exchange extends AppCompatActivity implements Runnable{

    private final String TAG = "Rate";
    private float dollarRate = 0.1f;
    private float euroRate = 0.2f;
    private float yenRate = 16.5f;
    private String updateDate = "";

    EditText rmb;
    TextView result;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        rmb = (EditText) findViewById(R.id.rmb);
        result = (TextView) findViewById(R.id.showOut);

        //get saved data
        SharedPreferences sharedPreferences = getSharedPreferences("definedrate", Activity.MODE_PRIVATE);
        //也可用SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);，高版本可用，获得一个配置文件（保存关键数据信息，不建议多个配置文件），保存少量数据，放在其他文件内保存数据
        dollarRate = sharedPreferences.getFloat("Ndollar_rate", 0.0f);
        euroRate = sharedPreferences.getFloat("Neuro_rate", 0.0f);
        yenRate = sharedPreferences.getFloat("Nyen_rate", 0.0f);

        updateDate = sharedPreferences.getString("update_date","");

        //get time by new Date or by follwings:
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//low-case mm means Mins
        final String todayStr = sdf.format(today);


        Log.i(TAG, "onCreate: new dollarRate=" + dollarRate);
        Log.i(TAG, "onCreate: new euroRate=" + euroRate);
        Log.i(TAG, "onCreate: new yenRate=" + yenRate);
        Log.i(TAG, "onCreate: new updateDate" + updateDate);
        Log.i(TAG, "onCreate: new todaystr" + todayStr);
        //logcat输出程序日志，或者使用debug模式逐条调试。停在断点处，在窗口显示当前变量。Console旁边的第二个按钮逐行显示运行结果，第三个按钮跳入调用方法内，还有占用资源消耗的显示。
        //return();跳出循环。

        //check the date whether it is correct
        if(!todayStr.equals(updateDate)){
            Log.i(TAG,"onCreate: 需要更新");
            //开启子线程
            Thread t = new Thread(this);
            //this，调用当前对象的run方法
            t.start();
        }else{
            Log.i(TAG,"onCreate: 不需要更新");
        }


        handler = new Handler(){
            @Override
            public void handleMessage (Message msg){
                if(msg.what==5){
                    Bundle bd1 = (Bundle) msg.obj;
                    dollarRate = bd1.getFloat("dollar_rate");
                    euroRate = bd1.getFloat("euro_rate");
                    yenRate = bd1.getFloat("yen_rate");

                    Log.i(TAG, "handleMessage: dollar =" + dollarRate);
                    Log.i(TAG, "handleMessage: euro =" + euroRate);
                    Log.i(TAG, "handleMessage: yen =" + yenRate);

                    //save updated date
                    SharedPreferences sharedPreferences = getSharedPreferences("myrate",Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putFloat("dollar_rate",dollarRate);
                    editor.putFloat("euro_rate",euroRate);
                    editor.putFloat("yen_rate",yenRate);
                    editor.putString("update_date",todayStr);
                    editor.apply();

                    Toast.makeText(exchange.this,"汇率已更新",Toast.LENGTH_SHORT).show();
                 }
                 super.handleMessage(msg);
            }
        };
        //上为类方法重写
    }

    public void onClick(View btn) {
        String str = rmb.getText().toString();

        float r = 0;
        if (str.length() > 0) {
            r = Float.parseFloat(str);
        }else{
            Toast.makeText(this, "请输入金额", Toast.LENGTH_SHORT).show();
        }

        float val = 0;
        if (btn.getId() == R.id.Dollar) {
            //val = r * (1/6.7f);
            //result.setText(String.valueOf(val));
            result.setText(String.format("%.2f",r*dollarRate));
        } else if (btn.getId() == R.id.EURO) {
            //val = r * (1/7.5f);
            //result.setText(val + "");
            result.setText(String.format("%.2f",r*euroRate));
        } else if (btn.getId() == R.id.YEN) {
            //val = r * 16.5f;
            //result.setText(String.valueOf(val));
            result.setText(String.format("%.2f",r*yenRate));
        }
    }

    public void openOne(View btn) {
        openConfig();
    }

    private void openConfig() {
        Intent define = new Intent(this,UserDefine.class);
        define.putExtra("dollar_rate_ori",dollarRate);
        define.putExtra("euro_rate_ori",euroRate);
        define.putExtra("yen_rate_ori",yenRate);
        //可用bundle封装

        Log.i(TAG,"openOne:dollar_rate=" + dollarRate);
        Log.i(TAG,"openOne:euro_rate=" + euroRate);
        Log.i(TAG,"openOne:yen_rate=" + yenRate);
        //startActivity(define);
        startActivityForResult(define,1);

        //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:87092173");
        //startActivity(intent);
        //finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exchange,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.menu_set){
            openConfig();
        }else if(item.getItemId()== R.id.open_list){
        //打开列表
        Intent list = new Intent(this,MyList2Activity.class);
        startActivity(list);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int reqestCode, int resultCode, Intent data){
        //通过requestCode找到是哪个页面带回的数据，区分不同带回的数据resultCode，并知道如何拆分。测试后发现requestCode和resultCode可以一致。
        //run方法
        if(reqestCode==1 && resultCode==2){

            Bundle bundle = data.getExtras();
            dollarRate = bundle.getFloat("key_dollar",0.1f);
            euroRate = bundle.getFloat("key_euro",0.1f);
            yenRate = bundle.getFloat("key_yen",0.1f);

            Log.i(TAG,"onActivityResult:dollar_rate=" + dollarRate);
            Log.i(TAG,"onActivityResult:euro_rate=" + euroRate);
            Log.i(TAG,"onActivityResult:yen_rate=" + yenRate);

            //汇率写入
            SharedPreferences sharedPreferences = getSharedPreferences("myrate",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("dollar_rate",dollarRate);
            editor.putFloat("euro_rate",euroRate);
            editor.putFloat("yen_rate",yenRate);
            editor.commit();
            Log.i(TAG,"onActivityResult: data have saved to sharedPreference");
            //如何使数据来自已有的网络资源？不可在主线程中访问网络资源，由于为了提高系统响应速度，所以不允许占用更多主线程；所以要使用多线程，设置子线程读取数据。

        }

        super.onActivityResult(reqestCode,resultCode,data);
    }

    @Override
    public void run(){
        Log.i(TAG, "run: run()......");
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //延缓程序开启时间

            Bundle bundle = new Bundle();//save the rate we get



        //获取网络数据(Method 1)
        /*URL url = null;
        try {
            url = new URL("http://www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            InputStream in = http.getInputStream();

            String html = inputStream2String(in);
                    //匹配格式之后，获得输入流,之后解析文本
            Log.i(TAG,"run: html=" + html);
            Document doc = Jsoup.parse(html);

        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }*/

        //获取网络数据(Method 2)
        bundle = getFromBOC();
        //getFromBOC(bundle);
        //save rate into bundle

        //获取message对象用于返回数组
        Message msg = handler.obtainMessage(5);
        //取出消息队列，一个参数arg1，两个arg2；复杂如obj类型，所有对象父类；what标记当前message属性，方便接收信息比对
        //msg.what = 5;
        //整数型
        msg.obj = bundle;
        handler.sendMessage(msg);
        //将msg发送到安卓平台队列方便主函数中handler message处理

    }

    private Bundle getFromBOC() {
        Bundle bundle = new Bundle();
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.usd-cny.com/bankofchina.htm").get();
            Log.i(TAG, "run:" + doc.title());

            Elements tables = doc.getElementsByTag("table");
            /*for (Element table: tables){
                Log.i(TAG, "run: table["+i+"]=" + table);
                i++;
            }*/
            Element table6 = tables.get(0);
            //Log.i(TAG, "run: table6 =" + table6);
            //获取TDK中的数据
            Elements tds = table6.getElementsByTag("td");
            for (int i=0; i<tds.size(); i+=6){
                Element td1 = tds.get(i);
                Element td2 = tds.get(i+5);
                Log.i(TAG, "run:  " + td1.text() + "==>" + td2.text());
                String str1 = td1.text();
                String val = td2.text();

                if("美元".equals(str1)){
                    bundle.putFloat("dollar_rate", 100f/Float.parseFloat(val));
                }else if("欧元".equals(str1)){
                    bundle.putFloat("euro_rate", 100f/Float.parseFloat(val));
                }else if("日元".equals(str1)){
                    bundle.putFloat("yen_rate", 100f/Float.parseFloat(val));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bundle;
    }

    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "gb2312");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }
//获取数据；方向布局实现；生命周期（4.24
}
