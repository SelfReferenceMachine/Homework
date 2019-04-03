package com.lofter.nonalaberin_rosettastone.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class score extends AppCompatActivity {
    TextView score;
    TextView score2;

    protected void onCreat(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_score);
        score2 = (TextView)findViewById(R.id.score1);

    }

    public void btnOne1(View one1){
        showScore(1);
    }

    public void btnTwo1(View two1){
        showScore(2);
    }

    public void btnThree1(View three1){
        showScore(3);
    }

    private void showScore(int inc){
        String oldScore = (String)score2.getText();
        int newScore2 = Integer.parseInt(oldScore)+ inc;
        score2.setText("" + newScore2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        score = (TextView)findViewById(R.id.score);


        Button one = (Button)findViewById(R.id.one);
        Button two = (Button)findViewById(R.id.two);
        Button three = (Button)findViewById(R.id.three);
        Button reset = (Button)findViewById(R.id.reset);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b = score.getText().toString();
                Integer a = Integer.valueOf(b);
                Integer c = a+1;
                score.setText(String.format("%d",c));
            }

        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b = score.getText().toString();
                Integer a = Integer.valueOf(b);
                Integer c = a+2;
                score.setText(String.format("%d",c));
            }

        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b = score.getText().toString();
                Integer a = Integer.valueOf(b);
                Integer c = a+3;
                score.setText(String.format("%d",c));
            }

        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score2 = (TextView)findViewById(R.id.score1);
                score.setText(String.format("0"));
                score2.setText(String.format("0"));
            }

        });
    }


}
