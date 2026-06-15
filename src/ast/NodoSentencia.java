package ast;

public abstract class NodoSentencia extends Nodo {

    public NodoSentencia(String nombre) {
        super(nombre);
    }

    public abstract void generaAssembler(StringBuilder asm);
}
