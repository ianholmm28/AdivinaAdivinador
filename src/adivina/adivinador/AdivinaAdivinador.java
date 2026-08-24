/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package adivina.adivinador;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author holmian,Valentino cinti, Piston Lautaro
 */
public class AdivinaAdivinador {
    public static void main(String[] args) {
        
       // Random random = new Random();
        Scanner sc = new Scanner(System.in);
        
        List<Personaje> listaPersonajes = new ArrayList<>();
        
            for (int x=0; x<23; x++){
                listaPersonajes.add(new Personaje(x));
            }
            
            //String nombre = sc.nextLine();
            System.out.println(listaPersonajes);
            
        }
        
        
    }
