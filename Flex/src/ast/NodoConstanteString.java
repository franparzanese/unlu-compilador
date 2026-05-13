package ast;

public class NodoConstanteString extends NodoExpresion {

    private final String valor;

    public NodoConstanteString(String valor) {
        super("CTE_STR");
        this.valor = valor;
    }

    @Override
    public String getDescripcionNodo() {
        return "CTE_STR: " + valor.replace("\"", "\\\"");
    }
}