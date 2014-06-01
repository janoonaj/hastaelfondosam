package com.testapps.testLibGDX.characters.cowboy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;

public class CowboysBand {
    ArrayList<CowboyController> cowboysControllers;
    private Texture cowboyTexture;

    public CowboysBand() {
        cowboysControllers = new ArrayList<CowboyController>();
        this.cowboyTexture = new Texture(Gdx.files.internal("cowboyAndando.png"));
    }

    public void addCowboyToBand(){
        CowboyController newCowboy = new CowboyController(cowboyTexture);
        cowboysControllers.add(newCowboy);
    }

    public void render(SpriteBatch batch, float elapsedTime){
        for (CowboyController cowboy : this.cowboysControllers)
        {
            cowboy.render(batch, elapsedTime);
        }

    }

    public void dispose() {
        cowboyTexture.dispose();
    }
}
