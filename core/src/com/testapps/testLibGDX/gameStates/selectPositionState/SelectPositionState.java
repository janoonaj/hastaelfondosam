package com.testapps.testLibGDX.gameStates.selectPositionState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.testapps.testLibGDX.BattleFieldController;
import com.testapps.testLibGDX.buttons.ButtonController;
import com.testapps.testLibGDX.characters.cowboy.CowboysBand;
import com.testapps.testLibGDX.gameStates.IGameStates;
import com.testapps.testLibGDX.gameStates.selectPositionState.SelectorButtonMovePlayer;

import java.awt.Point;
import java.util.HashMap;

public class SelectPositionState implements IGameStates {
    ButtonController buttonController;
    BattleFieldController battleFieldController;
    HashMap<Integer, SelectorButtonMovePlayer> selectorsButtons;
    CowboysBand band;

    public SelectPositionState(BattleFieldController battleFieldController, ButtonController buttonController, CowboysBand band) {
        this.battleFieldController = battleFieldController;
        this.buttonController = buttonController;
        this.band = band;
        createSelectorButtons();
    }

    private void createSelectorButtons() {
        Texture selector = new Texture(Gdx.files.internal("moveToSelector.png"));
        int height20Percent = Gdx.graphics.getHeight() / 5;
        int width25Percent = Gdx.graphics.getWidth() / 4;
        Point pos1 = new Point(width25Percent * 2 - selector.getWidth() / 2,
                Gdx.graphics.getHeight() - height20Percent - selector.getHeight() / 2);
        Point pos2 = new Point(width25Percent * 3 - selector.getWidth() / 2,
                Gdx.graphics.getHeight() - height20Percent*2 - selector.getHeight() / 2);
        Point pos3 = new Point(width25Percent * 3 - selector.getWidth() / 2,
                Gdx.graphics.getHeight() - height20Percent*3 - selector.getHeight() / 2);
        Point pos4 = new Point(width25Percent * 2 - selector.getWidth() / 2,
                height20Percent - selector.getHeight() / 2);
        Point pos5 = new Point(width25Percent - selector.getWidth() / 2,
                Gdx.graphics.getHeight() - height20Percent*3 - selector.getHeight() / 2);
        Point pos6 = new Point(width25Percent - selector.getWidth() / 2,
                Gdx.graphics.getHeight() - height20Percent*2 - selector.getHeight() / 2);
        selectorsButtons = new HashMap<Integer, SelectorButtonMovePlayer>();
        selectorsButtons.put(1, new SelectorButtonMovePlayer(1, selector, pos1, this));
        selectorsButtons.put(2, new SelectorButtonMovePlayer(2, selector, pos2, this));
        selectorsButtons.put(3, new SelectorButtonMovePlayer(3, selector, pos3, this));
        selectorsButtons.put(4, new SelectorButtonMovePlayer(4, selector, pos4, this));
        selectorsButtons.put(5, new SelectorButtonMovePlayer(5, selector, pos5, this));
        selectorsButtons.put(6, new SelectorButtonMovePlayer(6, selector, pos6, this));
        for(SelectorButtonMovePlayer bttn : this.selectorsButtons.values())
        {
            this.buttonController.subscribeButton(bttn);
        }
    }

    @Override
    public void init() {
        this.buttonController.hideMenuButtons();
    }

    @Override
    public void render(SpriteBatch batch, float elapsedTime) {
        Array<SelectorButtonMovePlayer> nextMovements = calculateNextMovements();
        for(SelectorButtonMovePlayer bttn : nextMovements)
        {
            bttn.render(batch);
        }

    }

    @Override
    public void dispose() {
        for(SelectorButtonMovePlayer bttn : this.selectorsButtons.values())
        {
            bttn.dispose();
        }
    }

    private Array<SelectorButtonMovePlayer> calculateNextMovements(){
        Array<SelectorButtonMovePlayer> availablePositions = new Array<SelectorButtonMovePlayer>();
        Integer myPlayerPos = this.band.getMyCowboy().getBoardPos();
        if(myPlayerPos == 1)
        {
            availablePositions.add(this.selectorsButtons.get(2));
            availablePositions.add(this.selectorsButtons.get(6));
        }
        else if(myPlayerPos == 6)
        {
            availablePositions.add(this.selectorsButtons.get(1));
            availablePositions.add(this.selectorsButtons.get(5));
        }
        else
        {
            availablePositions.add(this.selectorsButtons.get(myPlayerPos - 1));
            availablePositions.add(this.selectorsButtons.get(myPlayerPos + 1));
        }

        return availablePositions;
    }

    public void selectorPushed (SelectorButtonMovePlayer selector)
    {
        this.band.getMyCowboy().moveTo(selector.getPos());
        battleFieldController.buttonPressed(selector);
    }
}