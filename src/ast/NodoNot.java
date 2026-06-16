package ast;

public class NodoNot extends NodoExpresionBooleana {

    private final NodoExpresionBooleana condicion;

    public NodoNot(NodoExpresionBooleana condicion) {
        super("NOT");
        this.condicion = condicion;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();

        return super.graficar(idPadre)
                + condicion.graficar(miId);
    }

    @Override
    public void generarSaltoFalso(StringBuilder asm, String etiquetaFalsa) {
        //  falso de NOT es verdadero de la condición.
        condicion.generarSaltoVerdadero(asm, etiquetaFalsa);
    }

    @Override
    public void generarSaltoVerdadero(StringBuilder asm, String etiquetaVerdadera) {
        // verdadero de NOT es falso de la condición.
        condicion.generarSaltoFalso(asm, etiquetaVerdadera);
    }
}