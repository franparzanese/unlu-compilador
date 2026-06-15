package ast;

public abstract class NodoExpresion extends Nodo {
    protected String nombreEnTS;

    public NodoExpresion(String nombre) {
        super(nombre);
    }

    public abstract void generaAssembler(StringBuilder asm);
}
