package com.testapps.testLibGDX.characters.cowboy;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.testapps.testLibGDX.PositionsOnScreen;
import com.testapps.testLibGDX.characters.cowboy.views.CowboyView;

import java.awt.Point;

public class Cowboy {
    private int id;
    private CowboyView view;
    private int boardPos;

    final private float speed = 1f;
    private boolean moving = false;
    private Vector2 moveTo;
    private Integer moveToBoardPosition;
    private Vector2 currentDirection;
    private boolean shooting = false;

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
        if(this.shooting)
        {

        }
        this.view.render(batch, elapsedTime);
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

    public void moveTo(Integer boardPos) {
        moveToBoardPosition = boardPos;
        Point pos3 = PositionsOnScreen.getScreenPos(boardPos);
        moveTo = new Vector2((float)(pos3.x), (float)(pos3.y));
        moving = true;
    }

    private void updatePos(float elapsedTime)
    {
        //TODO: direction should be wrong, the movement is not linear.
        //Create it as class parameter does not solve the issue
        //Maybe problems between floats and integers?
        //solution: http://gamedev.stackexchange.com/questions/74700/how-to-start-an-animation-just-once-on-an-event-in-libgdx
        //It is creating a problem of infinite loop in shooting animation?



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
            this.boardPos = this.moveToBoardPosition;
            this.moveToBoardPosition = null;
        }

        this.setPos(nextPosPt);
    }

    public void shootTo(Integer boardPos) {
        setShootingAnimation(boardPos);
        this.shooting = true;
    }

    public void die(){
        this.view.die();
    }

    public void dispose(){
        this.view.dispose();
    }

    private void setShootingAnimation(Integer boardPos) {
        Point myPos = PositionsOnScreen.getScreenPos(this.boardPos);
        Point objectivePos = PositionsOnScreen.getScreenPos(boardPos);
        Integer distX = objectivePos.x - myPos.x;
        Integer distY = objectivePos.y - myPos.y;
        if(Math.abs(distX) < Math.abs(distY))
        {//Up//Down
            if(distY > 0)
            {
                this.view.showAnimShootUp();
            }
            else
            {
                this.view.showAnimShootDown();
            }
        }
        else
        {//Right//Left
            if(distX > 0)
            {
                this.view.showAnimShootRight();
            }
            else
            {
                this.view.showAnimShootLeft();
            }
        }
    }


}
