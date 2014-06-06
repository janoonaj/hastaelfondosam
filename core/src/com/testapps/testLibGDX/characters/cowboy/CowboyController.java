package com.testapps.testLibGDX.characters.cowboy;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CowboyController {
    private Integer id;
    private CowboyView view;

    public CowboyController(Texture cowboy, Integer id) {

        this.view = new CowboyView(cowboy);
        this.id = id;

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
