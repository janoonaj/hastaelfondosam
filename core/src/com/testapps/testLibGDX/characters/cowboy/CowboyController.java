package com.testapps.testLibGDX.characters.cowboy;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CowboyController {
    CowboyView view;

    public CowboyController(Texture cowboy) {

        this.view = new CowboyView(cowboy);


        /////////////////////////////////
        executeTest();
        /////////////////////////////////
    }

    public void render(SpriteBatch batch, float elapsedTime) {
        this.view.render(batch, elapsedTime);
    }

    private void executeTest(){
        //this.view.shootLeft();
        this.view.stop(new CowboyOrientation(CowboyOrientation.STOP_NW));
    }
}
