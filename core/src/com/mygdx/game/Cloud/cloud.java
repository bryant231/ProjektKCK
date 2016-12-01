package com.mygdx.game.Cloud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

public class cloud {  //chatbox
	
	private TextFieldStyle textFieldStyle; 
	private BitmapFont bitmapFont;
	public TextField textField;
	public cloud( int WIDTH , float X , float Y) {
		bitMapFontInit();
		textFieldStyleInit();
		textFieldInit( WIDTH , X , Y);
		
	}
	
	private void textFieldStyleInit() {
		textFieldStyle = new TextFieldStyle();
		textFieldStyle.fontColor = Color.WHITE;
		textFieldStyle.font = bitmapFont;
	}

	public void textFieldInit( int WIDTH, float X , float Y ) {
		textField = new TextField("", textFieldStyle);
		textField.setMessageText("");
		textField.setWidth(WIDTH);
		textField.setX(X);
		textField.setY(Y);
		textField.setDebug(false);
	}
	public void bitMapFontInit() {
		bitmapFont = new BitmapFont();
		
	}
	public void setPosition(float X, float Y) {
		textField.setX(X);
		textField.setY(Y);
	}
	public void setMessage(String wiadomosc) {
		textField.setMessageText(wiadomosc);
	}
}