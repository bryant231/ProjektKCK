/*		
		for (koordynatY = 1; koordynatY < slowa; koordynatY++)
		{
			for (koordynatX = 0; koordynatX <= koordynatY; koordynatX++)
			{
//				int Pole1x;	int Pole1y;	int Pole2x;	int Pole2y;
				
				System.out.println("Wchodzimy w komorke " + koordynatY + koordynatX);
					if (koordynatY<slowa-koordynatX)
					{	
						System.out.println("Probujemy parsowac komorke " + koordynatX + koordynatY);
						Pole1y = 0;
						Pole1x = koordynatX;
						Pole2y = koordynatY-1;
						Pole2x = koordynatX+1;
						
						while(Pole1y < koordynatY)
						{
							System.out.println("Sprawdzamy komórki " + Pole1y + Pole1x + " oraz " + Pole2y + Pole2x);
//							System.out.println("");
							tablica[koordynatX][koordynatY] = SprawdzGramatyke(tablica,Pole1y, Pole1x, Pole2y, Pole2x);
							System.out.println("Wynik: " + tablica[koordynatX][koordynatY]);
							Pole1y = Pole1y+1;  Pole1x = Pole1x;
							Pole2x = Pole2x+1;	Pole2y = Pole2y-1;	
							//counter=counter+1;
						}
					}
			}
		}
	*/	


		//Niech koordynatX = koordynat w poziomie, koordynatY = koordynat w pionie
		//Koordynaty dla tabeli 5-słowowej
		//[00][10][20][30][40] - tutaj beda wprowadzone slowa
		//[01][11][21][31][41]
		//[02][12][22][32][42]
		//[03][13][23][33][43]
		//[04][14][24][34][44]
		//
		//To co zaplanowane jest to żeby robić:
		//[00][10][20][30][40]
		//[01][11][21][31]
		//[02][12][22]
		//[03][13]
		//[04]				
				
		//Interesuje nas wynik z pola [04] - powinien być S
		