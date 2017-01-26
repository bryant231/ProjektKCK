package com.mygdx.screens;
 
import java.io.IOException;
 
import com.mygdx.game.ProjektKCK;
import com.mygdx.game.Buttons.AbstractButton;
import com.mygdx.game.actors.Actors;
import com.mygdx.game.actors.MainCharacter;
import com.mygdx.game.actors.StartowyNPC;
import com.mygdx.game.parserCYK.Parserv3;
import com.mygdx.game.parserCYK.ZwrocDoScreen;
import com.mygdx.ingameConsole.Console;
import com.mygdx.game.actors.Enemy;
import com.mygdx.game.actors.Enemy2;
import com.mygdx.game.actors.Enemy3;
 
public class TutorialScreen extends AbstractScreen {
	
    private Console console;
    public int ilosc_elemt_w_tablicy_przeszkod = 66; // tworzymy tablice
                                                    // przeszkod taka duzo ile
                                                    // jest elementow na ktore
                                                    // nie mozna wejsc w grze
    private MainCharacter mainCharacter;
    public Actors layoutconsole; // dodajemy layout konsoli
    public Actors statslayout; // dodajemy layout statystyk
    public Actors map; // mapa danej planszy
    public Actors map2;
    public AbstractButton CantStand[]; // tablica w ktorej sa obiekty do kolizji
    public Boolean znikajacyPrzeciwnik[];
    public Parserv3 Parser1;
    public StartowyNPC npc1;
    public StartowyNPC npc0;
    public Enemy enemy1;
    public Enemy2 enemy2;
    public Enemy3 enemy3;
 
    public TutorialScreen(ProjektKCK game) throws IOException {
        super(game);
        create();
    }
 
    public void create() throws IOException {
        try {
            Parser1 = new Parserv3();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        znikajacyPrzeciwnik = new Boolean[3];
        znikajacyPrzeciwnik[0] = false;
        znikajacyPrzeciwnik[1] = false;
        znikajacyPrzeciwnik[2] = false;
        CantStand = new AbstractButton[ilosc_elemt_w_tablicy_przeszkod];
        CantStandInit();
        for (int i = 0; i < ilosc_elemt_w_tablicy_przeszkod; i++) {
            stage.addActor(CantStand[i].button);
        }
        layoutconsole = new Actors(0, 0, "Layout\\layoutconsole.png");
        statslayout = new Actors(0, 668, "Layout\\statslayout.png");
        map = new Actors(0, 0, "Maps\\tutorial\\1.jpg");
        map2 = new Actors(0, 0, "Maps\\tutorial\\2.png");
        console = new Console(600, 270, 25, 1);
        mainCharacter = new MainCharacter(432, 450, "CharacterMovement\\walking e0000.png", stage);
        npc0 = new StartowyNPC("Pierwszy", 550, 512, "StarterNPC\\stoppped_0001.png", this.stage, this.console,
                this.Parser1, 450, 450, 200, 140);
        npc1 = new StartowyNPC("Startowy", 1650, 560, "NPCMovement\\stopped0000.png", this.stage, this.console,
                this.Parser1, 1600, 500, 200, 140);
        enemy1 = new Enemy("Wrog", 2400, 600, "OrcMovement\\stopped0000.png", this.stage, this.console, this.Parser1,
                2350, 550, 200, 140);
        enemy2 = new Enemy2("Wrog2", 3000, 462, "OgreEnemy\\stopped0002.png", this.stage, this.console, this.Parser1,
                2950, 412, 200, 140);
        enemy3 = new Enemy3("Wrog3", 850, 440, "SpiderEnemy\\stopped_0000.png", this.stage, this.console, this.Parser1,
                800, 390, 200, 140);
 
        stage.addActor(map.image);
        stage.addActor(map2.image);
        stage.addActor(npc1.image);
        stage.addActor(npc0.image);
        stage.addActor(enemy1.image);
        stage.addActor(enemy2.image);
        stage.addActor(enemy3.image);
        stage.addActor(mainCharacter.image);
        stage.addActor(layoutconsole.image);
        stage.addActor(console.textField);
        stage.addActor(statslayout.image);
        stage.addActor(mainCharacter.statistics[0].textField);
        stage.addActor(mainCharacter.statistics[1].textField);
        stage.addActor(mainCharacter.statistics[2].textField);
        stage.addActor(mainCharacter.statistics[3].textField);
        stage.act();
 
    }
 
    @Override
    public void render(float delta) {
        super.render(delta);
        whatToDo();
        CanTalkWithNpc();
        refreshCamera();
 
        stage.act();
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }
 
    private void whatToDo() {
        if (console.PhraseEntered == true) {
 
            String fraza = new String();
            fraza = console.GetText();
 
            // Tablica zwracana przez parser
            ZwrocDoScreen wynik = new ZwrocDoScreen();
            fraza = fraza.toLowerCase();
            try {
                wynik = Parserv3.Dzialaj(fraza);
            } catch (IOException e) {
                System.out.println("Popsules nasza gre");
            }
 
            // Wyswietla w konsoli co zwraca parser (czy fraza jest zdaniem czy
            // nie).
            // Jesli jest to wyswietla tez jego typ
            // System.out.println(wynik[0]);
 
            if (wynik.PodajRozmiarLista_co_zwracam() == 0) {
                mainCharacter.Speak("Nie rozumiem Cie!");
            } else {
 
                switch (wynik.PodajElementLista_co_zwracam(0)) {
                case "Z_Idz":
                    // System.out.println(wynik.PodajCzy_liczba_kratek());
                    if (wynik.PodajCzy_liczba_kratek() == false) {
                        mainCharacter.move(wynik.PodajElementLista_co_zwracam(1), CantStand,
                                ilosc_elemt_w_tablicy_przeszkod);
                    } else {
                        // System.out.println(wynik.PodajLiczba_kratek());
                        mainCharacter.moveBy(wynik.PodajElementLista_co_zwracam(1), wynik.PodajLiczba_kratek(),
                                CantStand, ilosc_elemt_w_tablicy_przeszkod);
                    }
                    break;
                case "Z_Atakuj":
                    mainCharacter.SetCanAttack(true);
                    CanAttackNpc(wynik);
                    break;
                case "Z_Kom":
                    if (wynik.PodajCzyLiczba() == false) {
                        mainCharacter.Speak(wynik.PodajElementLista_co_zwracam(1));
                    } else {
                        mainCharacter.Speak(Integer.toString(wynik.PodajLiczba()));
                    }
                    break;
                }
            }
 
            console.PhraseEntered = false;
 
        }
 
    }
 
    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
 
    }
 
    public void CantStandInit() {
 
        CantStand[0] = new AbstractButton(289, 320, 32, 32);
        CantStand[1] = new AbstractButton(609, 320, 32, 32);
        CantStand[2] = new AbstractButton(289, 610, 32, 32);
        CantStand[3] = new AbstractButton(609, 610, 32, 32);
        CantStand[4] = new AbstractButton(-10, 0, 10, 1024);
        CantStand[5] = new AbstractButton(3200, 0, 10, 1024);
        CantStand[6] = new AbstractButton(0, -10, 3200, 10);
        CantStand[7] = new AbstractButton(0, 1024, 3200, 10);
        CantStand[8] = new AbstractButton(579, 506, 32, 94); /*NPC1*/
        CantStand[9] = new AbstractButton(1680, 570, 35, 75); /*NPC2-kowal*/
        CantStand[10] = new AbstractButton(1605, 615, 200, 210);
        CantStand[11] = new AbstractButton(1615, 515, 32, 32);
        CantStand[12] = new AbstractButton(1775, 515, 32, 32);
        CantStand[13] = new AbstractButton(2976, 540, 32, 32);
        CantStand[14] = new AbstractButton(2976, 388, 32, 32);
        CantStand[15] = new AbstractButton(2912, 356, 32, 32);
        CantStand[16] = new AbstractButton(0, 0, 130, 170);
        CantStand[17] = new AbstractButton(0, 845, 130, 300);
        CantStand[18] = new AbstractButton(867, 517, 32, 32);
        CantStand[19] = new AbstractButton(835, 549, 32, 32);
        CantStand[20] = new AbstractButton(803, 581, 32, 32);
        CantStand[21] = new AbstractButton(865, 386, 32, 32);
        CantStand[22] = new AbstractButton(833, 354, 32, 32);
        CantStand[23] = new AbstractButton(801, 322, 32, 32);
        CantStand[24] = new AbstractButton(161, 259, 64, 64);
        CantStand[25] = new AbstractButton(225, 163, 64, 64);
        CantStand[26] = new AbstractButton(353, 163, 64, 64);
        CantStand[27] = new AbstractButton(289, 131, 64, 64);
        CantStand[28] = new AbstractButton(417, 131, 64, 64);
        CantStand[29] = new AbstractButton(481, 131, 64, 64);
        CantStand[30] = new AbstractButton(609, 131, 64, 64);
        CantStand[31] = new AbstractButton(737, 131, 64, 64);
        CantStand[32] = new AbstractButton(545, 99, 64, 64);
        CantStand[33] = new AbstractButton(673, 99, 64, 64);
        CantStand[34] = new AbstractButton(161, 647, 64, 64);
        CantStand[35] = new AbstractButton(225, 711, 64, 64);
        CantStand[36] = new AbstractButton(289, 711, 64, 64);
        CantStand[37] = new AbstractButton(353, 743, 64, 64);
        CantStand[38] = new AbstractButton(417, 775, 64, 64);
        CantStand[39] = new AbstractButton(481, 775, 64, 64);
        CantStand[40] = new AbstractButton(609, 775, 64, 64);
        CantStand[41] = new AbstractButton(673, 775, 64, 64);
        CantStand[42] = new AbstractButton(545, 743, 64, 64);
        CantStand[43] = new AbstractButton(2303, 550, 64, 64);
        CantStand[44] = new AbstractButton(2562, 550, 64, 64);
        CantStand[45] = new AbstractButton(2303, 780, 64, 64);
        CantStand[46] = new AbstractButton(2562, 710, 64, 64);
        CantStand[47] = new AbstractButton(2239, 716, 64, 64);
        CantStand[48] = new AbstractButton(2626, 774, 64, 64);
        CantStand[49] = new AbstractButton(2271, 876, 64, 64);
        CantStand[50] = new AbstractButton(2335, 940, 64, 64);
        CantStand[51] = new AbstractButton(2399, 972, 192, 64);
        CantStand[52] = new AbstractButton(2591, 940, 64, 64);
        CantStand[53] = new AbstractButton(2978, 732, 190, 32);
        CantStand[54] = new AbstractButton(3168, 764, 32, 32);
        CantStand[55] = new AbstractButton(3008, 228, 200, 32);
        CantStand[56] = new AbstractButton(2976, 260, 32, 32);
        CantStand[57] = new AbstractButton(2944, 292, 32, 32);
        CantStand[58] = new AbstractButton(2912, 356, 32, 32);
        CantStand[58] = new AbstractButton(3040, 196, 32, 32);
        CantStand[59] = new AbstractButton(2946, 700, 32, 32);
        CantStand[60] = new AbstractButton(2946, 604, 32, 32);
        CantStand[61] = new AbstractButton(2912, 538, 32, 32);
        CantStand[62] = new AbstractButton(2912, 668, 32, 32);
        CantStand[63] = new AbstractButton(870, 450, 64, 64); /* do usunięcia pole po enemy3 (droga) */
        CantStand[64] = new AbstractButton(3020, 472, 64, 64); /* do usunięcia pole po enemy2 (prawa strona mapy) */
        CantStand[65] = new AbstractButton(2416, 600, 64, 64); /* do usunięcia pole po enemy1 (obok kowala) */
        
    }
 
    public void refreshCamera() {
        camera.position.set(mainCharacter.image.getX() + 50, mainCharacter.image.getY(), 0);
        statslayout.image.setX(mainCharacter.image.getX() - 470);
        statslayout.image.setY(mainCharacter.image.getY() + 290);
        console.textField.setX(mainCharacter.image.getX() - 260);
        console.textField.setY(mainCharacter.image.getY() - 370);
        layoutconsole.image.setX(mainCharacter.image.getX() - 480);
        layoutconsole.image.setY(mainCharacter.image.getY() - 390);
        mainCharacter.statsPositionUpdate();
    }
 
    public void CanTalkWithNpc() {
        npc1.collisionCheck(mainCharacter.bounds);
        npc0.collisionCheck(mainCharacter.bounds);
        npc1.rozmowa(enemy3,mainCharacter);
        npc0.rozmowa(enemy3,mainCharacter);
        if (enemy1.Defeated == false)
            enemy1.displayDamage();
        if (enemy2.Defeated == false)
            enemy2.displayDamage();
        if (enemy3.Defeated == false)
            enemy3.displayDamage();
        
        if (enemy1.Defeated == true){
            CantStand[65].bounds.set(0, 0, 0, 0);
            CantStand[65].button.setX(0);
            CantStand[65].button.setY(0);
        }
            
        if (enemy2.Defeated == true){
        	CantStand[64].bounds.set(0, 0, 0, 0);
            CantStand[64].button.setX(0);
            CantStand[64].button.setY(0);
        }
            
        if (enemy3.Defeated == true){
        	CantStand[63].bounds.set(0, 0, 0, 0);
            CantStand[63].button.setX(0);
            CantStand[63].button.setY(0);
        }
            
        
    }
 
    public void CanAttackNpc(ZwrocDoScreen wynik){
        enemy1.collisionCheck(mainCharacter.bounds);
        enemy2.collisionCheck(mainCharacter.bounds);
        enemy3.collisionCheck(mainCharacter.bounds);
        
        if(mainCharacter.GetCanAttack() == true){
            if (enemy1.isMainCharacterInside() == true) {
            enemy1.SetUsedWord(wynik.PodajElementLista_co_zwracam(1));
            enemy1.IsHitbyMC(mainCharacter);
            }
            if (enemy2.isMainCharacterInside() == true) {
            enemy2.SetUsedWord(wynik.PodajElementLista_co_zwracam(1));
            enemy2.IsHitbyMC(mainCharacter);
            }
            if (enemy3.isMainCharacterInside() == true) {
            enemy3.SetUsedWord(wynik.PodajElementLista_co_zwracam(1));
            enemy3.IsHitbyMC(mainCharacter);
            }
        }
    }
 
}