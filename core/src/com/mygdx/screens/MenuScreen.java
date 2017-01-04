package com.mygdx.screens;

import java.io.IOException;

import com.mygdx.game.ProjektKCK;
import com.mygdx.game.actors.Actors;
import com.mygdx.ingameConsole.Console;

public class MenuScreen extends AbstractScreen {

	private Console console;
	private Actors background;

	public MenuScreen(ProjektKCK game) {
		super(game);
		create();
	}

	public void create() {
		console = new Console(600, 210, 230, 60);
		background = new Actors(0, 0, "Menu\\Background.jpg");

		stage.addActor(background.image);
		stage.addActor(console.textField);
		stage.act();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		GetCommendAndDoIt(console.PhraseEntered);
		stage.act();
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	public void ConsoleCommendsMenuScreen(String commend) {
		if (commend.equals("rozpocznij"))
			try {
				dispose();
				game.setScreen(new TutorialScreen(game));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Problem przy zmianie Menu -> Tutorial");
			}
		
	}

	public void GetCommendAndDoIt(boolean PhraseEntered) {
		if (PhraseEntered == true) {
			PhraseEntered = false;
			ConsoleCommendsMenuScreen(console.GetText());
		}
	}

}
