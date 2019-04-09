package com.lofter.nonalaberin_rosettastone.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class exchange extends AppCompatActivity {
    EditText rmb;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        rmb = findViewById(R.id.rmb);
        result = findViewById(R.id.showOut);

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
            val = r * (1/6.7f);
            result.setText(String.valueOf(val));
        } else if (btn.getId() == R.id.EURO) {
            val = r * (1/7.5f);
            result.setText(val + "");
        } else if (btn.getId() == R.id.YEN) {
            val = r * 16.5f;
            result.setText(String.valueOf(val));
        }

    }
}
