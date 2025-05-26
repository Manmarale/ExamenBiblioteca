/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author alejandro.escudero
 */
import model.Lector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DatabaseConnection;

public class LectorDAO {
    private Connection conexion;

    public LectorDAO() {
        try {
            this.conexion = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    //operaciones CRUD
    public boolean agregarLector(Lector lector) {
        String sql = "INSERT INTO lectores (nombre, email) VALUES (?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, lector.getNombre());
            pstmt.setString(2, lector.getEmail());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al agregar lector: " + e.getMessage());
            return false;
        }
    }
//lista los lectores
    public List<Lector> listarLectores() {
        List<Lector> lectores = new ArrayList<>();
        String sql = "SELECT * FROM lectores";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Lector lector = new Lector();
                lector.setId(rs.getInt("id"));
                lector.setNombre(rs.getString("nombre"));
                lector.setEmail(rs.getString("email"));
                lectores.add(lector);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar lectores: " + e.getMessage());
        }
        return lectores;
    }
//actualiza al lector
    public boolean actualizarLector(Lector lector) {
        String sql = "UPDATE lectores SET nombre=?, email=? WHERE id=?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, lector.getNombre());
            pstmt.setString(2, lector.getEmail());
            pstmt.setInt(3, lector.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar lector: " + e.getMessage());
            return false;
        }
    }
//elimina al lector
    public boolean eliminarLector(int id) {
        String sql = "DELETE FROM lectores WHERE id=?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar lector: " + e.getMessage());
            return false;
        }
    }
//busca a los lectores por id
    public Lector buscarLectorPorId(int id) {
        String sql = "SELECT * FROM lectores WHERE id=?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Lector lector = new Lector();
                lector.setId(rs.getInt("id"));
                lector.setNombre(rs.getString("nombre"));
                lector.setEmail(rs.getString("email"));
                return lector;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar lector: " + e.getMessage());
        }
        return null;
    }
}
