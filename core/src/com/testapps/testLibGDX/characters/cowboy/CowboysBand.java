package com.testapps.testLibGDX.characters.cowboy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CowboysBand {
    HashMap<Integer, CowboyController> cowboysControllers;
    private Texture cowboyTexture;

    private Integer currentID;

    public CowboysBand() {
        currentID = 1;
        cowboysControllers = new HashMap<Integer, CowboyController>();
        this.cowboyTexture = new Texture(Gdx.files.internal("cowboyAndando.png"));
    }

    public void addCowboyToBand(){
        CowboyController newCowboy = new CowboyController(cowboyTexture, this.currentID);
        cowboysControllers.put(currentID, newCowboy);
        currentID++;
    }

    public CowboyController getCowboy(Integer id){
        return this.cowboysControllers.get(id);
    }

    public void render(SpriteBatch batch, float elapsedTime){
        for (CowboyController cb : this.cowboysControllers.values()) {
            cb.render(batch, elapsedTime);
        }

    }

    public void dispose() {
        currentID = 1;
        cowboyTexture.dispose();
    }
}
