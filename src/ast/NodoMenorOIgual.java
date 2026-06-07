package ast;

public class NodoMenorOIgual extends NodoComparacion {
    public NodoMenorOIgual(NodoExpresion izquierda, NodoExpresion derecha) {
        super("<=", izquierda, derecha);
    }
}
