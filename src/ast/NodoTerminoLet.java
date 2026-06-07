package ast;

public class NodoTerminoLet extends NodoAsignacion {
    public NodoTerminoLet(NodoIdentificador id, NodoExpresion valor) {
        super(id, valor);
    }

    public NodoTerminoLet(NodoIdentificador id) {
        super(id, null);
    }
}
