package com.testapps.testLibGDX;


import com.badlogic.gdx.Gdx;
import com.testapps.testLibGDX.characters.cowboy.CowboyController;
import com.testapps.testLibGDX.characters.cowboy.CowboyOrientation;
import com.testapps.testLibGDX.characters.cowboy.CowboysBand;

import java.awt.Point;

public class BattleFieldView {

    public BattleFieldView() {
    }

    public void create(CowboysBand cowboysBand) {
        displayCowboys(cowboysBand);
    }

    private void displayCowboys(CowboysBand cowboysBand) {
        CowboyController cowboy1 = cowboysBand.getCowboy(1);
        CowboyController cowboy2 = cowboysBand.getCowboy(2);
        Point ptUpperCenter  = new Point(Gdx.graphics.getWidth() / 2 - cowboy1.getWidth() / 2,
                Gdx.graphics.getHeight() - cowboy1.getHeight());
        cowboy1.setPos(ptUpperCenter);
        cowboy1.stop(new CowboyOrientation(CowboyOrientation.STOP_S));
        Point ptLowerCenter  = new Point(Gdx.graphics.getWidth() / 2 - cowboy1.getWidth() / 2,
                0);
        cowboy2.setPos(ptLowerCenter);


    }
}
