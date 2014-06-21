package com.testapps.testLibGDX.buttons;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.testapps.testLibGDX.BattleFieldController;
import com.testapps.testLibGDX.Utils;

import java.awt.Point;

public class ButtonController implements InputProcessor{
    Array<IActionButton> menuButtons;
    Array<IButtonsSubscribed> subscribedButtons;
    BattleFieldController battleFieldController;

    public ButtonController(BattleFieldController battleFieldController) {
        Gdx.input.setInputProcessor(this);

        this.battleFieldController = battleFieldController;
        menuButtons = new Array<IActionButton>();
        subscribedButtons = new Array<IButtonsSubscribed>();
        createMoveButton();
        this.showMenuButtons();
    }

    private void createMoveButton() {
        Texture texture = new Texture(Gdx.files.internal("footprint.png"));
        Point pos = new Point(Gdx.graphics.getWidth() / 40,
                                Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 40) - texture.getHeight());
        this.menuButtons.add(new ActionMoveButton(texture, pos));
    }

    public void render(SpriteBatch batch, float elapsedTime){
        for(IActionButton button : this.menuButtons)
        {
            button.render(batch, elapsedTime);
        }
    }

    public void subscribeButton(IButtonsSubscribed button){
        this.subscribedButtons.add(button);
    }

    public void hideMenuButtons(){
        //Don't use Iterators because it can crash with for loop in touchDown
        for(int i = 0; i < menuButtons.size; i++)
        {
            menuButtons.get(i).hide();
        }
    }

    public void showMenuButtons(){
        //Don't use Iterators because it can crash with for loop in touchDown
        for(int i = 0; i < menuButtons.size; i++)
        {
            menuButtons.get(i).show();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenY = convertFromTouchToScreenCoordenades(screenY);

        for(IActionButton actionBttn : this.menuButtons)
        {
           if(actionBttn.touchEvent(screenX, screenY))
           {
               battleFieldController.buttonPressed(actionBttn);
           }
        }

        for(IButtonsSubscribed subscribedBttn : this.subscribedButtons)
        {
            subscribedBttn.screenTouched(screenX, screenY);
        }


        return false;
    }

    private int convertFromTouchToScreenCoordenades(int screenY) {
        return Gdx.graphics.getHeight() - screenY;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void dispose() {
        for(IActionButton actionBttn : this.menuButtons)
        {
            actionBttn.dispose();
        }

        for(IButtonsSubscribed subscribedBttn : this.subscribedButtons)
        {
            subscribedBttn.dispose();
        }
    }
}
