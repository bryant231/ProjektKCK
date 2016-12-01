package com.mygdx.screens;

import com.mygdx.game.ProjektKCK;
import com.mygdx.game.Buttons.AbstractButton;
import com.mygdx.game.actors.Actors;
import com.mygdx.game.actors.MainCharacter;
import com.mygdx.ingameConsole.Console;

public class GamePlayScreen extends AbstractScreen {

	private Console console;
	public int ilosc_elemt_w_tablicy_przeszkod = 1;   //tworzymy tablice przeszkod taka duzo ile jest elementow na ktore nie mozna wejsc w grze
	private MainCharacter mainCharacter;
	public Actors layoutconsole;   //dodajemy layout konsoli
	public Actors statystykilayout;   //dodajemy layout statystyk
	public Actors mapa;          //mapa danej planszy     
	public AbstractButton CantStand[];   //tablica w ktorej sa obiekty do kolizji

	public GamePlayScreen(ProjektKCK game) {
		super(game);
		create();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		if (console.PhraseEntered == true) {

			mainCharacter.move(console.GetText() , CantStand , ilosc_elemt_w_tablicy_przeszkod);

			console.PhraseEntered = false;
		}
		
		stage.act();
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}
	
	public void create() {
		CantStand = new AbstractButton[ilosc_elemt_w_tablicy_przeszkod];
		CantStandInit();
		for (int i=0 ; i<ilosc_elemt_w_tablicy_przeszkod ; i++) {
			stage.addActor(CantStand[i].button);
		}
		layoutconsole = new Actors(0, 0, "layoutconsole.png");
		statystykilayout = new Actors(0, 668, "postaclayout.png");
		mapa = new Actors(0, 0, "forest_town.png");
		console = new Console(600, 270, 25, 40);
		stage.addActor(mapa.image);
		stage.addActor(layoutconsole.image);
		stage.addActor(console.textField);
		stage.addActor(statystykilayout.image);
		mainCharacter = new MainCharacter(0, 480, "Roman.png", stage);
		stage.addActor(mainCharacter.image);
		stage.act();
	}
	
	
	public void CantStandInit() {
		CantStand[0] = new AbstractButton(32, 541, 32, 32);
	}

}
