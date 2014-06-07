package com.testapps.testLibGDX;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testapps.testLibGDX.characters.cowboy.Cowboy;
import com.testapps.testLibGDX.characters.cowboy.CowboyFactory;
import com.testapps.testLibGDX.characters.cowboy.CowboysBand;

public class BattleFieldController {
    BattleFieldView view;
    CowboyFactory cowboyFactory;
    CowboysBand cowboysBand;

    public BattleFieldController() {
        view = new BattleFieldView();
        cowboyFactory = new CowboyFactory();
        cowboysBand = new CowboysBand();
    }

    public void create() {
        createCowboys();
        view.create(cowboysBand);
    }

    private void createCowboys() {
        Cowboy cowboyI = this.cowboyFactory.createMyPlayer();
        Cowboy cowboyOther = this.cowboyFactory.createEnemy();
        this.cowboysBand.addCowboyToBand(cowboyI);
        this.cowboysBand.addCowboyToBand(cowboyOther);
    }

    public void render(SpriteBatch batch, float elapsedTime) {
        cowboysBand.render(batch, elapsedTime);
    }
}
