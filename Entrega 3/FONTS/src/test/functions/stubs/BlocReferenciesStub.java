package test.functions.stubs;

import main.domain.classes.Full;
import main.domain.classes.types.BlocReferencies;
import main.domain.classes.types.Pair;
import main.domain.classes.types.Referencia;

import java.util.List;

public class BlocReferenciesStub extends BlocReferencies {
    List<Referencia> _refs;

    public BlocReferenciesStub(Pair<Integer, Integer> pos1, Pair<Integer, Integer> pos2, Full f) {
        super(pos1, pos2, f);
    }

    public BlocReferenciesStub(List<Referencia> refs) {
        this(new Pair<>(0, 0), new Pair<>(1, 0), null);
        _refs = refs;
    }

    @Override
    public List<Referencia> GetReferencies() {
        return _refs;
    }

    @Override
    public int GetSize() {
        return _refs.size();
    }

    @Override
    protected void referencies() { }
}