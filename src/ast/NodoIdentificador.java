package ast;

public class NodoIdentificador extends NodoExpresion {
    private final String identificador;

    public NodoIdentificador(String identificador) {
        super("ID");
        this.identificador = identificador;
        this.nombreEnTS = identificador;
    }

    @Override
    public String getDescripcionNodo() {
        return "ID: " + identificador;
    }

    @Override
    public void generaAssembler(StringBuilder asm) {
        /** @TODO Implementar */
    }
}
