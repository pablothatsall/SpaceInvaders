package com.example.pablo.spaceinvaders;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.pablo.spaceinvaders.R.layout.naves;

/**
 * Created by Ruben on 13/11/2016.
 */

public class ClaseNaves extends Activity {
    Button nave1;
    Button nave2;
    static int nave = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(naves);
        nave1 = (Button) findViewById(R.id.button4);
        nave2 = (Button) findViewById(R.id.button5);
        nave1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                nave = 1;
            }

        });
        nave2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                nave = 2;
            }

        });

    }

    ;

    public static int getNave() {
        return nave;
    }
}





