package ast;

public class NodoComparacion extends NodoExpresionBooleana {

    private final NodoExpresion izquierda;
    private final NodoExpresion derecha;

    public NodoComparacion(String nombre, NodoExpresion izquierda, NodoExpresion derecha) {
        super(nombre);
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
    public void generaAssembler(StringBuilder asm) {

        izquierda.generaAssembler(asm);
        derecha.generaAssembler(asm);

        asm.append("FLD ").append(izquierda.nombreEnTS).append("\n");
        asm.append("FLD ").append(derecha.nombreEnTS).append("\n");
        asm.append("FXCH\n");
        asm.append("FCOM\n");
        asm.append("FSTSW AX\n");
        asm.append("SAHF\n");
        asm.append("FFREE\n");
    }

    @Override
public void generarSaltoFalso(StringBuilder asm, String etiquetaFalsa) {
    generaAssembler(asm);

    if (this instanceof NodoMayor) {
        asm.append("JBE ").append(etiquetaFalsa).append("\n");
    } else if (this instanceof NodoMenor) {
        asm.append("JAE ").append(etiquetaFalsa).append("\n");
    } else if (this instanceof NodoMayorOIgual) {
        asm.append("JB ").append(etiquetaFalsa).append("\n");
    } else if (this instanceof NodoMenorOIgual) {
        asm.append("JA ").append(etiquetaFalsa).append("\n");
    }
}

@Override
public void generarSaltoVerdadero(StringBuilder asm, String etiquetaVerdadera) {
    generaAssembler(asm);

    if (this instanceof NodoMayor) {
        asm.append("JA ").append(etiquetaVerdadera).append("\n");
    } else if (this instanceof NodoMenor) {
        asm.append("JB ").append(etiquetaVerdadera).append("\n");
    } else if (this instanceof NodoMayorOIgual) {
        asm.append("JAE ").append(etiquetaVerdadera).append("\n");
    } else if (this instanceof NodoMenorOIgual) {
        asm.append("JBE ").append(etiquetaVerdadera).append("\n");
    }
}
}