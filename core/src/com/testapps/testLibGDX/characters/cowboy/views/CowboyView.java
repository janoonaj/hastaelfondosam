package com.testapps.testLibGDX.characters.cowboy.views;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.testapps.testLibGDX.characters.cowboy.CowboyOrientation;
import com.testapps.testLibGDX.characters.cowboy.animations.CowboyAnimation;
import com.testapps.testLibGDX.characters.cowboy.animations.CowboyShootingDown;
import com.testapps.testLibGDX.characters.cowboy.animations.CowboyShootingLeft;
import com.testapps.testLibGDX.characters.cowboy.animations.CowboyShootingRight;
import com.testapps.testLibGDX.characters.cowboy.animations.CowboyShootingUp;
import com.testapps.testLibGDX.characters.cowboy.animations.CowboyWalkingLeft;
import com.testapps.testLibGDX.characters.cowboy.animations.CowboyWalkingRight;

import java.awt.Point;

public class CowboyView {

    private final float FRAME_DURATION = 1/15f;

    private Texture texture;
    private CowboyAnimation animation;
    private TextureRegion stoppedFrame;
    protected Point pos;
    protected int width;
    protected int height;


    public CowboyView(Texture texture) {
        this.texture = texture;
        pos = new Point(0, 0);
        this.stop(new CowboyOrientation(CowboyOrientation.STOP_N));
    }

    public void render(SpriteBatch batch, float elapsedTime) {
        renderBehindMainPlayer(batch, elapsedTime);
        renderMainPlayer(batch, elapsedTime);
        renderFronMainPlayer(batch, elapsedTime);

    }

    protected void renderBehindMainPlayer(SpriteBatch batch, float elapsedTime)
    {

    }

    private void renderMainPlayer(SpriteBatch batch, float elapsedTime)
    {
        TextureRegion currentFrame = null;
        if(this.animation != null) {
            currentFrame = this.animation.getFrame(elapsedTime, true);
        }
        else if(this.stoppedFrame != null){
            currentFrame = this.stoppedFrame;
        }

        if(currentFrame != null) {
            batch.draw(currentFrame, pos.x, pos.y);
        }
    }

    protected void renderFronMainPlayer(SpriteBatch batch, float elapsedTime)
    {

    }

    protected void updateWidthAndHeight(TextureRegion currentFrame) {
        this.width = currentFrame.getRegionWidth();
        this.height = currentFrame.getRegionHeight();
    }

    public void moveRight(){
        this.stoppedFrame = null;
        this.animation = new CowboyWalkingRight(this.texture, FRAME_DURATION);
        updateWidthAndHeight(this.animation.getFrame(0, false));
    }

    public void moveLeft(){
        this.stoppedFrame = null;
        this.animation = new CowboyWalkingLeft(this.texture, FRAME_DURATION);
        updateWidthAndHeight(this.animation.getFrame(0, false));
    }

    public void shootUp(){
        this.stoppedFrame = null;
        this.animation = new CowboyShootingUp(this.texture, FRAME_DURATION);
        updateWidthAndHeight(this.animation.getFrame(0, false));
    }

    public void shootDown(){
        this.stoppedFrame = null;
        this.animation = new CowboyShootingDown(this.texture, FRAME_DURATION);
        updateWidthAndHeight(this.animation.getFrame(0, false));
    }

    public void shootLeft(){
        this.stoppedFrame = null;
        this.animation = new CowboyShootingLeft(this.texture, FRAME_DURATION);
        updateWidthAndHeight(this.animation.getFrame(0, false));
    }

    public void shootRight(){
        this.stoppedFrame = null;
        this.animation = new CowboyShootingRight(this.texture, FRAME_DURATION);
        updateWidthAndHeight(this.animation.getFrame(0, false));
    }

    public void stop(CowboyOrientation orientation){
        orientation.setTexture(this.texture);
        this.animation = null;
        this.stoppedFrame = orientation.obtainFrame();
        updateWidthAndHeight(this.stoppedFrame);
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos.setLocation(pos.x, pos.y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
