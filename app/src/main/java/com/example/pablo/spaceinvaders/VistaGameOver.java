package com.example.pablo.spaceinvaders;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import java.io.IOException;

/**
 * Created by Pablo on 04/12/2016.
 */

public class VistaGameOver extends SurfaceView implements Runnable{
        private int pantallaX;
        private int pantallaY;
        private Canvas lienzo;
        private Paint paint;
        private SurfaceHolder holder;

    public VistaGameOver(Context context, int x, int y) {
        super(context);
        holder = getHolder();
        paint = new Paint();
        pantallaX = x;
        pantallaY = y;
    }

    @Override
    public void run() {
        draw();
    }
    private void draw (){
        if (holder.getSurface().isValid()){
            lienzo = holder.lockCanvas();

            lienzo.drawColor(Color.argb(255, 0, 0, 0));
            paint.setColor(Color.argb(255, 255, 255, 255));

            //Dibuja Cosas

            paint.setColor(Color.argb(255, 255, 255, 255));

            paint.setTextSize(400);
            lienzo.drawText("GAME OVER", pantallaX/(pantallaX/3), 50, paint);
            holder.unlockCanvasAndPost(lienzo);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:

                if(motionEvent.getY() < pantallaY){
                    this.setVisibility(VistaGameOver.GONE);
                }
                break;
        }
        return true;
    }
}
