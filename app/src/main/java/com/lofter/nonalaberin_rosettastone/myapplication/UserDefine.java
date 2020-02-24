package com.lofter.nonalaberin_rosettastone.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class UserDefine extends AppCompatActivity {
    public final String TAG ="UserDefine";

    EditText dollarT;
    EditText euroT;
    EditText yenT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_define);
        Intent original = getIntent();
        float dollarD = original.getFloatExtra("dollar_rate_ori",0.0f);
        float euroD = original.getFloatExtra("euro_rate_ori",0.0f);
        float yenD = original.getFloatExtra("yen_rate_ori",0.0f);

        Log.i(TAG,"onCreate:dollar_defined=" + dollarD);
        Log.i(TAG,"onCreate:euro_defined=" + euroD);
        Log.i(TAG,"onCreate:yen_defined=" + yenD);

        dollarT = (EditText)findViewById(R.id.dollar_ud);
        euroT = (EditText)findViewById(R.id.euro_ud);
        yenT = (EditText)findViewById(R.id.yen_ud);

        //传递参数到修改控件
        dollarT.setText(String.valueOf(dollarD));
        euroT.setText(String.valueOf(euroD));
        yenT.setText(String.valueOf(yenD));
    }


    public void save(View btn){
        Log.i(TAG,"save:");
        //获取修改值
        float newDollar = Float.parseFloat(dollarT.getText().toString());
        float newEuro = Float.parseFloat(euroT.getText().toString());
        float newYen = Float.parseFloat(yenT.getText().toString());

        Log.i(TAG,"获取值:");
        Log.i(TAG,"saved:dollar_rate=" + newDollar);
        Log.i(TAG,"saved:euro_rate=" + newEuro);
        Log.i(TAG,"saved:yen_rate=" + newYen);
        //保存
        Intent defined = getIntent();
        Bundle bdE = new Bundle();
        bdE.putFloat("key_dollar",newDollar);
        bdE.putFloat("key_euro",newEuro);
        bdE.putFloat("key_yen",newYen);
        defined.putExtras(bdE);
        setResult(2,defined);

        //返回到调用
        finish();
    }
}
