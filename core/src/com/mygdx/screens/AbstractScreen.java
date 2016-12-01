package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.ProjektKCK;

public abstract class AbstractScreen implements Screen {   //screen na ktorym budujemy pozostale , zawiera scene i kamere
	
	protected ProjektKCK game;
	protected Stage stage;
	protected OrthographicCamera camera;
	protected SpriteBatch spriteBatch;
	
	public AbstractScreen(ProjektKCK game) {
		this.game = game;
		createCamera();
		stage = new Stage(new StretchViewport(ProjektKCK.WIDTH,ProjektKCK.HEIGHT,camera));
		spriteBatch = new SpriteBatch();
		Gdx.input.setInputProcessor(stage);
	}
	
	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,6000,6000);  //6000x6000 rozmiar naszej mapy
		camera.update();
		
	}

	public void show (){
		
	}
	
	@Override
	public void render (float delta){
		clearScreen();
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
	}


	private void clearScreen() {
		Gdx.gl.glClearColor( 0 , 0 , 0 , 0 );
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	public void pause (){
	}

	
	public void resume (){}


	public void hide (){}

	
	public void dispose (){
		game.dispose();
	}
}
