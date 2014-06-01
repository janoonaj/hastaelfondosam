package com.testapps.testLibGDX.characters.cowboy;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.testapps.testLibGDX.characters.cowboy.animations.CowboyAnimation;
import com.testapps.testLibGDX.characters.cowboy.animations.CowboyShootingDown;
import com.testapps.testLibGDX.characters.cowboy.animations.CowboyShootingLeft;
import com.testapps.testLibGDX.characters.cowboy.animations.CowboyShootingRight;
import com.testapps.testLibGDX.characters.cowboy.animations.CowboyShootingUp;
import com.testapps.testLibGDX.characters.cowboy.animations.CowboyWalkingLeft;
import com.testapps.testLibGDX.characters.cowboy.animations.CowboyWalkingRight;

public class CowboyView {

    private final float FRAME_DURATION = 1/15f;

    private Texture texture;
    private CowboyAnimation animation;


    public CowboyView(Texture texture) {
        this.texture = texture;
    }

    public void render(SpriteBatch batch, float elapsedTime) {
        if(animation != null) {
            batch.draw(this.animation.getFrame(elapsedTime, true), 0, 0);
        }
    }

    public void moveRight(){
        animation = new CowboyWalkingRight(this.texture, FRAME_DURATION);
    }

    public void moveLeft(){
        animation = new CowboyWalkingLeft(this.texture, FRAME_DURATION);
    }

    public void shootUp(){
        animation = new CowboyShootingUp(this.texture, FRAME_DURATION);
    }

    public void shootDown(){
        animation = new CowboyShootingDown(this.texture, FRAME_DURATION);
    }

    public void shootLeft(){
        animation = new CowboyShootingLeft(this.texture, FRAME_DURATION);
    }

    public void shootRight(){
        animation = new CowboyShootingRight(this.texture, FRAME_DURATION);
    }
}
