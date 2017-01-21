package com.mygdx.game.actors;

import java.io.IOException;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.parserCYK.Parserv3;
import com.mygdx.game.parserCYK.ZwrocDoScreen;
import com.mygdx.ingameConsole.Console;

public class NPC extends Entity {
	
	public NPC(String Type, int X, int Y, String sciezka, Stage stage, Console console, Parserv3 parser, int a, int b,
			int c, int d) throws IOException {
		super(Type, X, Y, sciezka, stage, console, parser, a, b, c, d);
		// TODO Auto-generated constructor stub
	}

	public void sayHello() {

		int licznik = 0;

		if (MainCharacterInside == true) {
			if (console.phraseEntereddlaNPC == true) {
				console.phraseEntereddlaNPC = false;
				ZwrocDoScreen wynik = null;
				
				try {
					wynik = Parserv3.Dzialaj(console.LastSentenceInConsole);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(wynik.PodajElementLista_co_zwracam(0).equals("Z_Kom") 
				   && wynik.PodajElementLista_co_zwracam(2).equals("przywitanie")){
					MainCharacter.SetMcState("rozmowa");
					int l = 0;
					// Licze ile jest przywitan
					for (int g = 0; g < Parserv3.ttab.length; g++) {
						if (Parserv3.ttab[g].PodajLS().equals("przywitanie")) {
							licznik++;
						}
					}
					// Wpisuje przywitania do tablicy
					String[] temptab = new String[licznik];
					for (int h = 0; h < Parserv3.ttab.length; h++) {
						if (Parserv3.ttab[h].PodajLS().equals("przywitanie")) {
							temptab[l] = Parserv3.ttab[h].PodajPS();
							l++;
						}
					}
					int wyl = 0;
					// Losuje liczbe
					wyl = generator.nextInt(licznik);
					
					this.Speak(temptab[wyl]);
						
					przywitanie = true;
				}
			}
		}
	}
	public void sayBye() {

		int licznik = 0;

		if (MainCharacterInside == true) {
	
				int l = 0;
				// Licze ile jest pozegnan
				for (int g = 0; g < Parserv3.ttab.length; g++) {
					if (Parserv3.ttab[g].PodajLS().equals("pozegnanie")) {
						licznik++;
					}
				}
				// Wpisuje pozegnania do tablicy
				String[] temptab = new String[licznik];
				for (int h = 0; h < Parserv3.ttab.length; h++) {
					if (Parserv3.ttab[h].PodajLS().equals("pozegnanie")) {
						temptab[l] = Parserv3.ttab[h].PodajPS();
						l++;
					}
				}
				int wyl = 0;
				// Losuje liczbe
				wyl = generator.nextInt(licznik);
					
				this.Speak(temptab[wyl]);
						
			}
		
	}
}

