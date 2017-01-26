package com.mygdx.game.actors;

import java.io.IOException;
import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Buttons.AbstractButton;
import com.mygdx.game.Cloud.cloud;
import com.mygdx.game.parserCYK.Parserv3;
import com.mygdx.ingameConsole.Console;

public abstract class Entity extends Actors {

	private Stage stage;
	protected AbstractButton button;
	protected Parserv3 Parser;
	protected Console console;
	Random generator;
	protected boolean MainCharacterInside;
	protected String Type;
	public boolean przywitanie = false;
	public float iloscCzasuTimer = 3;
	cloud Cloud2;

	public Entity(String Type, int X, int Y, String sciezka, Stage stage, Console console, Parserv3 parser, int a,
			int b, int c, int d) throws IOException {
		super(X, Y, sciezka);
		this.stage = stage;
		this.console = console;
		this.Parser = parser;
		button = new AbstractButton(a, b, c, d); // 1600,600, 200, 140
		button.button.setDebug(false);
		generator = new Random();
		MainCharacterInside = true;
		stage.addActor(button.button);
		this.Type = Type;
		Cloud2 = new cloud(50, image.getX() + 35, image.getY() + 75,1f);
		// TODO Auto-generated constructor stub
	}

	public void collisionCheck(Rectangle rectangle) {
		if (button.bounds.overlaps(rectangle)) {
			this.MainCharacterInside = true;
			
		} else
			this.MainCharacterInside = false;
			przywitanie = false;
	}

	public void Speak(String text,float czas) {
		iloscCzasuTimer = czas;
		
		Cloud2.textField.setMessageText(text);
		stage.addActor(Cloud2.textField);
		Timer.schedule(new Task() {
			@Override
			public void run() {
				Cloud2.textField.remove();
			}
		}, iloscCzasuTimer);
	}

	public boolean isMainCharacterInside() {
		return MainCharacterInside;
	}

	public void setMainCharacterInside(boolean mainCharacterInside) {
		MainCharacterInside = mainCharacterInside;
	}

	
}
