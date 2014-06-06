package com.testapps.testLibGDX;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testapps.testLibGDX.characters.cowboy.CowboysBand;

public class BattleFieldController {
    BattleFieldView view;

    public BattleFieldController() {
        view = new BattleFieldView();
    }

    public void create(CowboysBand cowboysBand) {
        view.create(cowboysBand);
    }

    public void render(SpriteBatch batch) {

    }
}
