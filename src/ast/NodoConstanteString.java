package ast;

public class NodoConstanteString extends NodoExpresion {

    private final String valor;

    public NodoConstanteString(String valor) {
        super("CTE_STR");
        this.valor = valor;
        this.nombreEnTS = generarNombre(valor);
    }

    private String generarNombre(String valor) {
        return "_" + valor
                .replace("\"", "")
                .replace(" ", "_");
    }

    @Override
    public String getDescripcionNodo() {
        return "CTE_STR: " + valor.replace("\"", "\\\"");
    }

    @Override
    public void generaAssembler(StringBuilder asm) {
        /** No genera código. PRINT usa nombreEnTS. */
    }
}