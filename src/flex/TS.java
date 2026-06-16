package flex;

import java.util.ArrayList;

/**
 * Clase que almacena los datos para la tabla de símbolos.
 */
public final class TS {

	private static TS instance;
	private ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
	private static int cantindadDeAuxiliares = 0;

	private TS() {}

	public static TS getInstance() {
		if (instance == null) {
			instance = new TS();
		}
		return instance;
	}

	public Object[][] getData() {
		Object[][] result = new Object[data.size()][];
		for (int i = 0; i < result.length; i++) {
			result[i] = data.get(i).toArray();
		}
		return result;
	}

	public void clearData() {
		data.clear();
	}

	public void updateTypeSymbol(Object nombre, String tipo) {
		for (int i = 0; i < data.size(); i++) {
			if (nombre.toString().equals(data.get(i).get(0))) {
				data.get(i).set(2, tipo);
				break;
			}
		}
	}

	public void addSymbol(Object nombre,
                      Object token,
                      Object tipo,
                      Object valor,
                      Object longitud) {

    for (ArrayList<Object> fila : data) {
        if (nombre.toString().equals(fila.get(0).toString())) {
            return;
        }
    }

    ArrayList<Object> newData = new ArrayList<Object>();
    newData.add(nombre);
    newData.add(token);
    newData.add(tipo);
    newData.add(valor);
    newData.add(longitud);
    data.add(newData);
}

	public String addAuxiliar(Object token,
						   Object tipo,
						   Object valor,
						   Object longitud) {
		TS.cantindadDeAuxiliares++;
		String nombre = "_@aux" + TS.cantindadDeAuxiliares;
		addSymbol(nombre, "ID", "AUX", "-", "-"); // Se agrega el auxiliar como ID para que luego generaData()
		return nombre;
	}


private static int cantidadEtiquetas = 0;

public String addEtiqueta() {
    cantidadEtiquetas++;
    return "ETIQ" + cantidadEtiquetas;
}


}
