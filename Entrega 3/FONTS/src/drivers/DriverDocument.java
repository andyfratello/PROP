package drivers;

import main.domain.classes.Cela;
import main.domain.classes.Document;
import main.domain.classes.Full;
import main.domain.classes.types.Pair;
import main.domain.controllers.CtrlDocument;
import java.util.*;

/**
 * @author
 */
public class DriverDocument {
    private CtrlDocument cd = null;
    private Scanner in;

    public void testConstructora() {
        cd = new CtrlDocument();
        testCrearDocument();
        cd.crearFull();
    }

    /**
     * Retorna string en Hexavigesimal (base26 en caràcters) a integer
     * @paramString en base26 a caràcters
     * @return Integer
     * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
     */
    private static int hexavigesimalToInteger(String x) {
        int res = 0;
        for (int i = 0; i < x.length(); i++) {
            res *= 26;
            res += x.charAt(i) - 'A' + 1;
        }
        --res;
        return res;
    }

    /**
     * Retorna true si char x pot ser parsejat a Integer, false en cas contrari
     * @param x
     * @return Boolean
     * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)<p> Modificat per: <p> Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
     */
    private static boolean esInteger(char x) {
        if (x < '0' || x > '9') return false;
        return true;
    }

    private static Pair<Integer, Integer> llegirCoords() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int x, y;
        int i = 0;
        while (i < input.length() && !esInteger(input.charAt(i))) i++;
        String x_string = input.substring(0,i);
        String y_string = input.substring(i,input.length());
        x = hexavigesimalToInteger(x_string);
        y = Integer.parseInt(y_string, 10);
        return new Pair<Integer, Integer>(x, y);
    }

    public void testCercaValor() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Quin valor voleu buscar: ");
        String buscar = in.nextLine();

        Full f = cd.obtenirFullActiu();
        Set<Pair<Pair<Integer,Integer> , String> > resCela = f.CercarValor(buscar);
        if (resCela.size() <= 0) {
            System.out.println("Valor no trobat!");
        }
        System.out.println("Valors trobats:");
        resCela.forEach(i->{System.out.println(i.second());});

    }

//testCopiarCela -> CtrlClipboard
//testTallarCela -> CtrlClipboard
//testCopiarBloc -> CtrlClipboard
//testTallarBloc -> CtrlClipboard
//testEnganxar -> CtrlClipboard
//testObtenirValorCela -> ?
//testObtenirValorRealCela -> ?


    public void testGetDocumentObert() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        Document res = cd.getDocumentObert();
        System.out.println("Nom document: " + res.GetNom() + " Localitzacio Document: " + res.GetLocal());
    }

    public void testObtenirFullActiu() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        Full res = cd.obtenirFullActiu();
        System.out.println("ID Full actiu: " + res.GetID());
    }

    public void testSetFullActiu() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        System.out.println("Entra el ID del full actiu al que es vol canviar");
        List<Integer> llista = cd.getDocumentObert().getTotsIDs();
        System.out.println("possibles IDs: " + llista);
        Scanner in = new Scanner(System.in);
        int idf = in.nextInt();
        cd.setFullActiu(idf);
        System.out.println("Canviat full actiu!");

    }

    public void testCrearDocument() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        System.out.println("Nom del document a crear: ");
        String nomDoc = in.nextLine();
        System.out.println("Nom entrat: " + nomDoc);
        System.out.println("Path document a crear: ");
        String path = in.nextLine();
        System.out.println("Path entrat: " + path);
        cd.crearDocument(path, nomDoc);
    }

    public void testTancarDocument() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        cd.tancarDocument();
        System.out.println("Document tancat!");
    }

    public void testObtenirCela() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Introdueix la coordenada de la cela a llegir (pex: A3)");
        Pair<Integer, Integer> coords = llegirCoords();
        Full f = cd.obtenirFullActiu();
        Cela c = f.GetCela(coords.first().intValue(), coords.second().intValue());
        System.out.println("Valor Usuari Cela: " + c.GetValorUsuari()/* + ", Valor Real Cela: " c.GetValorReal()*/);
    }

    public void testModificarCela() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Introdueix la coordenada de la cela a modificar (pex: A3)");
        Pair<Integer, Integer> coords = llegirCoords();
        System.out.println("Nou valor de la Cela: ");
        String input = in.nextLine();
        cd.modificarCela(coords.first().intValue(), coords.second().intValue(), input);
        System.out.println("Cela modificada!");

    }
    public void testAfegirFila() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Introdueix la posicio on vols afegir una fila (0.." + (cd.obtenirFullActiu().GetNumFil()) + ")");
        int pos = in.nextInt();
        cd.AfegirFila(pos);
        System.out.println("Fila Afegida!");
    }

    public void testAfegirColumna() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Introdueix la posicio on vols afegir una columna (0.." + (cd.obtenirFullActiu().GetNumCol()) + ")");
        int pos = in.nextInt();
        cd.AfegirColumna(pos);
        System.out.println("Columna Afegida!");

    }

    public void testEliminarFila() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Introdueix la posicio de la fila que vols eliminar (0.." + (cd.obtenirFullActiu().GetNumFil() - 1) + ")");
        int pos = in.nextInt();
        cd.EliminarFila(pos);
        System.out.println("Fila Eliminada!");

    }

    public void testEliminarColumna() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Introdueix la posicio de la columna que vols eliminar (0.." + (cd.obtenirFullActiu().GetNumCol() - 1) + ")");
        int pos = in.nextInt();
        cd.EliminarColumna(pos);
        System.out.println("Columna Eliminada!");

    }
    public void testCanviarNomDocument() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Introdueix el nou nom del Document: ");
        String nom = in.nextLine();
        cd.canviarNomDoc(nom);
        System.out.println("Nom del Document canviat a " + nom + "!");

    }
    public void testCanviarNomFull() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Introdueix el nou nom del Full: ");
        String nom = in.nextLine();
        cd.canviarNomFull(nom);
        System.out.println("Nom del Full canviat a " + nom + "!");

    }
    public void testEliminarFullActiu() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        cd.EliminarFullActiu();
        System.out.println("FullActiuEliminat!");

    }
    public void testCrearFull() throws Exception {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Introdueix el nou nom del Full: ");
        String nom = in.nextLine();
        System.out.println("Introdueix el numero de columnes: ");
        int ncol = in.nextInt();
        System.out.println("Introdueix el numero de files: ");
        int nfil = in.nextInt();
        cd.crearFull(nom, ncol, nfil);
        System.out.println("FullCreat!");
    }

    public static void main (String [] args) throws Exception {
        DriverDocument dd = new DriverDocument();
        System.out.println("Driver de Document (PROP Grup 1.1)");
        System.out.println("");
        mostra_metodes();
        dd.in = new Scanner(System.in);
        String input = dd.in.nextLine();
        while (!input.equals("0") && !input.equals("sortir")) {
            switch (input) {
                case "1":
                case "constructora": {
                    dd.testConstructora();
                    break;
                }
                case "2":
                case "cercarValor": {
                    dd.testCercaValor();
                    break;
                }
                case "3":
                case "getDocumentObert": {
                    dd.testGetDocumentObert();
                    break;
                }
                case "4":
                case "obtenirFullActiu": {
                    dd.testObtenirFullActiu();
                    break;
                }
                case "5":
                case "setFullActiu": {
                    dd.testSetFullActiu();
                    break;
                }
                case "6":
                case "crearDocument": {
                    dd.testCrearDocument();
                    break;
                }
                case "7":
                case "tancarDocument": {
                    dd.testTancarDocument();
                    break;
                }
                case "8":
                case "obtenirCela": {
                    dd.testObtenirCela();
                    break;
                }
                case "9":
                case "modificarCela": {
                    dd.testModificarCela();
                    break;
                }
                case "10":
                case "afegirFila": {
                    dd.testAfegirFila();
                    break;
                }
                case "11":
                case "afegirColumna": {
                    dd.testAfegirColumna();
                    break;
                }
                case "12":
                case "eliminarFila": {
                    dd.testEliminarFila();
                    break;
                }
                case "13":
                case "eliminarColumna": {
                    dd.testEliminarColumna();
                    break;
                }
                case "14":
                case "canviarNomDocument": {
                    dd.testCanviarNomDocument();
                    break;
                }
                case "15":
                case "canviarNomFull": {
                    dd.testCanviarNomFull();
                    break;
                }
                case "16":
                case "eliminarFull": {
                    dd.testEliminarFullActiu();
                    break;
                }
                case "17":
                case "crearFull": {
                    dd.testCrearFull();
                    break;
                }
                default:
                    break;
            }
            dd.tornarMenu();
            mostra_metodes();
            input = dd.in.nextLine();  //nextLine();
        }
        dd.in.close();
    }

    private static void mostra_metodes() {
        System.out.println("(1|constructora) - constructora");
        System.out.println("(2|ordenarPerColumnes) - ordenarPerColumnes");
        System.out.println("(3|getDocumentObert) - getDocumentObert");
        System.out.println("(4|obtenirFullActiu) - obtenirFullActiu");
        System.out.println("(5|setFullActiu) - setFullActiu");
        System.out.println("(6|crearDocument) - crearDocument");
        System.out.println("(7|tancarDocument) - tancarDocument");
        System.out.println("(8|obtenirCela) - obtenirCela");
        System.out.println("(9|modificarCela) - modificarCela");
        System.out.println("(10|afegirFila) - afegirFila");
        System.out.println("(11|afegirColumna) - afegirColumna");
        System.out.println("(12|eliminarFila) - eliminarFila");
        System.out.println("(13|eliminarColumna) - eliminarColumna");
        System.out.println("(14|canviarNomDocument) - canviarNomDocument");
        System.out.println("(15|canviarNomFull) - canviarNomFull");
        System.out.println("(16|eliminarFull) - eliminarFull");
        System.out.println("(17|crearFull) - crearFull");
        System.out.println("");
        System.out.println("(0|sortir) - Tancar driver");
    }

    private void tornarMenu() {
        System.out.println("Prem ENTER per tornar al menu principal");
        in.nextLine();
    }
}
