package com.moussa.a4get.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnDay, btnNight;
    private View layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDay = (Button) findViewById(R.id.btnDay);
        btnNight = (Button) findViewById(R.id.btnNigth);
        layout = findViewById(R.id.layout);

        btnNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setBackgroundColor(-16777216);
            }
        });
        btnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setBackgroundColor(-1);
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // setContentView(R.layout.calculator);
                Intent i = new Intent(MainActivity.this, com.moussa.a4get.feature.Calculator.class);
                startActivity(i);

            }
        });


    }
}
