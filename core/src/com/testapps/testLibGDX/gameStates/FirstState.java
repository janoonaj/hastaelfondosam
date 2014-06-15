package com.testapps.testLibGDX.gameStates;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testapps.testLibGDX.characters.cowboy.Cowboy;
import com.testapps.testLibGDX.characters.cowboy.CowboyOrientation;
import com.testapps.testLibGDX.characters.cowboy.CowboysBand;

import java.awt.Point;

public class FirstState implements IGameStates{
    CowboysBand cowboys;

    public FirstState(CowboysBand cowboysBand) {
        cowboys = cowboysBand;
    }

    @Override
    public void render(SpriteBatch batch, float elapsedTime) {

    }



    @Override
    public void init() {
        Cowboy cowboy1 = cowboys.getCowboy(1);
        Cowboy cowboy2 = cowboys.getCowboy(2);
        Point ptUpperCenter  = new Point(Gdx.graphics.getWidth() / 2 - cowboy1.getWidth() / 2,
                Gdx.graphics.getHeight() - cowboy1.getHeight());
        Point ptLowerCenter  = new Point(Gdx.graphics.getWidth() / 2 - cowboy1.getWidth() / 2,
                0);

        cowboy1.setPos(ptLowerCenter);
        cowboy1.stop(new CowboyOrientation(CowboyOrientation.STOP_S));

        cowboy2.setPos(ptUpperCenter);
    }

    @Override
    public void dispose() {

    }
}
