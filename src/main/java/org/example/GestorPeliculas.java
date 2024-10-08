package org.example;

import java.io.*;
import java.util.ArrayList;

/**
 * Clase que gestiona las peliculas.
 */

public class GestorPeliculas {
    /**
     * Elimina una película por su ID.
     * @param id ID de la película a eliminar.
     */

    public void eliminarPeliculaPorID (Integer id) {
        ArrayList<Pelicula> peliculas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("peliculas.csv"))){
            String linea;
            while ( (linea=reader.readLine()) != null) {
                String[] datos = linea.split(",");
                Pelicula pelicula = new Pelicula(Integer.parseInt(datos[0]), datos[1], Integer.parseInt(datos[2]), datos[3], datos[4]);
                peliculas.add(pelicula);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("peliculas.csv"))) {

            for (Pelicula peli : peliculas) {
                if (!peli.getId().equals(id)) {
                    bw.write(peli.getId() + "," + peli.getTitulo() + "," + peli.getAnho() + "," + peli.getDirector() + "," + peli.getGenero() + "\n");
                } else {
                    System.out.println("Pelicula eliminada: " + peli.getTitulo());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}
