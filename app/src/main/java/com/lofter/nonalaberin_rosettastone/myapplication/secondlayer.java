package com.lofter.nonalaberin_rosettastone.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class secondlayer extends AppCompatActivity implements View.OnCreateContextMenuListener, View.OnClickListener {
    TextView out;
    EditText inp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondlayer);

        out = findViewById(R.id.text);
        out.setText(R.string.btn_label);

        inp =findViewById(R.id.inp);
        String str=inp.getText().toString();

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        Log.i("main", "onClick msg...");
        try{
            String text = inp.getText().toString();
            float a = Float.valueOf(text);
            float f = (a * 9) / 5 + 32;
            out.setText("温度为：" + String.format("%.2f", f));
        }
        catch (Exception e){
            out.setText("请输入你要转化的摄氏度数值！");
        }
    }
}
