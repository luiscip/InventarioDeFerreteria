/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package database;

/**
 *
 * @author Luiis
 */
public class PruebaConexion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion con =  new Conexion();
        con.conectar();

        if(con.cadena !=null){
            System.out.println("Conectado");
        } else{
            System.out.println("Desconectado");
        }


    }
    
}
