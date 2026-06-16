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
        asm.append("MOV DX, OFFSET ").append(expresion.nombreEnTS).append("\n");
        asm.append("MOV AH, 9\n");
        asm.append("INT 21h\n");
    }
}