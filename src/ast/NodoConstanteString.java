package ast;

public class NodoConstanteString extends NodoExpresion {

    private final String valor;

    public NodoConstanteString(String valor) {
        super("CTE_STR");
        this.valor = valor;
        this.nombreEnTS = generarNombre(valor);
    }

    private String generarNombre(String valor) {

        String contenidoSinComillas = valor
                .replace("\"", "");

        String nombreNormalizado = contenidoSinComillas
                .replaceAll("[^a-zA-Z0-9_]", "_");

        return "_" + nombreNormalizado;
    }

    @Override
    public String getDescripcionNodo() {
        return "CTE_STR: " + valor.replace("\"", "\\\"");
    }

    @Override
    public void generaAssembler(StringBuilder asm) {
        // No genera código.
        // NodoPrint utiliza nombreEnTS.
    }
}