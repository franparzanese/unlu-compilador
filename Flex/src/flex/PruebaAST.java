package flex;

import ast.NodoPrograma;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class PruebaAST {

	public static void main(String[] args) throws Exception {
		parser sintactico= new parser (new Lexico(new FileReader("prueba.txt")));
        NodoPrograma programa = (NodoPrograma) sintactico.parse().value;
     
        try {
            FileWriter archivo = new FileWriter("arbol.dot");
            PrintWriter pw = new PrintWriter(archivo);
            pw.println(programa.graficar());
            archivo.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    String cmd = "/usr/bin/dot -Tpng arbol.dot -o arbol.png";
    Runtime.getRuntime().exec(cmd);
    }
}
