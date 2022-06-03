package main.domain.classes.functions;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Arrodonir extends FuncioDouble {
    /** Arrodoneix el valor entrat x al enter mes proper
     * @param
     * @return int <p> <b> <i> ATENCIO: </i> </b> Si el tamany utilitzat en el double es massa gran </p>
     * el valor de retorn del int sera truncat
     */
    public int arrodonir() {
        return (int)Math.round(_value);
    }
}