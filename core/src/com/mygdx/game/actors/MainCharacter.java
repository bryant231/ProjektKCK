package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Buttons.AbstractButton;
import com.mygdx.game.Cloud.Statistics;
import com.mygdx.game.Cloud.cloud;

public class MainCharacter extends Actors {
	private float delay = (float) 0.005; // ile opoznienia miedzy ruchami
	private int MoveCountPixels = 64; // o ile pixeli sie przesunie w ciagu calego 1 cyklu ruchu
	public Rectangle TryBounds; // koordynaty ktore sa o krok do przodu przed glowna postacia
	private cloud Cloud; // chatbox
	public Statistics[] statistics; // tablica[4] statystyk
	private Stage stage;
	public boolean overlaptrue = false; // flaga do kolizji
	Boolean CanAttack = false;
	int ruch = 0;

	
	public MainCharacter(int X, int Y, String sciezka, Stage stage) {
		super(X, Y, sciezka);
		this.stage = stage;
		init(X, Y);
	}

	public void move(String gdzie, AbstractButton[] CantStand, int iloscElem) {

		overlaptrue = false; // flage ustawiamy na nie bo glowna postac nie ma
								// kolizji

		if (gdzie.equals("gora")) { // jesli ktos wpisal do gory w konsoli
			Timer.schedule(new Task() { // timer + zadanie
				@Override
				public void run() {
					TryBounds.set(bounds.getX(), bounds.getY() + 1, 32, 32); // dodajemy
																				// do
																				// naszej
																				// zmiennej
																				// ktora
																				// idzie
																				// przed
																				// romanem
																				// o
																				// 1
																				// do
																				// gory
					for (int i = 0; i < iloscElem; i++) {
						if (TryBounds.overlaps(CantStand[i].bounds)) { // sprawdzamy
																		// czy
																		// ta
																		// zmienna
																		// ma z
																		// czyms
																		// kolizje
							overlaptrue = true; // jesli tak to zwroc true
							break;
						}
					}
					if (overlaptrue == true) { // jesli true to ma tam nie isc i
												// wypisac w chmurce ze nie moze
												// na to wejsc
						Cloud.textField.setMessageText("Nie moge na\nto wejsc!");
						stage.addActor(Cloud.textField);
						Timer.schedule(new Task() {
							@Override
							public void run() {
								Cloud.textField.remove(); // chmurka jest 3 sek
															// a pozniej znika
								
							}
						}, 3);
					} else {
						image.setY(image.getY() + 1); // w przeciwnym wypadku
														// idz o 1 pixel wyzej
						changePicture("CharacterMovement\\walking n000" + ruch + ".png");
						ruch++;
						if (ruch == 8)
							ruch = 0;
						updateActorBounds();
					}
				}
			}, delay, delay, MoveCountPixels);
		}

		if (gdzie.equals("dol")) {

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
						changePicture("CharacterMovement\\walking s000" + ruch + ".png");
						ruch++;
						if (ruch == 8)
							ruch = 0;

						updateActorBounds();
					}
				}
			}, delay, delay, MoveCountPixels);
		}
		if (gdzie.equals("lewo")) {

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
						changePicture("CharacterMovement\\walking w000" + ruch + ".png");
						ruch++;
						if (ruch == 8)
							ruch = 0;
						updateActorBounds();
					}
				}
			}, delay, delay, MoveCountPixels);
		}
		if (gdzie.equals("prawo")) {

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
						changePicture("CharacterMovement\\walking e000" + ruch + ".png");
						ruch++;
						if (ruch == 8)
							ruch = 0;
						updateActorBounds();
					}
				}
			}, delay, delay, MoveCountPixels);

		}
	}

	public void moveBy(String gdzie, int ile, AbstractButton[] CantStand, int iloscElem) {

		overlaptrue = false; // flage ustawiamy na nie bo glowna postac nie ma
								// kolizji

		MoveCountPixels = MoveCountPixels * ile;

		if (gdzie.equals("gora")) { // jesli ktos wpisal do gory w konsoli
			Timer.schedule(new Task() { // timer + zadanie
				@Override
				public void run() {
					TryBounds.set(bounds.getX(), bounds.getY() + 1, 32, 32); // dodajemy
																				// do
																				// naszej
																				// zmiennej
																				// ktora
																				// idzie
																				// przed
																				// romanem
																				// o
																				// 1
																				// do
																				// gory
					for (int i = 0; i < iloscElem; i++) {
						if (TryBounds.overlaps(CantStand[i].bounds)) { // sprawdzamy
																		// czy
																		// ta
																		// zmienna
																		// ma z
																		// czyms
																		// kolizje
							overlaptrue = true; // jesli tak to zwroc true
							break;
						}
					}
					if (overlaptrue == true) { // jesli true to ma tam nie isc i
												// wypisac w chmurce ze nie moze
												// na to wejsc
						Cloud.textField.setMessageText("Nie moge na\nto wejsc!");
						stage.addActor(Cloud.textField);
						Timer.schedule(new Task() {
							@Override
							public void run() {
								Cloud.textField.remove(); // chmurka jest 3 sek
															// a pozniej znika
							}
						}, 3);
					} else {
						image.setY(image.getY() + 1); // w przeciwnym wypadku
														// idz o 1 pixel wyzej
						changePicture("CharacterMovement\\walking n000" + ruch + ".png");
						ruch++;
						if (ruch == 8)
							ruch = 0;
						updateActorBounds();
					}
				}
			}, delay, delay, MoveCountPixels);
		}

		if (gdzie.equals("dol")) {

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
						changePicture("CharacterMovement\\walking s000" + ruch + ".png");
						ruch++;
						if (ruch == 8)
							ruch = 0;

						updateActorBounds();
					}
				}
			}, delay, delay, MoveCountPixels);
		}
		if (gdzie.equals("lewo")) {

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
						changePicture("CharacterMovement\\walking w000" + ruch + ".png");
						ruch++;
						if (ruch == 8)
							ruch = 0;
						updateActorBounds();
					}
				}
			}, delay, delay, MoveCountPixels);
		}
		if (gdzie.equals("prawo")) {

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
						changePicture("CharacterMovement\\walking e000" + ruch + ".png");
						ruch++;
						if (ruch == 8)
							ruch = 0;
						updateActorBounds();
					}
				}
			}, delay, delay, MoveCountPixels);

		}
		MoveCountPixels = 64;
	}

	public void SetCanAttack(Boolean a){
		CanAttack = a;
	}
			
	public Boolean GetCanAttack(){
		return CanAttack;
	}
	
	public void updateActorBounds() {
		bounds.set(image.getX() + 28, image.getY() + 19, 44, 56);
		Cloud.setPosition(bounds.getX() + 40 + 35, bounds.getY() + 19 + 44);
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
		statistics[0] = new Statistics(10, 0, 0);
		statistics[0].textField.setMessageText(statistics[0].getStatistic());
		statistics[1] = new Statistics(10, 0, 0);
		statistics[1].textField.setMessageText(statistics[1].getStatistic());
		statistics[2] = new Statistics(10, 0, 0);
		statistics[2].textField.setMessageText(statistics[2].getStatistic());
		statistics[3] = new Statistics(10, 0, 0);
		statistics[3].textField.setMessageText(statistics[3].getStatistic());
		bounds.set(image.getX() + 32, image.getY() + 20, 32, 32);
	}

	void changePicture(String sciezka) {
		texture = new Texture(Gdx.files.internal(sciezka));
		region.setRegion(texture);

	}

	public void statsPositionUpdate() {
		statistics[0].setPosition(image.getX() - 335, image.getY() + 312);
		statistics[1].setPosition(image.getX() - 305, image.getY() + 312);
		statistics[2].setPosition(image.getX() - 277, image.getY() + 312);
		statistics[3].setPosition(image.getX() - 247, image.getY() + 312);
	}

	public void Speak(String text) {
		cloud Cloud2 = new cloud(50, image.getX() + 35, image.getY() + 20);
		Cloud2.textField.setMessageText(text);
		stage.addActor(Cloud2.textField);
		Timer.schedule(new Task() {
			@Override
			public void run() {
				Cloud2.textField.remove();
			}
		}, 3);
	}
}