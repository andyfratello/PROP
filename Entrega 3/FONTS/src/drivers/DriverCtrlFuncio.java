package drivers;

import main.domain.classes.functions.Interpret;
import main.domain.classes.types.Hexadecimal;
import main.domain.controllers.CtrlFuncions;
import main.domain.classes.types.Referencia;
import main.domain.classes.types.Binari;
import main.domain.classes.types.Pair;
import main.domain.classes.Cela;
import main.domain.classes.Full;
import java.util.Scanner;

public class DriverCtrlFuncio
{
	public Scanner in;
	private final Full _full;
	
	public DriverCtrlFuncio()
	{
		CtrlFuncions _cF = new CtrlFuncions();
		_full = _cF.GetFull();
		in = new Scanner(System.in);
	}

	private Pair<Integer,Integer> llegitCoords()
	{
		String c = in.nextLine();
		String[] split = c.split("(?<=\\D)(?=\\d)");	// Dividir en lletres i digits

		if (split.length != 2) throw new RuntimeException("Coordenades mal definides: " + c);

		int columna = Interpret.HexavigesimalToInteger(split[0]);
		int fila = Integer.parseInt(split[1]) - 1;

		return new Pair<> (columna, fila);
	}

	/** L'usuari indica la columna, la fila i el valor que li vol assignar */
	private void ModificaCela()
	{
		System.out.println("Introdueix la coordenada que es vol modificar (ex:A1)");
		Pair<Integer,Integer> coord = llegitCoords();
		System.out.println("Introdueix nou valor:");
		String valor = in.nextLine();
		if (_full.ModificaCela(coord.first(), coord.second(), valor)) System.out.println("S'ha sobreescrit una cel·la");

		System.out.println("Cela a la posició: " + (char) ('A' + coord.first()) + (coord.second()+1) + " Ha canviat a " + valor);
	}

	private void TestGetInfoCela()
	{
		System.out.println("Introdueix la coordenada que es vol modificar (ex:A1)");
		Pair<Integer,Integer> coord = llegitCoords();
		Cela c = _full.GetCela(coord.first(), coord.second());
		Referencia r = _full.GetReferencia(coord.first(), coord.second());
		System.out.println("-- " + r + " -> " + c + " --");
		System.out.println("-- CELA --");
		System.out.println("Tipus: " + c.GetTipus());
		System.out.println("Funcio: " + c.GetFuncio() + " " + c.EsFuncio());
		System.out.println("vUsuari: " + c.GetValorUsuari());
		System.out.println("vReal: " + c.GetValorReal());
		System.out.println("-- REFERENCIA --");
		System.out.println("Pos: " + r.GetPos());
		System.out.print  ("Referenciada per: ");
		r.GetReferenciadaPer().forEach(ref -> System.out.print(ref + " "));
		System.out.println("Te Cicle: " + r.teCicle());
		System.out.print  ("Conjunt de Updates");
		r.Update().forEach( p -> System.out.print("," + p));
		System.out.println();
	}

	/** Omple el full a diferents posicions amb numeros aleatoris */
	private void TestOmplirFullNumeros() {
		System.out.println("Numero de cel·les a afegir: ");
		int n = in.nextInt();
		int cols = _full.GetNumCol();
		int fils = _full.GetNumFil();
		for (int i = 0; i < n; i++)
		{
			Pair<Integer,Integer> pos = new Pair<>((int)(Math.random()*cols), (int)(Math.random()*fils));
			_full.ModificaCela(pos.first(), pos.second(), Double.toString((double)Math.round(Math.random()*200*100d) / 100d) );
		}
		System.out.println("Full poblat");
	}

	/** Omple el full a diferents posicions amb numeros Hexadecimals aleatoris */
	private void TestOmplirFullHex() {
		System.out.println("Numero de cel·les a afegir: ");
		int n = in.nextInt();
		int cols = _full.GetNumCol();
		int fils = _full.GetNumFil();
		for (int i = 0; i < n; i++)
		{
			Pair<Integer,Integer> pos = new Pair<>((int)(Math.random()*cols), (int)(Math.random()*fils));
			_full.ModificaCela(pos.first(), pos.second(), new Hexadecimal((int)(Math.random()*100)).toString() );
		}
		System.out.println("Full poblat");
	}

	/** Omple el full a diferents posicions amb numeros Binaris aleatoris */
	private void TestOmplirFullBin() {
		System.out.println("Numero de cel·les a afegir: ");
		int n = in.nextInt();
		int cols = _full.GetNumCol();
		int fils = _full.GetNumFil();
		for (int i = 0; i < n; i++)
		{
			Pair<Integer,Integer> pos = new Pair<>((int)(Math.random()*cols), (int)(Math.random()*fils));
			_full.ModificaCela(pos.first(), pos.second(), new Binari((int)(Math.random()*10)).toString() );
		}
		System.out.println("Full poblat");
	}

	/** Omple el full a diferents posicions amb Strings introduides per l'usuari */
	private void TestOmplirFullString() {
		System.out.println("Numero de cel·les a afegir: ");
		int n = in.nextInt();
		int cols = _full.GetNumCol();
		int fils = _full.GetNumFil();
		for (int i = 0; i < n; i++)
		{
			Pair<Integer,Integer> pos = new Pair<>((int)(Math.random()*cols), (int)(Math.random()*fils));
			System.out.println("String a introduir: ");
			String v = in.nextLine();
			_full.ModificaCela(pos.first(), pos.second(), v);
		}
		System.out.println("Full poblat");
	}


	private void PrintFull() { _full.PrintFull(); }

public static void main(String[] args)
{
	DriverCtrlFuncio driver = new DriverCtrlFuncio();

	opcions();
	String input = driver.in.nextLine();
	while (!input.equals("0") )
	{
		try {
			System.out.println("Input: " + input);
			switch (input) {
				case "1":
					driver.PrintFull();
					break;
				case "2":
					driver.ModificaCela();
					break;
				case "3":
					driver.TestGetInfoCela();
					break;
				case "4":
					driver.TestOmplirFullNumeros();
					break;
				case "5":
					driver.TestOmplirFullHex();
					break;
				case "6":
					driver.TestOmplirFullBin();
					break;
				case "7":
					driver.TestOmplirFullString();
					break;
				default:
					System.out.println("Opcio desconeguda");
			}
		} catch (Exception e) { System.out.println("[#ERROR] " + e.getMessage()); }
		finally {
			opcions();
			input = driver.in.nextLine();
		}
	}
}

	private static void opcions()
	{
		System.out.println("0 | Quit");
		System.out.println("1 | Print Full");
		System.out.println("2 | Modifica Cela");
		System.out.println("3 | Get informació de Cela");
		System.out.println("4 | Omplir full de valors numerics");
		System.out.println("5 | Omplir full de valors Hexadecimals");
		System.out.println("6 | Omplir full de valors Binaris");
		System.out.println("7 | Omplir full de valors String");
	}

}
