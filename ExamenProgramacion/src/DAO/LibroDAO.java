/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import model.Libro;
import model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alejandro.escudero
 */
public class LibroDAO {
    private Connection conexion;

    public LibroDAO() {
        try {
            this.conexion = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
    //operacionesCRUD
    public boolean agregarLibro(Libro libro) {
        String sql = "INSERT INTO libros (titulo, autor, anio_publicacion) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setInt(3, libro.getAnioPublicacion());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al agregar libro: " + e.getMessage());
            return false;
        }
    }
//lista los libros
    public List<Libro> listarLibros() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setAnioPublicacion(rs.getInt("anio_publicacion"));
                libros.add(libro);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar libros: " + e.getMessage());
        }
        return libros;
    }
//actualiza los libros
    public boolean actualizarLibro(Libro libro) {
        String sql = "UPDATE libros SET titulo=?, autor=?, anio_publicacion=? WHERE id=?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setInt(3, libro.getAnioPublicacion());
            pstmt.setInt(4, libro.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar libro: " + e.getMessage());
            return false;
        }
    }
//elimina los libros que quiere el usuario
    public boolean eliminarLibro(int id) {
        String sql = "DELETE FROM libros WHERE id=?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar libro: " + e.getMessage());
            return false;
        }
    }
//busca los libros por id
    public Libro buscarLibroPorId(int id) {
        String sql = "SELECT * FROM libros WHERE id=?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setAnioPublicacion(rs.getInt("anio_publicacion"));
                return libro;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar libro: " + e.getMessage());
        }
        return null;
    }
}