package com.testapps.testLibGDX.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.testapps.testLibGDX.buttons.ButtonController;
import com.testapps.testLibGDX.buttons.IButtonsSubscribed;
import com.testapps.testLibGDX.characters.cowboy.Cowboy;
import com.testapps.testLibGDX.characters.cowboy.CowboysBand;

import java.awt.Point;
import java.util.HashMap;

public class SelectPositionState implements IGameStates{
    ButtonController buttonController;
    HashMap<Integer, SelectorButton> selectorsButtons;
    CowboysBand band;

    public SelectPositionState(ButtonController buttonController, CowboysBand band) {
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
        selectorsButtons = new HashMap<Integer, SelectorButton>();
        selectorsButtons.put(1, new SelectorButton(1, selector, pos1, this));
        selectorsButtons.put(2, new SelectorButton(2, selector, pos2, this));
        selectorsButtons.put(3, new SelectorButton(3, selector, pos3, this));
        selectorsButtons.put(4, new SelectorButton(4, selector, pos4, this));
        selectorsButtons.put(5, new SelectorButton(5, selector, pos5, this));
        selectorsButtons.put(6, new SelectorButton(6, selector, pos6, this));
        for(SelectorButton bttn : this.selectorsButtons.values())
        {
            this.buttonController.subscribeButton(bttn);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void render(SpriteBatch batch, float elapsedTime) {
        Array<SelectorButton> nextMovements = calculateNextMovements();
        for(SelectorButton bttn : nextMovements)
        {
            bttn.render(batch);
        }

    }

    @Override
    public void dispose() {
        for(SelectorButton bttn : this.selectorsButtons.values())
        {
            bttn.dispose();
        }
    }

    private Array<SelectorButton> calculateNextMovements(){
        Array<SelectorButton> availablePositions = new Array<SelectorButton>();
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



}

class SelectorButton implements IButtonsSubscribed{
    private Integer id;
    private Texture texture;
    private Point pos;
    private SelectPositionState state;

    public SelectorButton(Integer id, Texture texture, Point pos, SelectPositionState state) {
        this.id = id;
        this.texture = texture;
        this.pos = pos;
        this.state = state;
    }

    public void render(SpriteBatch batch){
        batch.draw(this.texture, pos.x, pos.y);
    }


    public void dispose(){
        texture.dispose();
    }

    @Override
    public void screenTouched(int screenX, int screenY) {
        if(screenX >= this.pos.x && screenX <= this.pos.x + this.texture.getWidth() &&
                screenY >= this.pos.y && screenY <= this.pos.y + this.texture.getHeight())
        {
            //state.selectorPushed(this.id);
        }
    }
}