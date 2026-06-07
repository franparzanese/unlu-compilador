package ast;

public class NodoMayorOIgual extends NodoComparacion {
    public NodoMayorOIgual(NodoExpresion izquierda, NodoExpresion derecha) {
        super(">=", izquierda, derecha);
    }
}
