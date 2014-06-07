package com.testapps.testLibGDX.characters.cowboy;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testapps.testLibGDX.characters.cowboy.views.CowboyView;

import java.awt.Point;

public class Cowboy {
    private int id;
    private CowboyView view;

    public Cowboy(CowboyView view, int id) {

        this.view = view;
        this.id = id;
    }

    public void render(SpriteBatch batch, float elapsedTime) {
        this.view.render(batch, elapsedTime);
    }

    public void moveRight(){
        this.view.moveRight();
    }

    public void moveLeft(){
        this.view.moveLeft();
    }

    public void shootUp(){
        this.view.shootUp();
    }

    public void shootDown(){
       this.view.shootDown();
    }

    public void shootLeft(){
        this.view.shootLeft();
    }

    public void shootRight(){
        this.view.shootRight();
    }

    public void stop(CowboyOrientation orientation){
        this.view.stop(orientation);
    }

    public Point getPos() {
        return this.view.getPos();
    }

    public void setPos(Point pos) {
        this.view.setPos(pos);
    }

    public int getWidth() {
        return this.view.getWidth();
    }

    public int getHeight(){
        return this.view.getHeight();
    }

    public int getID() {return this.id;}
}
