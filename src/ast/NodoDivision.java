package ast;

public class NodoDivision extends NodoExpresionBinaria {

    public NodoDivision(NodoExpresion izquierda, NodoExpresion derecha) {
        super("/", izquierda, derecha);
    }

    @Override
    public void generaAssembler(StringBuilder asm) {
        /** @TODO Implementar */
    }
}
