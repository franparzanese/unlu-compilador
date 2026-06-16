package ast;
import flex.TS;
public class NodoDivision extends NodoExpresionBinaria {

    public NodoDivision(NodoExpresion izquierda, NodoExpresion derecha) {
        super("/", izquierda, derecha);
    }

     @Override
    public void generaAssembler(StringBuilder asm) {

        izquierda.generaAssembler(asm);
        derecha.generaAssembler(asm);

        nombreEnTS = TS.getInstance().addAuxiliar("-", "-", "-", "-");

        asm.append("FLD ").append(izquierda.nombreEnTS).append("\n");
        asm.append("FLD ").append(derecha.nombreEnTS).append("\n");
        asm.append("FDIV\n");
        asm.append("FSTP ").append(nombreEnTS).append("\n");
    }
}
