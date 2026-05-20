package ast;

import java.util.ArrayList;

public class NodoLet extends NodoSentencia {
    private final ArrayList<NodoTerminoLet> listaLet;
    private final NodoExpresion valorDefault;

    public NodoLet(ArrayList<NodoTerminoLet> listaLet, NodoExpresion valorDefault) {
        super("LET");
        this.listaLet = listaLet;
        this.valorDefault = valorDefault;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        StringBuilder resultado = new StringBuilder();

        // Grafica el nodo LET
        resultado.append(super.graficar(idPadre));

        // Loop para recorrer los términos LET.
        for (NodoTerminoLet terminoLet : this.listaLet) {
            if (terminoLet.getExpresion() == null) {
                terminoLet.setExpresion(this.valorDefault);
            }
            resultado.append(terminoLet.graficar(miId));
        }

        return resultado.toString();
    }
}
