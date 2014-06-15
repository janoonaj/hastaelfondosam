package com.testapps.testLibGDX;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testapps.testLibGDX.buttons.ButtonController;
import com.testapps.testLibGDX.buttons.IActionButton;
import com.testapps.testLibGDX.characters.cowboy.Cowboy;
import com.testapps.testLibGDX.characters.cowboy.CowboyFactory;
import com.testapps.testLibGDX.characters.cowboy.CowboysBand;
import com.testapps.testLibGDX.gameStates.FirstState;
import com.testapps.testLibGDX.gameStates.IGameStates;
import com.testapps.testLibGDX.gameStates.SelectPositionState;

public class BattleFieldController {
    CowboyFactory cowboyFactory;
    CowboysBand cowboysBand;
    ButtonController buttonController;

    IGameStates state;
    FirstState firstState;
    SelectPositionState selectPositionState;

    public BattleFieldController() {
        cowboyFactory = new CowboyFactory();
        cowboysBand = new CowboysBand();
        buttonController = new ButtonController(this);
    }

    public void create() {
        createCowboys();

        firstState = new FirstState(cowboysBand);
        selectPositionState = new SelectPositionState(buttonController, cowboysBand);


        firstState.init();
        state = firstState;
    }

    private void createCowboys() {
        Cowboy cowboyI = this.cowboyFactory.createMyPlayer();
        Cowboy cowboyOther = this.cowboyFactory.createEnemy();
        this.cowboysBand.addCowboyToBand(cowboyI);
        this.cowboysBand.addCowboyToBand(cowboyOther);
    }

    public void render(SpriteBatch batch, float elapsedTime) {
        state.render(batch, elapsedTime);
        cowboysBand.render(batch, elapsedTime);
        buttonController.render(batch, elapsedTime);
    }

    public void buttonPressed(IActionButton actionBttn) {
        state.dispose();
        state = selectPositionState;
        state.init();
    }
}
