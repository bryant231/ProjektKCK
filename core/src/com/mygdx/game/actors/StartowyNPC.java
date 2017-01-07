package com.mygdx.game.actors;

import java.io.IOException;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.mygdx.game.parserCYK.Parserv3;
import com.mygdx.ingameConsole.Console;

public class StartowyNPC extends NPC {

	public Boolean[] rozmowa = new Boolean[5];
	int czasowaZmienna = 0;

	public StartowyNPC(String Type, int X, int Y, String sciezka, Stage stage, Console console, Parserv3 parser, int a,
			int b, int c, int d) throws IOException {
		super(Type, X, Y, sciezka, stage, console, parser, a, b, c, d);
		for (int i = 0; i < 5; i++) {
			rozmowa[i] = false;
		}

		// TODO Auto-generated constructor stub
	}

	/*
	 * public void testPlayer() {
	 * 
	 * this.Speak("Oblicz 2+2!"); if (console.phraseEntereddlaNPC == true) {
	 * console.phraseEntereddlaNPC = false; if
	 * (console.LastSentenceInConsole.contains("4")) { this.Speak("Dobrze!");
	 * 
	 * } } }
	 */
	public void rozmowa() {
		if (przywitanie == true) {
			rozmowa[0] = true;
			console.EnterClickedforNPC = false;
		}
		if (rozmowa[0] == true) {
			iloscCzasuTimer = (float)0.01;
			czasowaZmienna++;
			if (czasowaZmienna > 200) {
				this.Speak("Oblicz 2+2");
				if (console.EnterClickedforNPC == true) {
					rozmowa[0] = false;
					rozmowa[1] = true;
				}
			}
			
		}
		
		if (rozmowa[1] == true) {
			
			if (console.LastSentenceInConsole.contains("4")) {
				//console.phraseEntereddlaNPC = false;
				this.Speak("Dobrze!");
			}

			else {
				this.Speak("No chyba nie umiesz liczyc! sprobuj ponownie! Ile to 2+2?!");
			}
		}

	}
}
