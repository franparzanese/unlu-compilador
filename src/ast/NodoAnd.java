package ast;

public class NodoAnd extends NodoExpresionBooleana {

    private final NodoExpresionBooleana izquierda;
    private final NodoExpresionBooleana derecha;

    public NodoAnd(NodoExpresionBooleana izquierda, NodoExpresionBooleana derecha) {
        super("AND");
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();

        return super.graficar(idPadre)
                + izquierda.graficar(miId)
                + derecha.graficar(miId);
    }

    @Override
    public void generarSaltoFalso(StringBuilder asm, String etiquetaFalsa) {
        // En AND, si cualquiera de las dos condiciones es falsa, salta.
        izquierda.generarSaltoFalso(asm, etiquetaFalsa);
        derecha.generarSaltoFalso(asm, etiquetaFalsa);
    }

    @Override
    public void generarSaltoVerdadero(StringBuilder asm, String etiquetaVerdadera) {
        // Para que AND sea verdadero, ambas deben ser verdaderas.
        String etiquetaIntermedia = flex.TS.getInstance().addEtiqueta();

        izquierda.generarSaltoFalso(asm, etiquetaIntermedia);
        derecha.generarSaltoVerdadero(asm, etiquetaVerdadera);

        asm.append(etiquetaIntermedia).append(":\n");
    }
}