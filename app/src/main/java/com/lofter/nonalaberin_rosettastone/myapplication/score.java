package com.lofter.nonalaberin_rosettastone.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class score extends AppCompatActivity {

    TextView show,showb;

    protected void onCreate(Bundle saveInstanceState){
       super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_score);
        show = findViewById(R.id.score);
        showb = findViewById(R.id.score2);
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        String scorea = ((TextView)findViewById(R.id.score)).getText().toString();
        String scoreb = ((TextView)findViewById(R.id.score2)).getText().toString();

        outState.putString("teama_score",scorea);
        outState.putString("teamb_score",scoreb);
    }
    //保留旋转时会丢失的数据

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String scorea = savedInstanceState.getString("teama_score");
        String scoreb = savedInstanceState.getString("teamb_score");

        ((TextView)findViewById(R.id.score)).setText(scorea);
        ((TextView)findViewById(R.id.score2)).setText(scoreb);
    }
    //还原保存的数据


    public void btn1(View v){
        if (v.getId()==R.id.btn1){
            show(1);
        }else if(v.getId()==R.id.btn1b){
            showb(1);
        }
    }

    public void btn2(View v){
        if (v.getId()==R.id.btn2){
            show(2);
        }else if(v.getId()==R.id.btn2b){
            showb(2);
        }
    }

    public void btn3(View v){
        if (v.getId()==R.id.btn3){
            show(3);
        }else if(v.getId()==R.id.btn3b){
            showb(3);
        }
    }

    public void btnReset(View v){
        TextView out = (TextView)findViewById(R.id.score);
        out.setText("0");
        ((TextView)findViewById(R.id.score2)).setText("0");
    }

    private void show(int inc){
        String oldScore = (String)show.getText();
        String newScore = String.valueOf(Integer.parseInt(oldScore)+ inc);
        show.setText(newScore);
    }

    private void showb(int inc){
        String oldScore = (String)showb.getText();
        String newScore = String.valueOf(Integer.parseInt(oldScore)+ inc);
        showb.setText(newScore);
    }


    /*@Override
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
    }*/


}
