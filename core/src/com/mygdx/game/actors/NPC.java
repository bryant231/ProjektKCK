package com.mygdx.game.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Buttons.AbstractButton;

public class NPC extends Actors{
	
	private Stage stage;   
	private AbstractButton button;
	private boolean CanTalk = false;
	
	public NPC(int X, int Y, String sciezka , Stage stage) {
		super(X, Y, sciezka);
		this.stage = stage;
		button = new AbstractButton(1600,600, 200, 140);
		button.button.setDebug(true);
		stage.addActor(button.button);
	}
	
	public void collisionCheck(Rectangle rectangle) {
		if (button.bounds.overlaps(rectangle)) {
			this.CanTalk = true;
		}
		else
			this.CanTalk = false;
	}
}
