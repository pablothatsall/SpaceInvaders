package com.example.pablo.spaceinvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Pablo on 28/09/2016.
 */

public class VistaSpaceInvaders extends SurfaceView implements Runnable {
    Context context;
    private Thread hilo = null;
    private SurfaceHolder holder;

    //Booleanos para controlar el juego
    private volatile boolean funcionando;
    private boolean pausado = true;

    private Canvas lienzo;
    private Paint paint;

    private long fps;
    private long timeThisFrame;

    private int pantallaX;
    private int pantallaY;

    private NaveJugador nave;
    private Disparo disparo;
    private Disparo[] disparoInvasores = new Disparo[200];
    private int sigDisparo;
    private int maxDisparos = 10;

    //Invader[] invaders = new Invader[60];
    //int numInvaders = 0;

    //Cubos defensivos
    //Sonidos
    int puntuacion = 0;
    int vidas = 3;

    public VistaSpaceInvaders(Context context, int x, int y){
        super(context);
        this.context = context;

        holder = getHolder();
        paint = new Paint();
        pantallaX = x;
        pantallaY = y;

        prepararNivel();
    }
    private void prepararNivel(){
        //Aquí se inicializa
        nave = new NaveJugador(context, pantallaX, pantallaY);
        disparo = new Disparo(pantallaY);
        for(int i = 0; i < disparoInvasores.length; i++){
            disparoInvasores[i] = new Disparo(pantallaY);
        }

    }


    @Override
    public void run(){
        while (funcionando){
            long startFrameTime = System.currentTimeMillis();
            if(!pausado){
                actualizar();
            }
            draw();
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if(timeThisFrame >= 1){
                fps = 1000 / timeThisFrame;
            }
        }
    }

    private void actualizar(){
        boolean bumped = false;
        boolean perdido = false;
        if (nave.getX > 0 && Nave.getX < pantallaY){
            nave.actualizar(fps);
        }
        for(int i = 0; i < disparoInvasores.length; i++){
            if(disparoInvasores[i].getEstado()){
                disparoInvasores[i].actualizar(fps);
            }
        }
        if (perdido){
            prepararNivel();
        }
        if(disparo.getEstado()){
            disparo.actualizar(fps);
            if (disparo.getPuntoImpactoY() < 0){
                disparo.setInactiva();
            }

        }
    }

    private void draw(){
        if (holder.getSurface().isValid()){
            lienzo = holder.lockCanvas();

            lienzo.drawColor(Color.argb(255, 0, 0, 0));
            paint.setColor(Color.argb(255, 255, 255, 255));
            if (disparo.getEstado()) {
                lienzo.drawRect(disparo.getRect(), paint);
            }
            for(int i = 0; i < disparoInvasores.length; i++){
                if(disparoInvasores[i].getEstado()){
                    lienzo.drawRect(disparoInvasores[i].getRect(), paint);
                }
            }
            //Dibuja Cosas
            lienzo.drawBitmap(nave.getBitmap(),nave.getX(), pantallaY - 80, paint);
            paint.setColor(Color.argb(255, 255, 255, 255));
            paint.setTextSize(40);
            lienzo.drawText("Puntuacion: " + puntuacion + " Vidas: " + vidas, 10, 50, paint);
            holder.unlockCanvasAndPost(lienzo);
        }
    }

    public void pausa(){
        funcionando = false;
        try{
            hilo.join();
        }catch(InterruptedException e){
            Log.e("Error: ", "joining thread");
        }
    }
    public void continuar(){
        funcionando = true;
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                pausado = false;
                if (motionEvent.getY() > pantallaY - pantallaY /8){
                    if (motionEvent.getX() > pantallaX / 2){
                        nave.setEstadoMovimiento(nave.DER);
                    }else{
                        nave.setEstadoMovimiento(nave.IZQ);
                    }

                }
                if(motionEvent.getY() < pantallaY - pantallaY / 8){
                    if(disparo.disparar(nave.getX()+nave.getAnchura()/2, pantallaY, disparo.UP)){

                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (motionEvent.getY() > pantallaY - pantallaY / 10){
                    nave.setEstadoMovimiento((nave.PARADO));
                }
                break;
        }
        return true;
    }
}
