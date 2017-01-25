package com.mygdx.screens;

import java.io.IOException;

import com.mygdx.game.ProjektKCK;
import com.mygdx.game.Buttons.AbstractButton;
import com.mygdx.game.actors.Actors;
import com.mygdx.game.actors.MainCharacter;
import com.mygdx.game.actors.StartowyNPC;
import com.mygdx.game.parserCYK.Parserv3;
import com.mygdx.game.parserCYK.ZwrocDoScreen;
import com.mygdx.ingameConsole.Console;
import com.mygdx.game.actors.Enemy;
import com.mygdx.game.actors.Enemy2;
import com.mygdx.game.actors.Enemy3;

public class TutorialScreen extends AbstractScreen {
	private Console console;
	public int ilosc_elemt_w_tablicy_przeszkod = 4; // tworzymy tablice
													// przeszkod taka duzo ile
													// jest elementow na ktore
													// nie mozna wejsc w grze
	private MainCharacter mainCharacter;
	public Actors layoutconsole; // dodajemy layout konsoli
	public Actors statslayout; // dodajemy layout statystyk
	public Actors map; // mapa danej planszy
	public Actors map2;
	public AbstractButton CantStand[]; // tablica w ktorej sa obiekty do kolizji

	public Parserv3 Parser1;
	public StartowyNPC npc1;
	public StartowyNPC npc0;
	public Enemy enemy1;
	public Enemy2 enemy2;
	public Enemy3 enemy3;

	public TutorialScreen(ProjektKCK game) throws IOException {
		super(game);
		create();
	}

	public void create() throws IOException {
		try {
			Parser1 = new Parserv3();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CantStand = new AbstractButton[ilosc_elemt_w_tablicy_przeszkod];
		CantStandInit();
		for (int i = 0; i < ilosc_elemt_w_tablicy_przeszkod; i++) {
			stage.addActor(CantStand[i].button);
		}
		layoutconsole = new Actors(0, 0, "Layout\\layoutconsole.png");
		statslayout = new Actors(0, 668, "Layout\\statslayout.png");
		map = new Actors(0, 0, "Maps\\tutorial\\1.jpg");
		map2 = new Actors(0, 0, "Maps\\tutorial\\2.png");
		console = new Console(600, 270, 25, 1);
		mainCharacter = new MainCharacter(432, 450, "CharacterMovement\\walking e0000.png", stage);
		npc0 = new StartowyNPC("Pierwszy", 560, 482, "StarterNPC\\stoppped_0001.png", this.stage, this.console,
				this.Parser1, 450, 450, 300, 140);
		npc1 = new StartowyNPC("Startowy", 1650, 560, "NPCMovement\\stopped0000.png", this.stage, this.console,
				this.Parser1, 1600, 500, 200, 140);
		enemy1 = new Enemy("Wrog", 1650, 350, "SpiderEnemy\\stopped_0000.png", this.stage, this.console, this.Parser1,
				1600, 300, 200, 140);
		enemy2 = new Enemy2("Wrog2", 1950, 350, "SpiderEnemy\\stopped_0000.png", this.stage, this.console, this.Parser1,
				1900, 300, 200, 140);
		enemy3 = new Enemy3("Wrog3", 2250, 350, "SpiderEnemy\\stopped_0000.png", this.stage, this.console, this.Parser1,
				2200, 300, 200, 140);

		stage.addActor(map.image);
		stage.addActor(map2.image);
		stage.addActor(npc1.image);
		stage.addActor(npc0.image);
		stage.addActor(enemy1.image);
		stage.addActor(enemy2.image);
		stage.addActor(enemy3.image);
		stage.addActor(mainCharacter.image);
		stage.addActor(layoutconsole.image);
		stage.addActor(console.textField);
		stage.addActor(statslayout.image);
		stage.addActor(mainCharacter.statistics[0].textField);
		stage.addActor(mainCharacter.statistics[1].textField);
		stage.addActor(mainCharacter.statistics[2].textField);
		stage.addActor(mainCharacter.statistics[3].textField);
		stage.act();

	}

	@Override
	public void render(float delta) {
		super.render(delta);
		whatToDo();
		CanTalkWithNpc();
		refreshCamera();

		stage.act();
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}

	private void whatToDo() {
		if (console.PhraseEntered == true) {

			String fraza = new String();
			fraza = console.GetText();

			// Tablica zwracana przez parser
			ZwrocDoScreen wynik = new ZwrocDoScreen();
			fraza = fraza.toLowerCase();
			try {
				wynik = Parserv3.Dzialaj(fraza);
			} catch (IOException e) {
				System.out.println("Popsules nasza gre");
			}

			// Wyswietla w konsoli co zwraca parser (czy fraza jest zdaniem czy
			// nie).
			// Jesli jest to wyswietla tez jego typ
			// System.out.println(wynik[0]);

			if (wynik.PodajRozmiarLista_co_zwracam() == 0) {
				mainCharacter.Speak("Nie rozumiem Cie!");
			} else {

				switch (wynik.PodajElementLista_co_zwracam(0)) {
				case "Z_Idz":
					// System.out.println(wynik.PodajCzy_liczba_kratek());
					if (wynik.PodajCzy_liczba_kratek() == false) {
						mainCharacter.move(wynik.PodajElementLista_co_zwracam(1), CantStand,
								ilosc_elemt_w_tablicy_przeszkod);
					} else {
						// System.out.println(wynik.PodajLiczba_kratek());
						mainCharacter.moveBy(wynik.PodajElementLista_co_zwracam(1), wynik.PodajLiczba_kratek(),
								CantStand, ilosc_elemt_w_tablicy_przeszkod);
					}
					break;
				case "Z_Atakuj":
					mainCharacter.SetCanAttack(true);
					CanAttackNpc(wynik);
					break;
				case "Z_Kom":
					if (wynik.PodajCzyLiczba() == false) {
						mainCharacter.Speak(wynik.PodajElementLista_co_zwracam(1));
					} else {
						mainCharacter.Speak(Integer.toString(wynik.PodajLiczba()));
					}
					break;
				}
			}

			console.PhraseEntered = false;

		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	public void CantStandInit() {
		CantStand[0] = new AbstractButton(289, 320, 32, 32);
		CantStand[1] = new AbstractButton(609, 320, 32, 32);
		CantStand[2] = new AbstractButton(289, 610, 32, 32);
		CantStand[3] = new AbstractButton(609, 610, 32, 32);
	}

	public void refreshCamera() {
		camera.position.set(mainCharacter.image.getX() + 50, mainCharacter.image.getY(), 0);
		statslayout.image.setX(mainCharacter.image.getX() - 470);
		statslayout.image.setY(mainCharacter.image.getY() + 290);
		console.textField.setX(mainCharacter.image.getX() - 260);
		console.textField.setY(mainCharacter.image.getY() - 370);
		layoutconsole.image.setX(mainCharacter.image.getX() - 480);
		layoutconsole.image.setY(mainCharacter.image.getY() - 390);
		mainCharacter.statsPositionUpdate();
	}

	public void CanTalkWithNpc() {
		npc1.collisionCheck(mainCharacter.bounds);
		npc0.collisionCheck(mainCharacter.bounds);
		npc1.rozmowa();
		npc0.rozmowa();
		if (enemy1.Defeated == false)
			enemy1.displayDamage();
		if (enemy2.Defeated == false)
			enemy2.displayDamage();
		if (enemy3.Defeated == false)
			enemy3.displayDamage();
	}

	public void CanAttackNpc(ZwrocDoScreen wynik){
		enemy1.collisionCheck(mainCharacter.bounds);
		enemy2.collisionCheck(mainCharacter.bounds);
		enemy3.collisionCheck(mainCharacter.bounds);
		
			
		if(mainCharacter.GetCanAttack() == true){
			if (enemy1.isMainCharacterInside() == true) {
			enemy1.SetUsedWord(wynik.PodajElementLista_co_zwracam(1));
			enemy1.IsHitbyMC();
			}
			if (enemy2.isMainCharacterInside() == true) {
			enemy2.SetUsedWord(wynik.PodajElementLista_co_zwracam(1));
			enemy2.IsHitbyMC();
			}
			if (enemy3.isMainCharacterInside() == true) {
			enemy3.SetUsedWord(wynik.PodajElementLista_co_zwracam(1));
			enemy3.IsHitbyMC();
			}
		}
	}

}
