package test.functions.stubs;

import main.domain.classes.functions.Interpret;
import main.domain.classes.types.Referencia;
import main.domain.classes.types.TType;
import main.domain.classes.types.Pair;
import main.domain.classes.Full;

public class ReferenciaStub extends Referencia {
    String _s;
    private ReferenciaStub(Pair<Integer, Integer> pos, Full f) {
        super(pos, f);
    }

    public ReferenciaStub(String s){
        super(null,null);
        _s = s;
    }

    public ReferenciaStub(double d) { this(String.valueOf(d)); }

    @Override
    public boolean EsFuncio(){ return false; }
    @Override
    public String GetValorReal() { return _s; }
    @Override
    public TType GetTipus() { return Interpret.getType(_s); }
}
