package com.testapps.testLibGDX;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testapps.testLibGDX.buttons.ActionMoveButton;
import com.testapps.testLibGDX.buttons.ButtonController;
import com.testapps.testLibGDX.buttons.IActionButton;
import com.testapps.testLibGDX.buttons.IButtonsSubscribed;
import com.testapps.testLibGDX.characters.cowboy.Cowboy;
import com.testapps.testLibGDX.characters.cowboy.CowboyFactory;
import com.testapps.testLibGDX.characters.cowboy.CowboysBand;
import com.testapps.testLibGDX.gameStates.InitGameState;
import com.testapps.testLibGDX.gameStates.IGameStates;
import com.testapps.testLibGDX.gameStates.MainState;
import com.testapps.testLibGDX.gameStates.selectPositionState.SelectPositionState;
import com.testapps.testLibGDX.gameStates.selectPositionState.SelectorButtonMovePlayer;

public class BattleFieldController {
    CowboyFactory cowboyFactory;
    CowboysBand cowboysBand;
    ButtonController buttonController;

    private IGameStates state;
    private InitGameState initGameState;
    private MainState mainState;
    SelectPositionState selectPositionState;

    public BattleFieldController() {
        cowboyFactory = new CowboyFactory();
        cowboysBand = new CowboysBand();
        buttonController = new ButtonController(this);
    }

    public void create() {
        createCowboys();

        initGameState = new InitGameState(cowboysBand);
        mainState = new MainState(this.buttonController);
        selectPositionState = new SelectPositionState(this, buttonController, cowboysBand);


        initGameState.init();
        state = initGameState;
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
        //state.dispose(); TODO it's Deleting textures... next time the state is required the images are not hidden
        if(actionBttn instanceof ActionMoveButton)
        {
            state = this.selectPositionState;
        }
        state.init();
    }

    public void buttonPressed(IButtonsSubscribed buttonSubscribed) {
        //state.dispose(); TODO it's Deleting textures... next time the state is required the images are not hidden
        if(buttonSubscribed instanceof SelectorButtonMovePlayer)
        {
            state = this.mainState;
        }
        state.init();
    }

}
