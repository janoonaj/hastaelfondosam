package com.testapps.testLibGDX.characters.cowboy;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.testapps.testLibGDX.Utils;
import com.testapps.testLibGDX.characters.cowboy.views.CowboyView;

import java.awt.Point;

public class Cowboy {
    private int id;
    private CowboyView view;
    private int boardPos;

    final private float speed = 1f;
    private boolean moving = false;
    private Vector2 moveTo;
    private Vector2 currentDirection;

    public Cowboy(CowboyView view, int id) {

        this.view = view;
        this.id = id;
    }

    public void render(SpriteBatch batch, float elapsedTime) {
        if(this.moving)
        {
            updatePos(elapsedTime);
            if(this.moving) {
                this.view.updateWalkingAnimation(this.currentDirection);
            }
        }
        this.view.render(batch, elapsedTime);
    }

    public void showAnimWalkRight(){
        this.view.showAnimWalkRight();
    }

    public void showAnimWalkLeft(){
        this.view.showAnimWalkLeft();
    }

    public void showAnimShootUp(){
        this.view.showAnimShootUp();
    }

    public void showAnimShootDown(){
       this.view.showAnimShootDown();
    }

    public void showAnimShootLeft(){
        this.view.showAnimShootLeft();
    }

    public void showAnimShootRight(){
        this.view.showAnimShootRight();
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

    public int getBoardPos() {
        return boardPos;
    }

    public void setBoardPos(int boardPos) {
        this.boardPos = boardPos;
    }

    public void moveTo(Point pos3) {
        moveTo = new Vector2((float)(pos3.x), (float)(pos3.y));
        moving = true;
    }

    private void updatePos(float elapsedTime)
    {
        //TODO: direction should be wrong, the movement is not linear.
        //Create it as class parameter does not solve the issue
        //Maybe problems between floats and integers?



        if(this.moving == false)
            return;

        Vector2 currentPos = new Vector2((float)(this.getPos().x), (float)(this.getPos().y));
        Vector2 newPos = currentPos.cpy();

        float realDistanceToEnd = Vector2.dst(newPos.x, newPos.y, moveTo.x, moveTo.y);
        currentDirection = this.moveTo.cpy();
        currentDirection.sub(newPos).nor();
        newPos.x += this.speed * currentDirection.x * elapsedTime;
        newPos.y += this.speed * currentDirection.y * elapsedTime;

        float distanceMoved = Vector2.dst(newPos.x, newPos.y, currentPos.x, currentPos.y);
        Point nextPosPt = new Point((int)newPos.x, (int)newPos.y);
        if(distanceMoved >= realDistanceToEnd)
        {
            nextPosPt.x = (int) newPos.x;
            nextPosPt.y = (int) newPos.y;
            this.moving = false;
            this.view.stop(new CowboyOrientation(CowboyOrientation.STOP_N));
        }

        this.setPos(nextPosPt);
    }
}
