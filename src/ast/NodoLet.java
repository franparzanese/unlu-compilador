package ast;

import java.util.List;

public class NodoLet extends NodoSentencia {
    private final List<NodoTerminoLet> listaLet;
    private final NodoExpresion valorDefault;

    public NodoLet(List<NodoTerminoLet> listaLet, NodoExpresion valorDefault) {
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
        for (NodoTerminoLet terminoLet : listaLet) {
            if (terminoLet.getExpresion() == null) {
                terminoLet.setExpresion(valorDefault);
            }
            resultado.append(terminoLet.graficar(miId));
        }

        return resultado.toString();
    }

   @Override
    public void generaAssembler(StringBuilder asm) {
    for (NodoTerminoLet terminoLet : listaLet) {

        if (terminoLet.getExpresion() == null) {
            terminoLet.setExpresion(valorDefault);
        }

        terminoLet.generaAssembler(asm);
    }
}
}
