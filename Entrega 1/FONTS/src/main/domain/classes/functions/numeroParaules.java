package main.domain.classes.functions;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class numeroParaules extends FuncioString {
    private static Boolean esCharValidEnParaula(char c) {
        return (c == '\'' || Character.isLetter(c));
    }

    /** Aquest metode retorna el nombre de paraules en el text s (Considerant que l'apostrof forma part de la paraula)
     * @param
     * @return int
     */
    public int numeroParaules() {
        if (_value.isBlank()) {
            return 0;
        }
        int numParaules = 0;
        Boolean enParaula = false;
        for (int i = 0; i < _value.length(); i++) {
            if (esCharValidEnParaula(_value.charAt(i)) && enParaula == false) {
                numParaules++;
                enParaula = true;
            }
            else if (!esCharValidEnParaula(_value.charAt(i))) {
                enParaula = false;
            }
        }
        return numParaules;
    }

}