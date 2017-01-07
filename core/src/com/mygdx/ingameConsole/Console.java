package com.mygdx.ingameConsole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.ingameConsole.Console;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;







///////////////////////////////////////////////////////////////
///////YOU ONLY NEED TO CREATE OBJECT OF THIS CLASS////////////
///////THEN ADD OBJECT.textFieldStyle TO YOUR STAGE////////////
///////////////////////////////////////////////////////////////
///////OBJECT.GetText RETURNS LAST STRING IN CONSOLE///////////
///////////////////////////////////////////////////////////////











public class Console {
	private String[] TableOfStrings;   //tablica do przechowywania wpisanych stringow
	public String LastSentenceInConsole;  //ostatni string w konsoli
	private TextFieldStyle textFieldStyle; 
	private BitmapFont bitmapFont;
	public TextField textField;
	public Boolean phraseEntereddlaNPC = false;
	public Boolean EnterClickedforNPC = false;
	
	public boolean PhraseEntered = false;   //zmienna ktora stwierdza czy cos zostalo wpisane
	private int countIN = 0;   //licznik do tablicy aby wyswietlalo ostatnie wpisane stringi
	private int countOUT = 0;  // to samo co up
	
	private FreeTypeFontGenerator generator;
	private FreeTypeFontParameter parameter;
	
	
	public Console( int WIDTH , float X , float Y, int size) {
		bitMapFontInit(size);
		textFieldStyleInit();
		textFieldInit( WIDTH , X , Y);
		setListener();
		TableOfStrings = new String[100];
		
	}

	public String GetText() {
		return LastSentenceInConsole;
	}

	public void setListener() {
		textField.addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keycode) {

				if (keycode == Input.Keys.ENTER) {
					textField.cut();
					if (textField.getText().length()>0) LastSentenceInConsole = textField.getText();
					if (textField.getText().length()>0) TableOfStrings[countIN] = textField.getText();
					if (textField.getText().length()>0) countIN++;
					
					if (countIN>99) countIN = 0;
					
					PhraseEntered = true;
					phraseEntereddlaNPC = true;
					EnterClickedforNPC = true;
					textField.selectAll();
					textField.cut();
					countOUT = countIN;
				
				}
				return false;
			}
		});
		
		
		textField.addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keycode)
			{  if (keycode == Input.Keys.UP) {
				if (countOUT-1 >=0) countOUT--;
				
				if (keycode == Input.Keys.UP) {
					textField.setText(TableOfStrings[countOUT]);
					textField.setCursorPosition(TableOfStrings[countOUT].length());
				}
				
			}
				return false;
			}
		});
		
		
		textField.addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keycode)
			{  if (keycode == Input.Keys.DOWN) {
				if (countOUT+1 < countIN) countOUT++;
				
				if (keycode == Input.Keys.DOWN) {
					textField.setText(TableOfStrings[countOUT]);
					textField.setCursorPosition(TableOfStrings[countOUT].length());
				}
			}
				return false;
			}
		});
	}
	
	
	
	

	//////////////////////////////////////////////////////////////
	//////////// JUST INIT ///////////// DONT READ LOWER /////////
	//////////////////////////////////////////////////////////////

	private void textFieldStyleInit() {
		textFieldStyle = new TextFieldStyle();
		textFieldStyle.fontColor = Color.WHITE;
		textFieldStyle.font = bitmapFont;
	}

	public void textFieldInit( int WIDTH, float X , float Y ) {
		textField = new TextField("", textFieldStyle);
		textField.setMessageText("...Wpisz co mam zrobic...");
		textField.setWidth(WIDTH);
		textField.setX(X);
		textField.setY(Y);
		textField.setClipboard(Gdx.app.getClipboard());
	}
	

	public void bitMapFontInit(int size) {
		bitmapFont = new BitmapFont();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("TimesNewRoman.ttf"));
		parameter = new FreeTypeFontParameter();
		parameter.size = size;
		bitmapFont = generator.generateFont(parameter);
		generator.dispose();
	}
	
	public void setPosition(float X, float Y) {
		textField.setPosition(X, Y);
	}

}
