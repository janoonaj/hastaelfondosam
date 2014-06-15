package com.testapps.testLibGDX.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Point;

public class ActionMoveButton implements IActionButton{
    Texture texture;
    Point pos;

    public ActionMoveButton(Texture texture, Point pos) {
        this.texture = texture;
        this.pos = pos;
    }

    @Override
    public void render(SpriteBatch batch, float elapsedTime) {
        batch.draw(this.texture, pos.x, pos.y);
    }

    @Override
    public Point getPos() {
        return pos;
    }

    @Override
    public Boolean touchEvent(int screenX, int screenY) {
        Boolean result = false;
        if(screenX >= this.pos.x && screenX <= this.pos.x + this.texture.getWidth() &&
                  screenY >= this.pos.y && screenY <= this.pos.y + this.texture.getHeight())
        {
            result = true;
        }

        return result;
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }
}
