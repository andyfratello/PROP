package drivers;

import main.domain.classes.Cela;
import main.domain.classes.Document;
import main.domain.classes.Full;
import main.domain.classes.types.Pair;
import main.domain.controllers.CtrlClipboard;
import main.domain.controllers.CtrlDomini;
import java.util.*;

/**
 * Aquest Driver serveix per comprovar que es poden copiar cel·les individualment i blocs, i enganxar-los a altres
 * coordenades del full on s'estigui treballant. A més inclou una constructora del document i un mètode per poder
 * modificar cel·les, per tal de veure si aquestes es copien, tallen i enganxen correctament
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class DriverClipboard {
    private CtrlClipboard cb = null;
    private CtrlDomini cd = null;
    private Scanner in;

    public void testConstructora() {
        cd = new CtrlDomini();
        testCrearDocument();
        cd.crearFull();
        cb = new CtrlClipboard(cd);
    }

    /**
     * Retorna string en Hexavigesimal (base26 en caràcters) a integer
     * @param String en base26 a caràcters
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

    private Pair<Integer, Integer> llegirCoords() {
        String input = in.nextLine();
        int x, y;
        int i = 0;
        while (i < input.length() && !esInteger(input.charAt(i))) i++;
        String x_string = input.substring(0,i);
        String y_string = input.substring(i,input.length());
        x = hexavigesimalToInteger(x_string);
        y = Integer.parseInt(y_string, 10) - 1;
        return new Pair<Integer, Integer>(x, y);
    }

    public void testCopiarCela() {
        if (cb == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        System.out.println("Introdueix coordenada de la Cela a copiar (pex: A3)");
        Pair<Integer, Integer> coords = llegirCoords();
        //cb.copiarCela(coords.first().intValue(), coords.second().intValue());
        cb.copiarBloc(coords.first().intValue(), coords.second().intValue(), coords.first().intValue(), coords.second().intValue());
        System.out.println("Cela copiada!");
    }

    public void testTallarCela() {
        if (cb == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        System.out.println("Introdueix coordenada de la Cela a tallar (pex: A3)");
        Pair<Integer, Integer> coords = llegirCoords();
        //cb.tallarCela(coords.first().intValue(), coords.second().intValue());
        cb.tallarBloc(coords.first().intValue(), coords.second().intValue(), coords.first().intValue(), coords.second().intValue());
        System.out.println("Cela tallada!");
    }

    public void testCopiarBloc() {
        if (cb == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        System.out.println("Introdueix les coordenades que formen el bloc de celes a copiar (pex: A3)");
        Pair<Integer, Integer> coord1 = llegirCoords();
        System.out.println("Segones coordenades: ");
        Pair<Integer, Integer> coord2 = llegirCoords();
        cb.copiarBloc(coord1.first().intValue(), coord1.second().intValue(), coord2.first().intValue(), coord2.second().intValue());
        System.out.println("Bloc copiat!");
    }

    public void testTallarBloc() {
        if (cb == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        System.out.println("Introdueix les coordenades que formen el bloc de celes a tallar (pex: A3)");
        Pair<Integer, Integer> coord1 = llegirCoords();
        System.out.println("Segones coordenades: ");
        Pair<Integer, Integer> coord2 = llegirCoords();
        cb.tallarBloc(coord1.first().intValue(), coord1.second().intValue(), coord2.first().intValue(), coord2.second().intValue());
        System.out.println("Bloc tallat!");
    }

    public void testEnganxar() {
        if (cb == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        System.out.println("Introdueix coordenada de la Cela on enganxar (pex: A3)");
        Pair<Integer, Integer> coords = llegirCoords();
        cb.enganxar(coords.first().intValue(), coords.second().intValue());
        System.out.println("Cela/les enganxades");
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

    public void testModificarCela() {
        if (cd == null) {
            System.err.println("Primer s'ha de crear el controlador!");
            return;
        }
        System.out.println("Introdueix la coordenada de la cela a modificar (pex: A3)");
        Pair<Integer, Integer> coords = llegirCoords();
        System.out.println("Nou valor de la Cela: ");
        String input = in.nextLine();
        cd.modificarCela(coords.first().intValue(), coords.second().intValue(), input);
        System.out.println("Cela modificada!");
    }

    public static void main (String [] args) {
        DriverClipboard dd = new DriverClipboard();
        System.out.println("Driver de Clipboard (PROP Grup 1.1)");
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
                case "modificarCela": {
                    dd.testModificarCela();
                    break;
                }
                case "3":
                case "copiarCela": {
                    dd.testCopiarCela();
                    break;
                }
                case "4":
                case "tallarCela": {
                    dd.testTallarCela();
                    break;
                }
                case "5":
                case "copiarBloc": {
                    dd.testCopiarBloc();
                    break;
                }
                case "6":
                case "tallarBloc": {
                    dd.testTallarBloc();
                    break;
                }
                case "7":
                case "enganxar": {
                    dd.testEnganxar();
                    break;
                }
                default:
                    break;
            }
            dd.tornarMenu();
            mostra_metodes();
            input = dd.in.nextLine();
        }
        dd.in.close();
    }

    private static void mostra_metodes() {
        System.out.println("(1|constructora) - Constructora");
        System.out.println("(2|modificarCela) - Modificar Cela");
        System.out.println("(3|copiarCela) - Copiar Cela");
        System.out.println("(4|tallarCela) - Tallar Cela");
        System.out.println("(5|copiarBloc) - Copiar Bloc");
        System.out.println("(6|tallarBloc) - Tallar Bloc");
        System.out.println("(7|enganxar) - Enganxar");
        System.out.println("");
        System.out.println("(0|sortir) - Tancar Driver");
    }

    private void tornarMenu() {
        System.out.println("Prem ENTER per tornar al menu principal");
        in.nextLine();
    }
}
