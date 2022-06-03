package drivers;

import main.domain.classes.Cela;
import main.domain.classes.Document;
import main.domain.classes.Full;
import main.domain.classes.types.Pair;
import main.domain.controllers.CtrlCelaSeleccionada;
import main.domain.controllers.CtrlDomini;
import main.domain.controllers.CtrlDocument;
import java.util.*;

/**
 * Driver per testejar les funcionalitats de CtrlCelaSeleccionada
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class DriverCelaSeleccionada {
    private static Scanner in = null;
    private static CtrlCelaSeleccionada cc = null;

    private static CtrlDomini cd = null;

    public static void main (String [] args) {
        init();

        System.out.println("Driver de testeig de CtrlCelaSeleccionada");
        System.out.println("");

        mostra_metodes();

        String input = in.nextLine();
        while (input != "0" && input != "sortir") {
            switch (input) {
                case "1":
                case "construirCtrlCelaSeleccionada": {

                    break;
                }
                case "2":
                case "obtenirCela": {

                    break;
                }
                case "3":
                case "obtenirPosicio": {

                    break;
                }
                case "4":
                case "selectNovaCela": {

                    break;
                }
                case "5":
                case "getValorUsuari": {

                    break;
                }
                case "6":
                case "getValorReal": {

                    break;
                }
                case "7":
                case "modificaCela": {

                    break;
                }
                default:
                    System.out.println("Valor invalid");
                    break;
            }
            input = in.nextLine();
        }
        in.close();
    }

    private void testConstructora(){
        System.out.println("Introdueix la columna: ");
        int col = in.nextInt();
        System.out.println("Introdueix la fila: ");
        int fil = in.nextInt();

        cc = new CtrlCelaSeleccionada(new CtrlDocument(), new Pair(col, fil));
    }

    private void testObtenirCela(){ System.out.println("La cel·la seleccionada es: " + cc.GetCelaSeleccionada() ); }

    private void testObtenirPosicio(){ System.out.println("La cel·la seleccionada esta a la posició: " + cc.GetPosicio() ); }

    private void testSelectNovaCela()
    {
        System.out.println("Introdueix la columna: ");
        int col = in.nextInt();
        System.out.println("Introdueix la fila: ");
        int fil = in.nextInt();

        cc.SelectNovaCela(new Pair(col, fil));
    }

    private void testGetValorUsuari(){ System.out.println("El valor introduit per l'usuari es: " + cc.GetValorUsuari() ); }

    private void testGetValorReal(){ System.out.println("El valor realde la cel·la es: " + cc.GetValorReal() ); }

    private void testModifica()
    {
        System.out.println("Introdueix nou valor per la cel·la: ");
        cc.Modificar(in.nextLine());
    }

    private static void mostra_metodes() {
        System.out.println("(1|construirCtrlCelaSeleccionada) - Crear instancia de CtrlCelaSeleccionada");
        System.out.println("(2|obtenirCela) - Obte el valor de la cel·la seleccionada");
        System.out.println("(3|obtenirPosicio) - Obte la posició de la cel·la seleccionada");
        System.out.println("(4|selectNovaCela) - Selecciona una nova cel·la");
        System.out.println("(5|getValorUsuari) - Obte el valor introduit per l'usuari de la cel·la seleccionada");
        System.out.println("(6|getValorReal) - Obte el valor real de la cel·la seleccionada");
        System.out.println("(7|modificaCela) - Modifica la cel·la seleccionada");
        System.out.println("");
        System.out.println("(0|sortir) - Tancar driver");
    }

    private static void init()
    {
        in = new Scanner(System.in);
        cd = new CtrlDomini();
        cd.crearDocument("");
        cd.crearFull();

        cc = new CtrlCelaSeleccionada(new CtrlDocument() );

    }
}