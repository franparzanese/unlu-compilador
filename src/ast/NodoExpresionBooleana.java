package ast;

public class NodoExpresionBooleana extends NodoExpresion {

    public NodoExpresionBooleana(String nombre) {
        super(nombre);
    }

    @Override
    public void generaAssembler(StringBuilder asm) {
        // Las booleanas se traducen con saltos, no dejando un valor en la FPU.
    }

    public void generarSaltoFalso(StringBuilder asm, String etiquetaFalsa) {
        // Se implementa en las subclases.
    }

    public void generarSaltoVerdadero(StringBuilder asm, String etiquetaVerdadera) {
        // Se implementa en las subclases.
    }
}