package com.testapps.testLibGDX.gameStates.selectPositionState;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testapps.testLibGDX.PositionsOnScreen;
import com.testapps.testLibGDX.buttons.IButtonsSubscribed;

import java.awt.Point;

public class SelectorButtonMovePlayer implements IButtonsSubscribed {
    private Texture texture;
    private Point pos;
    private SelectPositionState state;
    private Integer boardPos;

    public SelectorButtonMovePlayer(Texture texture, Integer boardPos, SelectPositionState state) {
        this.texture = texture;
        this.pos = PositionsOnScreen.getScreenPos(boardPos);
        this.pos.x -= texture.getWidth() / 2;
        this.pos.y -= texture.getHeight() / 2;
        this.boardPos = boardPos;
        this.state = state;
    }

    public void render(SpriteBatch batch) {
        batch.draw(this.texture, pos.x, pos.y);
    }

    public Point getPos() {
        return this.pos;
    }

    public Integer getBoardPos(){
        return this.boardPos;
    }

    public void dispose() {
        texture.dispose();
    }

    public int getWidth() {
        return this.texture.getWidth();
    }

    public int getHeight() {
        return this.texture.getHeight();
    }

    @Override
    public void screenTouched(int screenX, int screenY) {
        if (screenX >= this.pos.x && screenX <= this.pos.x + this.texture.getWidth() &&
                screenY >= this.pos.y && screenY <= this.pos.y + this.texture.getHeight()) {
            state.selectorPushed(this);
        }
    }
}
