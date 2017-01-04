package com.mygdx.game.Buttons;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;

public class AbstractButton{
	public Rectangle bounds;   //bounds potrzebne do wykrywania kolizji
	public Button button;
	public AbstractButton(float XX,float YY , int WIDTHH , int HEIGHTT) {
		bounds = new Rectangle();
		bounds.set(XX, YY, WIDTHH, HEIGHTT);
		button = new Button(new ButtonStyle());
		button.setX(XX);
		button.setY(YY);
		button.setWidth(WIDTHH);
		button.setHeight(HEIGHTT);
		bounds.set(button.getX(), button.getY(), button.getWidth(), button.getHeight());
		button.setDebug(true);
	}
	
}