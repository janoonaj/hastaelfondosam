package com.testapps.testLibGDX.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Point;

public interface IActionButton {

    void render(SpriteBatch batch, float elapsedTime);

    Point getPos();

    //Return: pressed
    Boolean touchEvent(int screenX, int screenY);

    void show();

    void hide();

    public void dispose();
}
