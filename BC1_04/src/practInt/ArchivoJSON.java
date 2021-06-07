package practInt;

import java.io.*;
import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ArchivoJSON {
	
	// LEER
	
	public Problema leerJSON(String cadena) {
		File archivo = new File(cadena+ ".json");
		FileReader archivojson = null;
		Problema p = new Problema();

		try {
			archivojson = new FileReader(archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.print("No existe el fichero.");
			System.exit(0);
			
		}

		JsonParser parser = new JsonParser();
		JsonObject gsonObj = parser.parse(archivojson).getAsJsonObject();

		String inicial = gsonObj.get("INITIAL").getAsString();
		String objetivo = gsonObj.get("OBJETIVE").getAsString();
		String nombreTablero = gsonObj.get("MAZE").getAsString();
		
		p.setInitial(obtenerAtributos(inicial));
		p.setObjetive(obtenerAtributos(objetivo));
		p.setTablero(getLaberinto(nombreTablero));

		return p;
	}
	
	private Tablero getLaberinto(String nombreTab) {
		File archivo = new File(nombreTab);
		FileReader archivojson = null;
		System.out.println("Nombre "+ nombreTab);

		try {
			archivojson = new FileReader(archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		JsonParser parser = new JsonParser();
		JsonObject gsonObj = parser.parse(archivojson).getAsJsonObject();

		int filas = gsonObj.get("rows").getAsInt();
		int columnas = gsonObj.get("cols").getAsInt();

		JsonObject cells = gsonObj.get("cells").getAsJsonObject();
		Tablero tab = leerTablero(filas, columnas, cells);

		
		return tab;
	}

	private int[] obtenerAtributos(String atributo) {
		int[] valores = new int[2];
		valores[0] = getFilas(atributo);
		valores[1] = getColumnas(atributo);
		return valores;
	}

	private int getFilas(String atributo) {
		String[] separador = atributo.split(",");
		String part1 = separador[0]; // (....,
		part1 = part1.substring(1); // Quitamos el primer caracter , el (
		int fila = Integer.parseInt(part1);

		return fila;
	}

	private int getColumnas(String atributo) {
		String[] separador = atributo.split(",");
		String part1 = separador[1]; // ,...)
		part1 = part1.substring(1, part1.length() - 1); // Quitamos el ultimo caracter, el ), y el primero, el espacio
														// en blanco
		int columna = Integer.parseInt(part1);

		return columna;
	}
	
	public static Tablero leerTablero(int filas, int columnas, JsonObject cells) {
		Tablero tablero = new Tablero();

		tablero.setrows(filas);
		tablero.setcols(columnas);
		tablero = leerCeldas(filas, columnas, cells, tablero);
		return tablero;

	}

	public static Tablero leerCeldas(int filas, int columnas, JsonObject cells, Tablero tablero) {

		String posCelda = "";
		int[] vectorPosicion;
		Celda[][] celdas = new Celda[filas][columnas];
		Celda c;
		int k;

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				vectorPosicion = new int[2];
				c = new Celda();
				posCelda = "(" + i + ", " + j + ")";
				JsonObject cell = cells.get(posCelda).getAsJsonObject();
				vectorPosicion[0] = i;
				vectorPosicion[1] = j;
				
				c.setIdCelda(vectorPosicion);
				c.setValue(cell.get("value").getAsInt());
				JsonArray vecinos = cell.get("neighbors").getAsJsonArray();
				
				boolean [] vecis = new boolean [4];
				vecis[0]=true;
				vecis[1]=true;
				vecis[2]=true;
				vecis[3]=true;
				
				
				vecis = anadirElemento(vecis,vecinos);
					
				c.setNeighbors(vecis);
				celdas[i][j] = c;
				
			} 
	
		} 

		tablero.setTablero(celdas);

		return tablero;
	}
	public static boolean [] anadirElemento(boolean[]vecis, JsonArray vec) {
		
		for (int i=0; i<vecis.length; i++) {
			vecis[i]=vec.get(i).getAsBoolean();
		}
		return vecis;
	}
	
	
	//ESCRIBIR
	
    public void escribirJSON(Problema problema) {
        
        JsonObject obj = new JsonObject();

        int[] Dinitial = problema.getInitial();
        int[] Dobjetive = problema.getObjetive();

        String initial = "(" + Dinitial[0] + ", " + Dinitial[1] + ")";
        String objetive =  "(" + Dobjetive[0] + ", " + Dobjetive[1] + ")";
        int rows=problema.getTablero().getrows();
        int cols=problema.getTablero().getcols();
        String laberintoJSON = "problema_" + rows + "x" + cols + "_maze.json";

        obj.addProperty("INITIAL", initial);
        obj.addProperty("OBJETIVE", objetive);
        obj.addProperty("MAZE", laberintoJSON);
        
        
        try {
        	FileWriter doc = new FileWriter("problema_" + rows + "x" + cols + ".json");
        	doc.write(obj.toString());
            doc.flush();
            doc.close();
        } catch (Exception e) {
            System.out.println("Error al escribir el fichero " + e.toString());
        }
        escribirJSONGrande(problema.getTablero());
    }


    public void escribirJSONGrande(Tablero tablero) {

        JsonObject obj = new JsonObject();
        JsonObject objCasillas = anadirCasillas(tablero);
        
        JsonArray coordenada = new JsonArray();
        coordenada.add("N");
        coordenada.add("E");
        coordenada.add("S");
        coordenada.add("O");
        
        JsonArray Direccion = new JsonArray();
        //Se necesitan tuplas de objetos JSON para conservar las propiedades
        JsonArray Norte = new JsonArray();
        JsonArray Este = new JsonArray();
        JsonArray Sur = new JsonArray();
        JsonArray Oeste = new JsonArray();
        Norte.add(-1);
        Norte.add(0);
        Este.add(0);
        Este.add(1);
        Sur.add(1);
        Sur.add(0);
        Oeste.add(0);
        Oeste.add(-1);
        Direccion.add(Norte);
        Direccion.add(Este);
        Direccion.add(Sur);
        Direccion.add(Oeste);
        
        //FORMATO DE FICHERO PROPORCIONADO POR LA ASIGNATURA
        obj.addProperty("rows", tablero.getrows());
        obj.addProperty("cols", tablero.getcols());
        obj.addProperty("max_n", 4);
        obj.add("mov", Direccion);
        obj.add("id_mov", coordenada);
        obj.add("cells", objCasillas);

        try {
        	FileWriter doc = new FileWriter("problema_" + tablero.getrows() + "x"
                    + tablero.getcols() + "_maze.json");

            doc.write(obj.toString());
            doc.flush();
            doc.close();

        } catch (Exception e) {
            System.out.println("Error al escribir el fichero " + e.toString());
        }

    }

    private static JsonObject anadirCasillas(Tablero tablero) {
        JsonObject posicion = new JsonObject();
        Celda[][] celdas = tablero.getTablero();
        String pos = "";
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[0].length; j++) {
                JsonObject values = new JsonObject();
                JsonArray neighbors = new JsonArray();
                values.addProperty("value", celdas[i][j].getValue());

                neighbors.add(celdas[i][j].getNeighbors()[0]);
                neighbors.add(celdas[i][j].getNeighbors()[1]);
                neighbors.add(celdas[i][j].getNeighbors()[2]);
                neighbors.add(celdas[i][j].getNeighbors()[3]);

                values.add("neighbors", neighbors);
                pos = "(" + i + ", " + j + ")";
                posicion.add(pos, values);
            }
        }
        return posicion;
    }
}