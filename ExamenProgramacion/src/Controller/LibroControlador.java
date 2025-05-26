/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DAO.LibroDAO;
import model.Libro;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author alejandro.escudero
 */
public class LibroControlador {
    private LibroDAO libroDAO;
    private Scanner scanner;

    public LibroControlador() {
        this.libroDAO = new LibroDAO();
        this.scanner = new Scanner(System.in);
    }

    public void agregarLibro() {
        System.out.println("\n--- Agregar Libro ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Año de publicación: ");
        int anio = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Libro libro = new Libro(titulo, autor, anio);
        if (libroDAO.agregarLibro(libro)) {
            System.out.println("Libro agregado exitosamente!");
        } else {
            System.out.println("Error al agregar libro.");
        }
    }

    public void listarLibros() {
        System.out.println("\n--- Lista de Libros ---");
        List<Libro> libros = libroDAO.listarLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    public void editarLibro() {
        System.out.println("\n--- Editar Libro ---");
        System.out.print("ID del libro a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Libro libro = libroDAO.buscarLibroPorId(id);
        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        System.out.println("Datos actuales: " + libro);
        System.out.print("Nuevo título (dejar vacío para no cambiar): ");
        String titulo = scanner.nextLine();
        if (!titulo.isEmpty()) libro.setTitulo(titulo);

        System.out.print("Nuevo autor (dejar vacío para no cambiar): ");
        String autor = scanner.nextLine();
        if (!autor.isEmpty()) libro.setAutor(autor);

        System.out.print("Nuevo año de publicación (0 para no cambiar): ");
        int anio = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        if (anio != 0) libro.setAnioPublicacion(anio);

        if (libroDAO.actualizarLibro(libro)) {
            System.out.println("Libro actualizado exitosamente!");
        } else {
            System.out.println("Error al actualizar libro.");
        }
    }

    public void eliminarLibro() {
        System.out.println("\n--- Eliminar Libro ---");
        System.out.print("ID del libro a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (libroDAO.eliminarLibro(id)) {
            System.out.println("Libro eliminado exitosamente!");
        } else {
            System.out.println("Error al eliminar libro o libro no encontrado.");
        }
    }
}

