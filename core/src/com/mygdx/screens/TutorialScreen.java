package com.mygdx.screens;

import com.mygdx.game.ProjektKCK;
import com.mygdx.game.Buttons.AbstractButton;
import com.mygdx.game.actors.Actors;
import com.mygdx.game.actors.MainCharacter;
import com.mygdx.ingameConsole.Console;

public class TutorialScreen extends AbstractScreen {

	private Console console;
	public int ilosc_elemt_w_tablicy_przeszkod = 1;   //tworzymy tablice przeszkod taka duzo ile jest elementow na ktore nie mozna wejsc w grze
	private MainCharacter mainCharacter;
	public Actors layoutconsole;   //dodajemy layout konsoli
	public Actors statslayout;   //dodajemy layout statystyk
	public Actors map;          //mapa danej planszy    
	public Actors map2;
	public AbstractButton CantStand[];   //tablica w ktorej sa obiekty do kolizji
	public Actors npchouse;				//dodajemy domek NPCta - test

	public TutorialScreen(ProjektKCK game) {
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
		refreshCamera();
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
		layoutconsole = new Actors(0, 0, "Layout\\layoutconsole.png");
		statslayout = new Actors(0, 668, "Layout\\statslayout.png");
		map = new Actors(0, 0, "Maps\\tutorial\\1.jpg");
		mainCharacter = new MainCharacter(400, 450, "CharacterMovement\\walking e0000.png", stage);
		map2 = new Actors(0,0, "Maps\\tutorial\\2.png");
		npchouse = new Actors(1600,680,"NPCMovement\\NPCHouse0001.png");
		console = new Console(600, 270, 25);
		
		stage.addActor(map.image);
		stage.addActor(mainCharacter.image);
		stage.addActor(map2.image);
		stage.addActor(npchouse.image);
		stage.addActor(layoutconsole.image);
		stage.addActor(console.textField);
		stage.addActor(statslayout.image);
		stage.addActor(mainCharacter.statistics[0].textField);
		stage.addActor(mainCharacter.statistics[1].textField);
		stage.addActor(mainCharacter.statistics[2].textField);
		stage.addActor(mainCharacter.statistics[3].textField);
		stage.act();
	}
	
	
	public void CantStandInit() {
		CantStand[0] = new AbstractButton(289, 320, 32, 32);
	}
	public void refreshCamera() {
		camera.position.set(mainCharacter.image.getX()+50,mainCharacter.image.getY(),0);	
		statslayout.image.setX(mainCharacter.image.getX()-470);
		statslayout.image.setY(mainCharacter.image.getY()+290);
		console.textField.setX(mainCharacter.image.getX()-260);
		console.textField.setY(mainCharacter.image.getY()-370);
		layoutconsole.image.setX(mainCharacter.image.getX()-480);
		layoutconsole.image.setY(mainCharacter.image.getY()-390);
		mainCharacter.statsPositionUpdate();
	}
	
}
