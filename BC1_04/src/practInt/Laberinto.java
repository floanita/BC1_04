package practInt;

import java.util.*;

import static practInt.Main.TECLADO;

public class Laberinto {

    public static void generarCamino(Tablero tablero, Tablero copiaTablero, int aux1, int aux2, ArrayList<int[]> caminoPosible, ArrayList<Integer> posibilidades, int valor) {

        while (!tableroCompleto(tablero)) {

            if (!caminoPosible.contains(tablero.tablero[aux1][aux2].getIdCelda())) {
                caminoPosible.add(tablero.tablero[aux1][aux2].getIdCelda()); // metemos la celda inicial
            }
            int opcion;
            boolean avanzo = false;

            posibilidades.clear();
            posibilidades.add(1);
            posibilidades.add(2);
            posibilidades.add(3);
            posibilidades.add(4);

            while (avanzo == false) {

                generarArrayPosibilidades(tablero, aux1, aux2, caminoPosible, posibilidades);

                if (posibilidades.isEmpty()) {

                    tablero.tablero = recuperarEstado(tablero, copiaTablero); //AAAAAAAAAAAAAAQUI

                    caminoPosible.clear();

                    int[] xd = new int[2];
                    xd = generarRandomNueva(tablero, aux1, aux2, caminoPosible, xd);
                    aux1 = xd[0];
                    aux2 = xd[1];
                    break;
                }
                opcion = (int) (Math.random() * posibilidades.size());
                opcion = posibilidades.get(opcion);
               

                switch (opcion) {
                    case 1:
                        if (comprobarNorte(tablero, aux1, aux2, caminoPosible) == true) {
                            caminoPosible.add(tablero.tablero[aux1 - 1][aux2].getIdCelda()); //A�adimos celda al camino posible // ANTES ERA LA CONTIGUA A LA QUE IBA ..............
                            tablero.tablero[aux1][aux2].neighbors[0] = true;		//Celda actual NORTE = TRUE
                            tablero.tablero[aux1 - 1][aux2].neighbors[2] = true;	//Celda vecina SUR = TRUE
                            aux1 = aux1 - 1; //La celda actual ahora pasa a ser la vecina.

                            avanzo = true;

                            if (tablero.tablero[aux1][aux2].isVisitado() == true) {
                                crearCamino(tablero, aux1, aux2, caminoPosible);
                                copiaTablero.tablero = crearCopia(tablero, copiaTablero);

                                if (tableroCompleto(tablero)) {
                                    tablero.pintarTablero(tablero.getrows(), tablero.getcols());
                                    
                                }
                                int[] xd = new int[2];
                                xd = generarRandomNueva(tablero, aux1, aux2, caminoPosible, xd);
                                aux1 = xd[0];
                                aux2 = xd[1];
                                break;
                            }
                            break;
                        }
                        if (posibilidades.contains(1)) {
                            posibilidades.remove(posibilidades.indexOf(1));
                        }
                        break;

                    case 2:
                        if (comprobarEste(tablero, aux1, aux2, caminoPosible) == true) {
                            caminoPosible.add(tablero.tablero[aux1][aux2 + 1].getIdCelda()); //A�adimos celda al camino posible
                            tablero.tablero[aux1][aux2].neighbors[1] = true;		//Celda actual NORTE = TRUE
                            tablero.tablero[aux1][aux2 + 1].neighbors[3] = true;	//Celda vecina SUR = TRUE
                            aux2 = aux2 + 1; //La celda actual ahora pasa a ser la vecina.

                            avanzo = true;

                            if (tablero.tablero[aux1][aux2].isVisitado() == true) {
                                crearCamino(tablero, aux1, aux2, caminoPosible);
                                copiaTablero.tablero = crearCopia(tablero, copiaTablero);

                                if (tableroCompleto(tablero)) {
                                    tablero.pintarTablero(tablero.getrows(), tablero.getcols());
                                    
                                }
                                //generarRandomNueva(tablero, aux1, aux2, caminoPosible);
                                int[] xd = new int[2];
                                xd = generarRandomNueva(tablero, aux1, aux2, caminoPosible, xd);
                                aux1 = xd[0];
                                aux2 = xd[1];
                                break;
                            }
                            break;
                        }
                        if (posibilidades.contains(2)) {
                            posibilidades.remove(posibilidades.indexOf(2));
                        }
                        break;

                    case 3:
                        if (comprobarSur(tablero, aux1, aux2, caminoPosible) == true) {
                            caminoPosible.add(tablero.tablero[aux1 + 1][aux2].getIdCelda()); //A�adimos celda al camino posible
                            tablero.tablero[aux1][aux2].neighbors[2] = true;		//Celda actual NORTE = TRUE
                            tablero.tablero[aux1 + 1][aux2].neighbors[0] = true;	//Celda vecina SUR = TRUE
                            aux1 = aux1 + 1; //La celda actual ahora pasa a ser la vecina.

                            avanzo = true;
                            if (tablero.tablero[aux1][aux2].isVisitado() == true) {
                                crearCamino(tablero, aux1, aux2, caminoPosible);
                                copiaTablero.tablero = crearCopia(tablero, copiaTablero);

                                if (tableroCompleto(tablero)) {
                                    
                                    tablero.pintarTablero(tablero.getrows(), tablero.getcols());
                                    //System.exit(0);
                                }
                                //generarRandomNueva(tablero, aux1, aux2, caminoPosible);
                                int[] xd = new int[2];
                                xd = generarRandomNueva(tablero, aux1, aux2, caminoPosible, xd);
                                aux1 = xd[0];
                                aux2 = xd[1];
                                break;
                            }
                            break;
                        }
                        if (posibilidades.contains(3)) {
                            posibilidades.remove(posibilidades.indexOf(3));
                        }
                        break;

                    case 4:
                        if (comprobarOeste(tablero, aux1, aux2, caminoPosible) == true) {
                            caminoPosible.add(tablero.tablero[aux1][aux2 - 1].getIdCelda()); //A�adimos celda al camino posible
                            tablero.tablero[aux1][aux2].neighbors[3] = true;		//Celda actual NORTE = TRUE
                            tablero.tablero[aux1][aux2 - 1].neighbors[1] = true;	//Celda vecina SUR = TRUE
                            aux2 = aux2 - 1; //La celda actual ahora pasa a ser la vecina.

                            avanzo = true;
                            

                            if (tablero.tablero[aux1][aux2].isVisitado() == true) {
                                crearCamino(tablero, aux1, aux2, caminoPosible);
                                copiaTablero.tablero = crearCopia(tablero, copiaTablero);

                                if (tableroCompleto(tablero)) {
                                    
                                    tablero.pintarTablero(tablero.getrows(), tablero.getcols());
                                   
                                }
                               
                                int[] xd = new int[2];
                                xd = generarRandomNueva(tablero, aux1, aux2, caminoPosible, xd);
                                aux1 = xd[0];
                                aux2 = xd[1];
                                break;
                            }
                            break;
                        }
                        if (posibilidades.contains(4)) {
                            posibilidades.remove(posibilidades.indexOf(4));
                        }
                        break;
                }
            }
        }

        mostrarSucesores(tablero);
        if (valor==1) {
        
        	guardarImagen(tablero);
        }

    }


	private static void solucionProblema(Tablero tablero) {
    	Random r = new Random();
    	for(int i=0; i<tablero.getrows(); i++) {
    		for(int j=0; j<tablero.getcols(); j++) {
    			tablero.getTablero()[i][j].setValue(r.nextInt(4));
    		}
    	}	
		
	}
	public static void mostrarSucesores(Tablero tablero) {
        Problema p = new Problema();
        ArchivoJSON escribirJSON = new ArchivoJSON();

        p.setTablero(tablero);
        int[] inicial = {0, 0};
        int[] objetivo = {p.getTablero().getrows() - 1, p.getTablero().getcols() - 1};
        p.setInitial(inicial);
        p.setObjetive(objetivo);
        
        for (int i=0; i<tablero.getrows();i++) {
        	for (int j=0 ; j<tablero.getcols(); j++) {
                int[] cambiante = {i, j};
                System.out.println("SUC(("+i+","+j+"))="+p.sucesores(cambiante, tablero));
        	}
        }
        escribirJSON.escribirJSON(p);

    }

    public static void generarArrayPosibilidades(Tablero tablero, int aux1, int aux2, ArrayList<int[]> caminoPosible, ArrayList<Integer> posibilidades) {
        if (aux1 - 1 >= 0) {
            if (caminoPosible.contains(tablero.tablero[aux1 - 1][aux2].getIdCelda())) {
                if (posibilidades.contains(1)) {
                    posibilidades.remove(posibilidades.indexOf(1));
                }
                
            }

        } else {
            if (posibilidades.contains(1)) {
                posibilidades.remove(posibilidades.indexOf(1));
            }
            
        }

        if (aux2 + 1 <= tablero.getcols() - 1) {
            if (caminoPosible.contains(tablero.tablero[aux1][aux2 + 1].getIdCelda())) {
                if (posibilidades.contains(2)) {
                    posibilidades.remove(posibilidades.indexOf(2));
                }
                
            }

        } else {
            if (posibilidades.contains(2)) {
                posibilidades.remove(posibilidades.indexOf(2));
            }
           
        }

        if (aux1 + 1 <= tablero.getrows() - 1) {
            if (caminoPosible.contains(tablero.tablero[aux1 + 1][aux2].getIdCelda())) {
                if (posibilidades.contains(3)) {
                    posibilidades.remove(posibilidades.indexOf(3));
                }
                
            }

        } else {
            if (posibilidades.contains(3)) {
                posibilidades.remove(posibilidades.indexOf(3));
            }
           
        }

        if (aux2 - 1 >= 0) {
            if (caminoPosible.contains(tablero.tablero[aux1][aux2 - 1].getIdCelda())) {
                if (posibilidades.contains(4)) {
                    posibilidades.remove(posibilidades.indexOf(4));
                }
                
            }

        } else {
            if (posibilidades.contains(4)) {
                posibilidades.remove(posibilidades.indexOf(4));
            }
            
        }
    }

    public static boolean tableroCompleto(Tablero tablero) { // M�todo que comprueba si quedan celdas por visitar.
        for (int i = 0; i < tablero.getrows(); i++) {
            for (int j = 0; j < tablero.getcols(); j++) {
                if (tablero.tablero[i][j].isVisitado() == false) {
                    return false;
                }
            }
        }
        return true;
    }

    // COMPROBAR IR A UNA POSICI�N
    // filas    columnas
    public static boolean comprobarNorte(Tablero tablero, int aux1, int aux2, ArrayList<int[]> caminoPosible) {
        if (aux1 - 1 >= 0) // Controlamos que no se salga del trablero.
        {
            if ((tablero.tablero[aux1][aux2].neighbors[0] == false) && (tablero.tablero[aux1 - 1][aux2].neighbors[2] == false) && (!caminoPosible.contains(tablero.tablero[aux1 - 1][aux2].getIdCelda()))) {
                return true;
            }
        }
        // Si mi celda tiene el Norte a False y el vecino del norte tiene el Sur a False y el del norte est� dentro del tablero y no caminoPosible no contiene la celda Norte.

        return false;
    }

    public static boolean comprobarEste(Tablero tablero, int aux1, int aux2, ArrayList<int[]> caminoPosible) {
        if (aux2 + 1 <= tablero.getcols() - 1) // Controlamos que no se salga del trablero.
        {
            if ((tablero.tablero[aux1][aux2].neighbors[1] == false) && (tablero.tablero[aux1][aux2 + 1].neighbors[3] == false) && (!caminoPosible.contains(tablero.tablero[aux1][aux2 + 1].getIdCelda()))) {
                return true;
            }
        }

        return false;
    }

    public static boolean comprobarSur(Tablero tablero, int aux1, int aux2, ArrayList<int[]> caminoPosible) {
        if (aux1 + 1 <= tablero.getrows() - 1) // Controlamos que no se salga del trablero.
        {
            if ((tablero.tablero[aux1][aux2].neighbors[2] == false) && (tablero.tablero[aux1 + 1][aux2].neighbors[0] == false) && (!caminoPosible.contains(tablero.tablero[aux1 + 1][aux2].getIdCelda()))) {
                return true;
            }
        }

        return false;
    }

    public static boolean comprobarOeste(Tablero tablero, int aux1, int aux2, ArrayList<int[]> caminoPosible) {
        if (aux2 - 1 >= 0) // Controlamos que no se salga del trablero.
        {
            if ((tablero.tablero[aux1][aux2].neighbors[3] == false) && (tablero.tablero[aux1][aux2 - 1].neighbors[1] == false) && (!caminoPosible.contains(tablero.tablero[aux1][aux2 - 1].getIdCelda()))) {
                return true;
            }
        }

        return false;
    }

    public static void crearCamino(Tablero tablero, int aux1, int aux2, ArrayList<int[]> caminoPosible) {
        for (int i = 0; i < tablero.getrows(); i++) { //Marcamos las celdas de caminoPosible a True.
            for (int j = 0; j < tablero.getcols(); j++) {
                for (int k = 0; k < caminoPosible.size(); k++) {
                    if (caminoPosible.get(k) == tablero.tablero[i][j].getIdCelda()) {
                        tablero.tablero[i][j].setVisitado(true);
                    }
                }
            }
        }
       
        caminoPosible.clear();
    }

    public static int[] generarRandomNueva(Tablero tablero, int aux1, int aux2, ArrayList<int[]> caminoPosible, int[] xd) {
        ArrayList<int[]> noVisitadas = new ArrayList<int[]>();

        Tablero.actualizarNoVisitadas(tablero, noVisitadas); // Este m�todo generar� la nueva lista de noVisitadas y nos devolver� una celda Random para seguir las iteraciones del bucle.

        int posicionRandom;
        int[] idCeldaRandom;
        if (!noVisitadas.isEmpty()) {
            posicionRandom = (int) (Math.random() * noVisitadas.size());
            idCeldaRandom = noVisitadas.get(posicionRandom);

            for (int i = 0; i < tablero.getrows(); i++) {
                for (int j = 0; j < tablero.getcols(); j++) {
                    if (tablero.tablero[i][j].getIdCelda() == idCeldaRandom) {
                        aux1 = i; // NO ACTUALIZA BIEN, CUIDAOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
                        aux2 = j; // Actualizamos los valores de i,j con las posiciones de la nueva celda
                    }
                }
            }

            xd[0] = aux1;
            xd[1] = aux2;

        } else {
            System.out.println("No quedan m�s celdas...");
            //tablero.pintarTablero(tablero.getrows(), tablero.getcols());
        }

        return xd;
    }

    public static Celda[][] crearCopia(Tablero tablero, Tablero copiaTablero) {
        for (int i = 0; i < tablero.getrows(); i++) {
            for (int j = 0; j < tablero.getcols(); j++) {
                copiaTablero.tablero[i][j].neighbors[0] = (tablero.tablero[i][j].neighbors[0]);
                copiaTablero.tablero[i][j].neighbors[1] = (tablero.tablero[i][j].neighbors[1]);
                copiaTablero.tablero[i][j].neighbors[2] = (tablero.tablero[i][j].neighbors[2]);
                copiaTablero.tablero[i][j].neighbors[3] = (tablero.tablero[i][j].neighbors[3]);

                copiaTablero.tablero[i][j].setVisitado(tablero.tablero[i][j].isVisitado());
            }
        }
        return copiaTablero.tablero;
    }
    //ELEGIR NUEVA CELDA ALEATORIA(NO VISITADA)	

    public static Celda[][] recuperarEstado(Tablero tablero, Tablero copiaTablero) {
        for (int i = 0; i < tablero.getrows(); i++) {
            for (int j = 0; j < tablero.getcols(); j++) {
                tablero.tablero[i][j].neighbors[0] = (copiaTablero.tablero[i][j].neighbors[0]);
                tablero.tablero[i][j].neighbors[1] = (copiaTablero.tablero[i][j].neighbors[1]);
                tablero.tablero[i][j].neighbors[2] = (copiaTablero.tablero[i][j].neighbors[2]);
                tablero.tablero[i][j].neighbors[3] = (copiaTablero.tablero[i][j].neighbors[3]);

                tablero.tablero[i][j].setVisitado(copiaTablero.tablero[i][j].isVisitado());
            }
        }
        return tablero.tablero;
    }

    // GUARDAR IMAGEN
    public static void guardarImagen(Tablero tablero) {
        Dibujar d = new Dibujar();
        try {
            d.dibujar(tablero, tablero.getrows(), tablero.getcols());
            String ruta = "Laberinto_" + tablero.getrows() + "x" + tablero.getcols() + ".png";
            StdDraw.save(ruta);
        } catch (NullPointerException e) {
            System.out.println("Error: "+ e);
        }
    }
    
    public static void Pintar(Tablero tablero) {
        Dibujar d = new Dibujar();
        try {
            d.pintar(tablero, tablero.getrows(), tablero.getcols());
            
        } catch (NullPointerException e) {
            System.out.println("Error: "+ e);
        }
    }
}
