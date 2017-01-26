package com.mygdx.game.actors;

import java.io.IOException;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Cloud.Statistics;
import com.mygdx.game.parserCYK.Parserv3;
import com.mygdx.game.parserCYK.ZwrocDoScreen;
import com.mygdx.ingameConsole.Console;

public class StartowyNPC extends NPC {

	public MainCharacter mainCharacter;
	public Boolean[] rozmowa = new Boolean[5];
	int czasowaZmienna = 0;
	boolean pokonanie_przeciwnika = false;
	public StartowyNPC(String Type, int X, int Y, String sciezka, Stage stage, Console console, Parserv3 parser, int a,
			int b, int c, int d) throws IOException {
		super(Type, X, Y, sciezka, stage, console, parser, a, b, c, d);
		for (int i = 0; i < 5; i++) {
			rozmowa[i] = false;
		}

		// TODO Auto-generated constructor stub
	}

	
	public void rozmowa(Enemy3 enemy3, MainCharacter mainCharacter) {
		this.mainCharacter = mainCharacter;
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
			
			czasowaZmienna = 0;
			}
		}
		
		if ((MainCharacterInside == true)&&(enemy3.Defeated == true)&&(pokonanie_przeciwnika == false)&&(rozmowa[2] == true)) {
			rozmowa[2] = false;
			rozmowa[3] = true;
			pokonanie_przeciwnika = true;
			czasowaZmienna = 0;
		}
		
		if (rozmowa[3] == true) {
			
			
			if(czasowaZmienna == 0) console.EnterClickedforNPC = false;
			rozmowa[4] = true;
			czasowaZmienna++;
		}
		
		if (rozmowa[4] == true) {
			rozmowa[3] = false;
			
			if(console.EnterClickedforNPC == false) {
				this.Speak("Wiedziałem że można na Ciebie liczyć!\nW podziękowaniu otrzymujesz miecz oraz statystykę do wyboru.\nChciałbyś być silniejszy , zwinniejszy , szybszy czy wytrzymalszy?", 1);
				czasowaZmienna = 0;
			}
			ZwrocDoScreen wynik = null;
			try {
				wynik = Parserv3.Dzialaj(console.LastSentenceInConsole);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(wynik.PodajElementLista_co_zwracam(0).equals("Z_Kom") && wynik.PodajElementLista_co_zwracam(2).equals("statystyka")){
					
					//console.phraseEntereddlaNPC = false;
					if(czasowaZmienna > 3000) rozmowa[4] = false;
					if(console.LastSentenceInConsole.contains("silniejszy")) {
					this.Speak("Dodalem ci trochę siły!\nOdwiedź kowala który znajduje się w mieście.\nWydaje mi się ze jest on w stanie ci pomóc.",(float)0.01);
					
					if(czasowaZmienna==0)	mainCharacter.statistics[0].change(6);
					mainCharacter.statistics[0].textField.setMessageText(mainCharacter.statistics[0].getStatistic());
					czasowaZmienna++;
					}
					else if(console.LastSentenceInConsole.contains("szybszy")) {
						this.Speak("Dodalem ci trochę szybkości!\nOdwiedź kowala który znajduje się w mieście.\nWydaje mi się ze jest on w stanie ci pomóc.",(float)0.01);
						
						if(czasowaZmienna==0)	mainCharacter.statistics[1].change(6);
						mainCharacter.statistics[1].textField.setMessageText(mainCharacter.statistics[1].getStatistic());
						czasowaZmienna++;
					}
					else if(console.LastSentenceInConsole.contains("wytrzymalszy")) {
						this.Speak("Dodalem ci trochę wytrzymałości!\nOdwiedź kowala który znajduje się w mieście.\nWydaje mi się ze jest on w stanie ci pomóc.",(float)0.01);
						if(czasowaZmienna==0) mainCharacter.statistics[2].change(6);
						mainCharacter.statistics[2].textField.setMessageText(mainCharacter.statistics[2].getStatistic());
						czasowaZmienna++;
					}
					else if(console.LastSentenceInConsole.contains("zwinniejszy")) {
						this.Speak("Dodalem ci trochę zwinności!\nOdwiedź kowala który znajduje się w mieście.\nWydaje mi się ze jest on w stanie ci pomóc.",(float)0.01);
						if(czasowaZmienna==0)	mainCharacter.statistics[3].change(6);
						mainCharacter.statistics[3].textField.setMessageText(mainCharacter.statistics[3].getStatistic());
						czasowaZmienna++;
					}
					else this.Speak("Poprostu wybierz co chcesz!\n Zwinność , wytrzymałość , szybkość czy siła?", (float)0.01);
					
			}
			
		}
		
	}
}
