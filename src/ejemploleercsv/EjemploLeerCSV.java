/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemploleercsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author jalex
 */
public class EjemploLeerCSV {

    //Aqui se haec el llamado de los metodos que se crearon
    public static void main(String[] args) {
        HashMap<String , Integer> palabrasContadas = leerYContarPalabras("greenhouse-gas-emissions-by-region-industry-and-household-year-ended-2022.csv");
        guardarArchivoCSV(palabrasContadas , "palabras_frecuencias.csv");
    }
    
    
    //Metodo que lee y cuenta el archivo .csv con ayuda del HashMap para llevar el conteo
    public static HashMap<String,Integer> leerYContarPalabras(String archivo)
    {
        HashMap<String , Integer> conteo = new HashMap();
    
        try (BufferedReader leer = new BufferedReader(new FileReader(archivo))) // Se lee el archivo
        {
            String linea;
            
            while((linea = leer.readLine()) != null)//Ciclo encargado de recorrer todo el archivo
            {
                //Aui se lee cada linea y el split se encarga de separar la cadena en palabras hasta
                //ver la primera coma 
                String[]palabras = linea.toLowerCase().split("\\W+");
                for (String palabra : palabras)
                {
                    if(!palabra.isEmpty())//Se guarda la palabra y la clave cada vez que se encuentre una coma
                    {
                        conteo.put(palabra, conteo.getOrDefault(palabra,0) +1);
                    }
                }
            }
        }
        catch(IOException ex)
        {
            System.err.println("Error al leer el archivo.");
        }
        return conteo; 
    
    }
    
    //Metodo que guarda el archivo en otro archivo con extension .csv
    public static void guardarArchivoCSV(HashMap<String,Integer> conteo , String archivoNuevo)
    {
        try (FileWriter escribir = new FileWriter(archivoNuevo))//se crea un objeto que lee el archivo anterior
        {
            escribir.write("Palabra,Frecuencia\n");
            //For each que es el encargado de comparar si el String palabra esta 
            // en la clave que se encuentra el for, ya que el metodo keySet() tiene todas las claves del HashMap
            for (String palabra: conteo.keySet())
            {
                    if(palabra.matches(".*[a-zA-Z].*")) // Aqui si el archivo contiene numeros los va omitir y solo mostrara las palabras
                {
                escribir.write(palabra +"," +conteo.get(palabra) + "\n");//Aqui se escriben las palabras y claves en el nuevo archivo
                }
            }
            System.out.println("Archivo guardado como: "+archivoNuevo);
        }
        catch (IOException ex)
        {
            System.err.println("Error al guardar el archivo .csv");
        }
    }
}
