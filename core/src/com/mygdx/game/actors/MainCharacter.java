package com.mygdx.game.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Buttons.AbstractButton;
import com.mygdx.game.Cloud.Statistics;
import com.mygdx.game.Cloud.cloud;

public class MainCharacter extends Actors {
	private float delay = (float) 0.05;   //ile opoznienia miedzy ruchami
	private int MoveCountPixels = 32;     //o ile pixeli sie przesunie w ciagu calego 1 cyklu ruchu
	public Rectangle TryBounds;           //koordynaty ktore sa o krok do przodu przed glowna postacia
	private cloud Cloud;                  //chatbox
	private Statistics[] statistics;      //tablica[4] statystyk 
	private Stage stage;                  
	public boolean overlaptrue = false;   //flaga do kolizji

	public MainCharacter(int X, int Y, String sciezka, Stage stage) {
		super(X, Y, sciezka);
		this.stage = stage;
		init(X, Y);
	}

	public void move(String gdzie, AbstractButton[] CantStand, int iloscElem) {
		
		overlaptrue = false;  //flage ustawiamy na nie bo glowna postac nie ma kolizji
		
		if (gdzie.equals("do gory")) {      //jesli ktos wpisal do gory w konsoli
			Timer.schedule(new Task() {     //timer + zadanie
				@Override
				public void run() {
					TryBounds.set(bounds.getX(), bounds.getY() + 1, 32, 32); //dodajemy do naszej zmiennej ktora idzie przed romanem o 1 do gory
					for (int i = 0; i < iloscElem; i++) {
						if (TryBounds.overlaps(CantStand[i].bounds)) {     //sprawdzamy czy ta zmienna ma z czyms kolizje
							overlaptrue = true;  //jesli tak to zwroc true
							break;
						}
					}
					if (overlaptrue == true) {    //jesli true to ma tam nie isc i wypisac w chmurce ze nie moze na to wejsc
						Cloud.textField.setMessageText("Nie moge na\nto wejsc!");
						stage.addActor(Cloud.textField);
						Timer.schedule(new Task() {
							@Override
							public void run() {
								Cloud.textField.remove();     //chmurka jest 3 sek a pozniej znika
							}
						}, 3);
					} else {
						image.setY(image.getY() + 1);    //w przeciwnym wypadku idz o 1 pixel wyzej
						updateActorBounds();
					}
				}
			}, delay, delay, MoveCountPixels);
		}

		if (gdzie.equals("na dol")) {

			Timer.schedule(new Task() {
				@Override
				public void run() {
					TryBounds.set(bounds.getX(), bounds.getY() - 1, 32, 32);
					for (int i = 0; i < iloscElem; i++) {
						if (TryBounds.overlaps(CantStand[i].bounds)) {
							overlaptrue = true;
							break;
						}
					}
					if (overlaptrue == true) {
						Cloud.textField.setMessageText("Nie moge na\nto wejsc!");
						stage.addActor(Cloud.textField);
						Timer.schedule(new Task() {
							@Override
							public void run() {
								Cloud.textField.remove();
							}
						}, 3);
					} else {
						image.setY(image.getY() - 1);
						updateActorBounds();
					}
				}
			}, delay, delay, MoveCountPixels);
		}
		if (gdzie.equals("w lewo")) {

			Timer.schedule(new Task() {
				@Override
				public void run() {
					TryBounds.set(bounds.getX() - 1, bounds.getY(), 32, 32);
					for (int i = 0; i < iloscElem; i++) {
						if (TryBounds.overlaps(CantStand[i].bounds)) {
							overlaptrue = true;
							break;
						}
					}
					if (overlaptrue == true) {
						Cloud.textField.setMessageText("Nie moge na\nto wejsc!");
						stage.addActor(Cloud.textField);
						Timer.schedule(new Task() {
							@Override
							public void run() {
								Cloud.textField.remove();
							}
						}, 3);
					} else {
						image.setX(image.getX() - 1);
						updateActorBounds();
					}
				}
			}, delay, delay, MoveCountPixels);
		}
		if (gdzie.equals("w prawo")) {

			Timer.schedule(new Task() {
				@Override
				public void run() {
					TryBounds.set(bounds.getX() + 1, bounds.getY(), 32, 32);
					for (int i = 0; i < iloscElem; i++) {
						if (TryBounds.overlaps(CantStand[i].bounds)) {
							overlaptrue = true;
							break;
						}
					}
					if (overlaptrue == true) {
						Cloud.textField.setMessageText("Nie moge na\nto wejsc!");
						stage.addActor(Cloud.textField);
						Timer.schedule(new Task() {
							@Override
							public void run() {
								Cloud.textField.remove();
							}
						}, 3);
					} else {
						image.setX(image.getX() + 1);
						updateActorBounds();
					}
				}
			}, delay, delay, MoveCountPixels);

		}
	}

	public void updateActorBounds() {
		bounds.set(image.getX(), image.getY(), 32, 32);
		Cloud.setPosition(bounds.getX() + 35, bounds.getY() + 20);
	}

	public void updateStatistics() {
		statistics[0].textField.setMessageText(statistics[0].getStatistic());
		statistics[1].textField.setMessageText(statistics[1].getStatistic());
		statistics[2].textField.setMessageText(statistics[2].getStatistic());
		statistics[3].textField.setMessageText(statistics[3].getStatistic());
	}

	public void init(int X, int Y) {
		TryBounds = new Rectangle();
		Cloud = new cloud(50, X + 35, Y + 20);
		statistics = new Statistics[4];
		statistics[0] = new Statistics(10, 135, 688);
		statistics[0].textField.setMessageText(statistics[0].getStatistic());
		statistics[1] = new Statistics(10, 165, 688);
		statistics[1].textField.setMessageText(statistics[1].getStatistic());
		statistics[2] = new Statistics(10, 193, 688);
		statistics[2].textField.setMessageText(statistics[2].getStatistic());
		statistics[3] = new Statistics(10, 223, 688);
		statistics[3].textField.setMessageText(statistics[3].getStatistic());
		stage.addActor(statistics[0].textField);
		stage.addActor(statistics[1].textField);
		stage.addActor(statistics[2].textField);
		stage.addActor(statistics[3].textField);
	}
}