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
    
	
	@Override
	public void create () {

        batch = new SpriteBatch();

        createCowboys();
	}

    private void createCowboys() {
        cowboysBand = new CowboysBand();
        cowboysBand.addCowboyToBand();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
	public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += Gdx.graphics.getDeltaTime();

        batch.begin();
        this.cowboysBand.render(batch, elapsedTime);
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
