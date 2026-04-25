package ast;

public class NodoTerminoLet extends Nodo {
    private final NodoIdentificador id;
    private NodoExpresion valor;

    public NodoTerminoLet(NodoIdentificador id, NodoExpresion valor) {
        super("Termino LET");
        this.id = id;
        this.valor = valor;
    }

    public NodoTerminoLet(NodoIdentificador id) {
        super("Termino LET");
        this.id = id;
        this.valor = null;
    }

    public NodoIdentificador getId() {
        return this.id;
    }

    public NodoExpresion getValor() {
        return this.valor;
    }

    public void setValor(NodoExpresion valor) {
        this.valor = valor;
    }
}
