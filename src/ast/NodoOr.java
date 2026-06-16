package ast;

public class NodoOr extends NodoExpresionBooleana {

    private final NodoExpresionBooleana izquierda;
    private final NodoExpresionBooleana derecha;

    public NodoOr(NodoExpresionBooleana izquierda, NodoExpresionBooleana derecha) {
        super("OR");
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
        // En OR, solo salta falso si ambas condiciones son falsas.
        String etiquetaVerdadera = flex.TS.getInstance().addEtiqueta();

        izquierda.generarSaltoVerdadero(asm, etiquetaVerdadera);
        derecha.generarSaltoFalso(asm, etiquetaFalsa);

        asm.append(etiquetaVerdadera).append(":\n");
    }

    @Override
    public void generarSaltoVerdadero(StringBuilder asm, String etiquetaVerdadera) {
        // En OR, si cualquiera es verdadera, salta a verdadero.
        izquierda.generarSaltoVerdadero(asm, etiquetaVerdadera);
        derecha.generarSaltoVerdadero(asm, etiquetaVerdadera);
    }
}