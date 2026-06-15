package ast;

import flex.TS;

public class NodoSuma extends NodoExpresionBinaria {

    public NodoSuma(NodoExpresion izquierda, NodoExpresion derecha) {
        super("+", izquierda, derecha);
    }

    @Override
    public void generaAssembler(StringBuilder asm) {
        /** @TODO Mejorar guardado en la TS. */
        nombreEnTS = TS.getInstance().addAuxiliar("-", "-", "-", "-");
        asm.append("FLD " + izquierda.nombreEnTS + "\n");
        asm.append("FLD " + derecha.nombreEnTS + "\n");
        asm.append("FADD\n");
        asm.append("FSTP " + nombreEnTS + "\n");
    }

}
