package ast;

public class NodoConstanteFloat extends NodoExpresion {

    private final float valor;

    public NodoConstanteFloat(float valor) {
        super("CTE_FLOAT");
        this.valor = valor;
       this.nombreEnTS = "_cte" + Float.toString(valor).replace(".", "_");
    }

    @Override
    public String getDescripcionNodo() {
        return "CTE_FLOAT: " + Float.toString(valor);
    }

    @Override
    public void generaAssembler(StringBuilder asm) {
        /** @TODO Implementar */
    }

}
