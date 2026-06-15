package ast;

public class NodoAsignacion extends NodoSentencia {
    private final NodoIdentificador identificador;
    private NodoExpresion expresion;

    public NodoAsignacion(NodoIdentificador identificador, NodoExpresion expresion) {
        super(":=");
        this.identificador = identificador;
        this.expresion = expresion;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                identificador.graficar(miId) +
                expresion.graficar(miId);
    }

    public NodoExpresion getExpresion() {
        return expresion;
    }

    public void setExpresion(NodoExpresion expresion) {
        this.expresion = expresion;
    }

    @Override
    public void generaAssembler(StringBuilder asm) {
        expresion.generaAssembler(asm);
        asm.append("FLD " + expresion.nombreEnTS + "\n");
        asm.append("FSTP " + identificador.nombreEnTS + "\n");
    }
}
