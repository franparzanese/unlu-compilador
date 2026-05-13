package ast;

import java.util.ArrayList;

public class NodoMientras extends NodoSentencia {

    private final NodoExpresion condicion;
    private final ArrayList<NodoSentencia> bloqueSentencias;

    public NodoMientras(NodoExpresion condicion, ArrayList<NodoSentencia> bloqueSentencias) {
        super("WHILE");
        this.condicion = condicion;
        this.bloqueSentencias = bloqueSentencias;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        StringBuilder resultado = new StringBuilder();

        // Grafica el nodo WHILE
        resultado.append(super.graficar(idPadre));

        // Grafica la condición como hija del WHILE
        if (this.condicion != null) {
            resultado.append(this.condicion.graficar(miId));
        }

        // Grafica cada sentencia del bloque como hija del WHILE
        if (this.bloqueSentencias != null) {
            for (NodoSentencia sentencia : this.bloqueSentencias) {
                if (sentencia != null) {
                    resultado.append(sentencia.graficar(miId));
                }
            }
        }

        return resultado.toString();
    }
}