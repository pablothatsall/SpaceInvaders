package com.example.pablo.spaceinvaders;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import static com.example.pablo.spaceinvaders.R.layout.naves;

/**
 * Created by Ruben on 13/11/2016.
 */

public class ClaseNaves extends Activity{
        Button nave1;
        Button nave2;
        NaveJugador nave;
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(naves);
        nave1=(Button)findViewById(R.id.button4);
        nave2=(Button)findViewById(R.id.button5);
        nave1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                        // TODO Auto-generated method stub
                       nave.navex = 1;
                }

        });
        nave2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                        // TODO Auto-generated method stub
                        nave.navex = 2;
                }

        });
};}





