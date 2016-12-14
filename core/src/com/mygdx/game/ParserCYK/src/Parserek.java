import java.io.*;  

public class Parserek {

	public static String CheckType(String a)
	{
		String temp; temp = a;
		if (	
				temp.contains("A") ||
				temp.contains("E") ||
				temp.contains("I") ||
				temp.contains("O") ||
				temp.contains("U") ||
				temp.contains("Y")
			)
			{temp = "A";}
		else 
			{temp = "B";}
		return temp;
	}

	
	public static String Przeparsuj(String tablica[][], int slowa)
	{
		int koordynatX; int koordynatY;		
		int Pole1x;	int Pole1y;	int Pole2x;	int Pole2y;	int counter;
		koordynatX = 0; koordynatY = 0;
		
		//Inicjalizacja tablicy - bez tego ryzykujemy nulle w tablicy
		for (koordynatY = 1; koordynatY<slowa; koordynatY++)
		{
			for (koordynatX = 0; koordynatX<slowa; koordynatX++)
			{
				tablica[koordynatY][koordynatX]="";
			}
		}
	
		
		for (koordynatY = 1; koordynatY<slowa; koordynatY++)
		{
			for (koordynatX = 0; koordynatX<slowa; koordynatX++)
			{

				if (koordynatX<=slowa-koordynatY) {
					System.out.println("Wchodzimy w komorke " + koordynatX + koordynatY);
					Pole1x = koordynatX;
					Pole1y = 0;
					Pole2x = koordynatX + 1;
					Pole2y = koordynatY - 1;
					counter = 1;
					while (Pole1y < koordynatY && Pole2x < slowa) {
						System.out.println(
								"Probujemy parsowac komorke " + koordynatX + koordynatY + " po raz " + counter);
						System.out.println("Sprawdzamy komórki " + Pole1x + Pole1y + " oraz " + Pole2x + Pole2y);

						tablica[koordynatX][koordynatY] = SprawdzGramatyke(tablica, Pole1x, Pole1y, Pole2x, Pole2y);
						counter++;
						Pole1x = Pole1x;
						Pole1y = Pole1y + 1;
						Pole2x = Pole2x + 1;
						Pole2y = Pole2y - 1;
					}
					System.out.println("W komórce " + koordynatX + koordynatY + " znajduje sie "
							+ tablica[koordynatX][koordynatY]);
				}
		
			}
		}
		
		int temp; temp = slowa-1;
		
		System.out.println("Zobaczmy zatem co jest w komórce " + temp + 0 + " a tam jest " + tablica[slowa-1][0]);
		if (tablica[temp][0].contains("S"))
			return "Zdanie jest w gramatyce";			
		else return "Zdania nie ma w gramatyce";
	}
	
	public static String SprawdzGramatyke(String tabliczka[][], int Pole1x, int Pole1y, int Pole2x, int Pole2y)
	{
		String ExitString = "";
		//System.out.println("Sprawdzamy gramatykę");
		if (tabliczka[Pole1x][Pole1y].contains("A") && tabliczka[Pole2x][Pole2y].contains("B")
				|| tabliczka[Pole1x][Pole1y].contains("B") && tabliczka[Pole2x][Pole2y].contains("A"))
		{
			ExitString = ExitString + "S";
		}
		else if (tabliczka[Pole1x][Pole1y]=="S" && tabliczka[Pole2x][Pole2y].contains("B")
				|| tabliczka[Pole1x][Pole1y].contains("B") && tabliczka[Pole2x][Pole2y]=="S" )
		{
			ExitString = ExitString + "C";
		}
		if (tabliczka[Pole1x][Pole1y].contains("A") && tabliczka[Pole2x][Pole2y]=="C"
			|| tabliczka[Pole1x][Pole1y]=="C" && tabliczka[Pole2x][Pole2y].contains("A"))
		{
			ExitString = ExitString + "S";
		}
		return ExitString;
	}
	
public static void main(String args[])
	{  

		//Wprowadzanie zdania
		String phrase; phrase = ""; System.out.println("Input phrase");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		try
			{phrase=br.readLine();}
		catch(Exception e)
			{System.out.println(e);}

		System.out.println("The sentence is: " + phrase);
		
		//Dzielenie zdania na tokeny
		String[] result = phrase.split("\\s+");
		
		//Pokazanie co mamy
		for (int x=0; x<result.length; x++)
			{
				System.out.println(result[x]);
			}

		//Stworzenie tablicy rozmiaru 5x5
		String Symbole[][] = new String[result.length][result.length];
		for (int i = 0; i <result.length; i++)
			{
				Symbole[i][0] = CheckType(result[i]);
				System.out.println(result[i] + " is of type " + Symbole[i][0]);
			}
		
		System.out.println("Dlugosc zdania: " + result.length);
		System.out.println("Badany format pokazuje koordynaty XY ");
		String CzyJest = Przeparsuj(Symbole,result.length);
		System.out.println(CzyJest);
	}
}
