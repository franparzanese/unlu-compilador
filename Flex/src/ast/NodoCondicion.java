package ast;

public class NodoCondicion extends NodoExpresion {

    private NodoExpresion izquierda;
    private String operador;
    private NodoExpresion derecha;

    public NodoCondicion(NodoExpresion izquierda, String operador, NodoExpresion derecha) {
        super("CONDICION");
        this.izquierda = izquierda;
        this.operador = operador;
        this.derecha = derecha;
    }
}