package ast;

public class NodoPrint extends NodoSentencia {

    private final NodoExpresion expresion;

    public NodoPrint(NodoExpresion expresion) {
        super("PRINT");
        this.expresion = expresion;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();

        return super.graficar(idPadre)
                + expresion.graficar(miId);
    }

   @Override
    public void generaAssembler(StringBuilder asm) {

        if (expresion instanceof NodoConstanteString) {
            asm.append("displayString ")
            .append(expresion.nombreEnTS)
            .append("\n");
            asm.append("newLine 1\n");
        } else if (expresion instanceof NodoIdentificador) {
            asm.append("DisplayFloat ")
            .append(expresion.nombreEnTS)
            .append(", 2\n");

            asm.append("newLine 1\n");
        }
    }
}