package com.moussa.a4get.feature;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Calculator extends AppCompatActivity {
    ArrayList<Button> btnNum;
    HashMap<String, Button> btnFx;
    static TextView tvResult = null;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        btnNum = new ArrayList<Button>();
        btnFx = new HashMap<String, Button>();
       tvResult = (TextView)findViewById(R.id.tvResult);
       findViewById(R.id.btnDel).setOnClickListener(new DoubleClickListener() {
           @Override
           public void onSingleClick(View v) {
               String str="";
               for(int i=0; i< tvResult.getText().length()-1; i++){
                   str = str + String.valueOf(tvResult.getText().charAt(i));
               }
               if(str.equals("")){
                   str="0";
               }
               tvResult.setText(str);
           }

           @Override
           public void onDoubleClick(View v) {
               tvResult.setText("0");
           }
       });
        /* _**** Adding num button to the arrayList ***_ */

        btnNum.add((Button)findViewById(R.id.btn0));
        btnNum.add((Button)findViewById(R.id.btn1));
        btnNum.add((Button)findViewById(R.id.btn2));
        btnNum.add((Button)findViewById(R.id.btn3));
        btnNum.add((Button)findViewById(R.id.btn4));
        btnNum.add((Button)findViewById(R.id.btn5));
        btnNum.add((Button)findViewById(R.id.btn6));
        btnNum.add((Button)findViewById(R.id.btn7));
        btnNum.add((Button)findViewById(R.id.btn8));
        btnNum.add((Button)findViewById(R.id.btn9));

        /* _**** Event onClick a num button***_ */

        for(int k=0; k < btnNum.size(); k++){
            final int p=k;
            btnNum.get(k).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (! tvResult.getText().toString().equals("0")){
                        if(tvResult.getText().charAt(tvResult.getText().length()-1) != '*' &&
                                tvResult.getText().charAt(tvResult.getText().length()-1) != '-' &&
                                tvResult.getText().charAt(tvResult.getText().length()-1) != '+'){
                            tvResult.setText(tvResult.getText().toString() + String.valueOf(p));
                        }else {
                            tvResult.setText(tvResult.getText().toString()+" "+String.valueOf(p));
                        }
                    }else{
                        tvResult.setText(String.valueOf(p));

                    }
                }
            });
        }

        /* _**** Adding function button ***_ */
        btnFx.put("dot",(Button)findViewById(R.id.btnDot));
        btnFx.put("del",(Button)findViewById(R.id.btnDel));
        btnFx.put("times",(Button)findViewById(R.id.btnTimes));
        btnFx.put("mines",(Button)findViewById(R.id.btnMines));
        btnFx.put("and",(Button)findViewById(R.id.btnAnd));
        btnFx.put("equal",(Button)findViewById(R.id.btnEqual));

        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! tvResult.getText().toString().equals("")&&
                    tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '*' &&
                    tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '-' &&
                    tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '.' &&
                    tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '+'){
                    tvResult.setText(tvResult.getText().toString()+String.valueOf('.'));
                }
            }
        });

        findViewById(R.id.btnTimes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! tvResult.getText().toString().equals("") &&
                    tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '*' &&
                    tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '-' &&
                    tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '.' &&
                    tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '+'){

                    tvResult.setText(tvResult.getText().toString()+' '+String.valueOf('*'));
                }
            }
        });
        findViewById(R.id.btnAnd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! tvResult.getText().toString().equals("") &&
                        tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '*' &&
                        tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '-' &&
                        tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '.' &&
                        tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '+'){

                    tvResult.setText(tvResult.getText().toString()+' '+String.valueOf('+'));
                }
            }
        });
        findViewById(R.id.btnMines).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! tvResult.getText().toString().equals("") &&
                        tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '*' &&
                        tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '-' &&
                        tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '.' &&
                        tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '+'){

                    tvResult.setText(tvResult.getText().toString()+' '+String.valueOf('-'));
                }
            }
        });
        findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNotSpecialChar() && isContainFxChar()){
                    StringTokenizer tokens = new StringTokenizer(tvResult.getText().toString(), " ");
                    String[] tv = new String[tokens.countTokens()];
                    int index= 0;
                    while(tokens.hasMoreTokens()){
                        tv[index] = tokens.nextToken();
                        index++;
                    }

                    double res=0;
                    double m1= Double.valueOf(tv[0]);
                    double m2 = Double.valueOf(tv[2]);

                    switch (tv[1].charAt(0)){
                        case '-':
                            res=m1 - m2;
                            break;
                        case '*':
                            res=m1 * m2;
                            break;
                        case '+':
                            res=m1 + m2;
                            break;
                    }

                    tvResult.setText(String.valueOf(res));
                }
            }
        });

    }
    public boolean isNotSpecialChar(){
        return (tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '*' &&
                tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '-' &&
                tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '.' &&
                tvResult.getText().toString().charAt(tvResult.getText().length()-1) != '+');
    }
    public boolean isContainFxChar(){
        return (tvResult.getText().toString().contains("*") ||
                tvResult.getText().toString().contains("-") ||
                tvResult.getText().toString().contains("+"));
    }

}
