package ast;

import flex.TS;

public class NodoSuma extends NodoExpresionBinaria {

    public NodoSuma(NodoExpresion izquierda, NodoExpresion derecha) {
        super("+", izquierda, derecha);
    }

    @Override
    public void generaAssembler(StringBuilder asm) {
        /** @TODO Mejorar guardado en la TS. */
        izquierda.generaAssembler(asm);
        derecha.generaAssembler(asm);
        nombreEnTS = TS.getInstance().addAuxiliar("-", "-", "-", "-");
        asm.append("FLD ").append(izquierda.nombreEnTS).append("\n");
        asm.append("FLD ").append(derecha.nombreEnTS).append("\n");
        asm.append("FADD\n");
        asm.append("FSTP ").append(nombreEnTS).append("\n");
    }

}
