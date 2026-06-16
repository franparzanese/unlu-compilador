package ast;

import java.util.List;

public class NodoIf extends NodoSentencia {
    private final NodoExpresionBooleana condicion;
    private final List<NodoSentencia> sentenciasThen;
    private final List<NodoSentencia> sentenciasElse;

    public NodoIf(NodoExpresionBooleana condicion, List<NodoSentencia> sentenciasThen, List<NodoSentencia> sentenciasElse) {
        super("IF");
        this.condicion = condicion;
        this.sentenciasThen = sentenciasThen;
        this.sentenciasElse = sentenciasElse;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        StringBuilder resultado = new StringBuilder();

        // Grafica el nodo IF
        resultado.append(super.graficar(idPadre));

        // Grafica la condición colgando directamente del nodo IF
        resultado.append(condicion.graficar(miId));

        // Agrega un nodo ficticio THEN colgando del nodo IF
        Nodo nodoThen = new Nodo("Then");
        resultado.append(nodoThen.graficar(miId));

        // Grafica las sentencias asociadas al "then" colgando del nodo ficticio THEN
        String idNodoThen = nodoThen.getIdNodo();
        for (NodoSentencia sentencia: sentenciasThen) {
            resultado.append(sentencia.graficar(idNodoThen));
        }

        // Si hay sentencias asociadas al "else"...
        if (sentenciasElse != null) {
            // Agrega un nodo ficticio "ELSE" colgando del nodo IF
            Nodo nodoElse = new Nodo("Else");
            resultado.append(nodoElse.graficar(miId));

            // Grafica las sentencias asociadas al "else" colgando del nodo ficticio ELSE
            String idNodoElse = nodoElse.getIdNodo();
            for (NodoSentencia sentencia: sentenciasElse) {
                resultado.append(sentencia.graficar(idNodoElse));
            }
        }

        return resultado.toString();
    }

  @Override
public void generaAssembler(StringBuilder asm) {

    // Etiqueta a la que se salta si la condición es falsa
    String etiquetaElse = flex.TS.getInstance().addEtiqueta();

    // Etiqueta que marca el final del IF
    String etiquetaFin = flex.TS.getInstance().addEtiqueta();

    // Genera el salto al ELSE cuando la condición es falsa
    condicion.generarSaltoFalso(asm, etiquetaElse);

    // Genera el bloque THEN
    for (NodoSentencia sentencia : sentenciasThen) {
        sentencia.generaAssembler(asm);
    }

    // Salta al final para no ejecutar el ELSE
    asm.append("JMP ").append(etiquetaFin).append("\n");

    // Inicio del bloque ELSE
    asm.append(etiquetaElse).append(":\n");

    if (sentenciasElse != null) {
        for (NodoSentencia sentencia : sentenciasElse) {
            sentencia.generaAssembler(asm);
        }
    }

   
    asm.append(etiquetaFin).append(":\n");
}
}
