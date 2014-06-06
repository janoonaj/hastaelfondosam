package com.testapps.testLibGDX;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testapps.testLibGDX.characters.cowboy.CowboysBand;


public class MyGdxGame implements ApplicationListener{
    private SpriteBatch batch;
    private float elapsedTime = 0;
    private CowboysBand cowboysBand;
    private BattleFieldController battleField;
    
	
	@Override
	public void create () {

        batch = new SpriteBatch();

        createCowboys();
        battleField.create(this.cowboysBand);
	}

    private void createCowboys() {
        cowboysBand = new CowboysBand();
        cowboysBand.addCowboyToBand();
        cowboysBand.addCowboyToBand();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
	public void render () {
        Gdx.gl.glClearColor(255f/255.0f, 205f/255.0f, 124f/255.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += Gdx.graphics.getDeltaTime();

        batch.begin();
        this.cowboysBand.render(batch, elapsedTime);
        this.battleField.render(batch);
        batch.end();
	}


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

        batch.dispose();
        cowboysBand.dispose();
    }
}
