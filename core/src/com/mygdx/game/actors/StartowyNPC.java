package com.mygdx.game.actors;

import java.io.IOException;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.mygdx.game.parserCYK.Parserv3;
import com.mygdx.game.parserCYK.ZwrocDoScreen;
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

	
	public void rozmowa(Enemy enemy1) {
		sayHello();
		if (przywitanie == true) {
			przywitanie = false;
			rozmowa[0] = true;
			console.EnterClickedforNPC = false;
		}
		if (rozmowa[0] == true) {
			//iloscCzasuTimer = (float)0.01;
			czasowaZmienna++;
			if (czasowaZmienna > 200) {
				this.Speak("Nie wyglądasz jak tutejszy! \nMam nadzieje ze nie szukasz klopotów",3);
				if (console.EnterClickedforNPC == true) {
					rozmowa[0] = false;
					rozmowa[1] = true;
					console.EnterClickedforNPC = false ;
				}
			}
			
		}
		
		if (rozmowa[1] == true) {
			
			ZwrocDoScreen wynik = null;
			
			try {
				wynik = Parserv3.Dzialaj(console.LastSentenceInConsole);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(wynik.PodajElementLista_co_zwracam(0).equals("Z_Kom") && wynik.PodajElementLista_co_zwracam(2).equals("czy_szukasz_klopotow")){
				
					//console.phraseEntereddlaNPC = false;
					if(console.LastSentenceInConsole.contains("tak")) {
					this.Speak("To świetnie bo mam dla ciebie zadanie.\nPomożesz mi?",(float)0.01);
					
					if(console.EnterClickedforNPC == true ) {
					rozmowa[1] = false;
					rozmowa[2] = true;
					czasowaZmienna=0;
					}
					}
					else if(console.LastSentenceInConsole.contains("nie")) {
						this.Speak("To dobrze bo lubimy tu przyjaznych.\nZechciałbyś mi pomóc?!",(float)0.01);
					}
					else this.Speak("Odpowiedz poprostu TAK lub NIE\ni nie marudź mi tu!", (float)0.01);
				
			}
			
		}
		
		if (rozmowa[2] == true) {
			czasowaZmienna++;
			this.Speak("Rozpraw się z tym pająkiem a\nnastepnie do mnie wróć po nagrodę!", 10);
			if(czasowaZmienna>300) {
			rozmowa[2] = false;
			czasowaZmienna = 0;
			}
		}
		
		if ((MainCharacterInside == true)&&(enemy1.Defeated == true)) {
			rozmowa[3] = true;
		}
		
		if (rozmowa[3] == true) {
			this.Speak("Wiedziałem że można na Ciebie liczyć!\nW podziękowaniu otrzymujesz miecz oraz statystykę do wyboru.\nChciałbyś być silniejszy , zwinniejszy , szybszy czy wytrzymalszy?", 1);
		}
		
	}
}
