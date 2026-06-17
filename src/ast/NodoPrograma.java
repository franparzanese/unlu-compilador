package ast;

import flex.TS;
import java.util.List;

public class NodoPrograma extends Nodo {
    private final List<NodoSentencia> sentencias;

    public NodoPrograma(List<NodoSentencia> sentencias) {
        super("PGM");
        this.sentencias = sentencias;
    }

    public String graficar() {
        // Acá se dispara la invocación a los métodos graficar() de los nodos.
        // Como un NodoPrograma no tiene padre, se inicia pasando null.
        return this.graficar(null);
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = "nodo_programa";

        StringBuilder resultado = new StringBuilder();
        resultado.append("graph G {");

        resultado.append(miId + " [label=\"Programa\"]\n");
        for (NodoSentencia sentencia : this.sentencias) {
            resultado.append(sentencia.graficar(miId));
        }

        resultado.append("}");

        return resultado.toString();
    }

    private String generaData() {
        StringBuilder dataAsm = new StringBuilder();
        Object[][] simbolos = TS.getInstance().getData();
        for (Object[] fila : simbolos) {
            String nombre = fila[0].toString();
            String token = fila[1].toString();
            String valor = fila[3].toString();
            if (token.equals("ID")) {
                dataAsm.append(nombre).append(" dd ?\n");
            } else if (token.equals("CONST_INT")) {
                dataAsm.append(nombre).append(" dd ").append(valor).append(".0\n");
            } else if (token.equals("CONST_FLOAT")) {
                dataAsm.append(nombre).append(" dd ").append(valor).append("\n");
            } else if (token.equals("CONST_STRING")) {
                dataAsm.append(nombre).append(" db ").append(valor).append(",'$'\n");
            }
        }        return dataAsm.toString();
    }

   public String generaAssembler() {
    StringBuilder asm = new StringBuilder();
    StringBuilder codigoAsm = new StringBuilder();

   
    // se agregan los auxiliares a la TS. 
    for (NodoSentencia sentencia : sentencias) {
        sentencia.generaAssembler(codigoAsm);
    }

    asm.append(".MODEL LARGE\n");
    asm.append(".386\n");
    asm.append(".STACK 200h\n\n");

    asm.append(".DATA\n\n");
    asm.append(generaData()).append("\n");

    asm.append(".CODE\n\n");
    asm.append("MOV AX,@DATA\n");
    asm.append("MOV DS,AX\n");
    asm.append("MOV ES,AX\n\n");

    asm.append(codigoAsm.toString());

    asm.append("\nMOV AX,4C00h\n");
    asm.append("INT 21h\n\n");
    asm.append("END\n");

    return asm.toString();
}
}
