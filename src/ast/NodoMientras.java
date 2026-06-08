package ast;

import java.util.List;

public class NodoMientras extends NodoSentencia {

    private final NodoExpresionBooleana condicion;
    private final List<NodoSentencia> bloqueSentencias;

    public NodoMientras(NodoExpresionBooleana condicion, List<NodoSentencia> bloqueSentencias) {
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
        if (condicion != null) {
            resultado.append(condicion.graficar(miId));
        }

        // Grafica cada sentencia del bloque como hija del WHILE
        if (bloqueSentencias != null) {
            for (NodoSentencia sentencia : bloqueSentencias) {
                if (sentencia != null) {
                    resultado.append(sentencia.graficar(miId));
                }
            }
        }

        return resultado.toString();
    }
}