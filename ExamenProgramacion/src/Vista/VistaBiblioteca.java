/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import Controller.LibroControlador;
import Controller.LectorControlador;
import java.util.Scanner;
/**
 *
 * @author alejandro.escudero
 */
public class VistaBiblioteca {
    private Scanner scanner;
    private LibroControlador libroControlador;
    private LectorControlador lectorControlador;
//scanner y controladores
    public VistaBiblioteca() {
        this.scanner = new Scanner(System.in);
        this.libroControlador = new LibroControlador();
        this.lectorControlador = new LectorControlador();
    }
//menu
    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n=== Sistema de Gestión de Biblioteca ===");
            System.out.println("1. Gestionar Libros");
            System.out.println("2. Gestionar Lectores");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            //paso a un rule Switch porque me lo recomienda el IDE con todos los Switch case
            switch (opcion) {
                case 1 -> gestionarLibros();
                case 2 -> gestionarLectores();
                case 3 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 3);
    }

    private void gestionarLibros() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Libros ---");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Listar Libros");
            System.out.println("3. Editar Libro");
            System.out.println("4. Eliminar Libro");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1 -> libroControlador.agregarLibro();
                case 2 -> libroControlador.listarLibros();
                case 3 -> libroControlador.editarLibro();
                case 4 -> libroControlador.eliminarLibro();
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private void gestionarLectores() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Lectores ---");
            System.out.println("1. Agregar Lector");
            System.out.println("2. Listar Lectores");
            System.out.println("3. Editar Lector");
            System.out.println("4. Eliminar Lector");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1 -> lectorControlador.agregarLector();
                case 2 -> lectorControlador.listarLectores();
                case 3 -> lectorControlador.editarLector();
                case 4 -> lectorControlador.eliminarLector();
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }
}

