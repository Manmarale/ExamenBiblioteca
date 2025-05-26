/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DAO.LectorDAO;
import model.Lector;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author alejandro.escudero
 */
public class LectorControlador {
    private LectorDAO lectorDAO;
    private Scanner scanner;

    public LectorControlador() {
        this.lectorDAO = new LectorDAO();
        this.scanner = new Scanner(System.in);
    }
//agrega a un lector 
    public void agregarLector() {
        System.out.println("\n--- Agregar Lector ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Lector lector = new Lector(nombre, email);
        if (lectorDAO.agregarLector(lector)) {
            System.out.println("Lector agregado exitosamente!");
        } else {
            System.out.println("Error al agregar lector.");
        }
    }
//lista a los lectores
    public void listarLectores() {
        System.out.println("\n--- Lista de Lectores ---");
        List<Lector> lectores = lectorDAO.listarLectores();
        if (lectores.isEmpty()) {
            System.out.println("No hay lectores registrados.");
        } else {
            lectores.forEach(System.out::println);
        }
    }
//edita a los lectores
    public void editarLector() {
        System.out.println("\n--- Editar Lector ---");
        System.out.print("ID del lector a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Lector lector = lectorDAO.buscarLectorPorId(id);
        if (lector == null) {
            System.out.println("Lector no encontrado.");
            return;
        }

        System.out.println("Datos actuales: " + lector);
        System.out.print("Nuevo nombre (dejar vacío para no cambiar): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) lector.setNombre(nombre);

        System.out.print("Nuevo email (dejar vacío para no cambiar): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) lector.setEmail(email);

        if (lectorDAO.actualizarLector(lector)) {
            System.out.println("Lector actualizado exitosamente!");
        } else {
            System.out.println("Error al actualizar lector.");
        }
    }
//elimina al lector
    public void eliminarLector() {
        System.out.println("\n--- Eliminar Lector ---");
        System.out.print("ID del lector a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (lectorDAO.eliminarLector(id)) {
            System.out.println("Lector eliminado exitosamente!");
        } else {
            System.out.println("Error al eliminar lector o lector no encontrado.");
        }
    }
}
