package com.example.pablo.spaceinvaders;

import android.graphics.RectF;

/**
 * Created by Pablo on 28/09/2016.
 */

public class Disparo {
    private float x;
    private float y;
    private RectF rect;

    public final int UP = 0;
    public final int DOWN = 1;
    int heading = -1;
    float velocidad = 350;

    private int anchura = 1;
    private int altura;

    private boolean activada;

    public Disparo(int pantallaY){
        altura = pantallaY / 20;
        activada = false;
        rect = new RectF();
    }

    public RectF getRect(){
        return rect;
    }
    public boolean getEstado(){
        return activada;
    }
    public void setInactiva(){
        activada = false;
    }
    public float getPuntoImpactoYÇ(){
        if (heading == DOWN){
            return y + altura;
        }else{
            return y;
        }
    }

    public boolean disparar(float iniX, float iniY, int direccion){
        if (!activada){
            x = iniX;
            y = iniY;
            heading = direccion;
            activada = true;
            return true;
        }
        // Si la bala está ya activada
        return false;
    }

    public void actualizar(long fps){
        if(heading == UP){
            y = y - velocidad / fps;
        }else{
            y = y + velocidad / fps;
        }
        rect.left = x;
        rect.right = x + anchura;
        rect.top = y;
        rect.bottom = y + altura;
    }
}
