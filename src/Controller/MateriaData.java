/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadgrupo3.Models.Materia;

/**
 *
 * @author Rodrigo
 */
public class MateriaData {
    private Connection conexion ;

    public MateriaData(Context conexion) throws SQLException {
        this.conexion = conexion.getConexion();
    }
    
    public void guardarMateria(Materia materia){
        String query = "INSERT INTO materia VALUES (null, ?, ?, ?)";
        
        try {
            PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, materia.getNombre_materia());
            statement.setInt(2, materia.getAnio());
            statement.setBoolean(3, materia.isEstado());
            
            statement.executeUpdate();
            
            ResultSet rSet = statement.getGeneratedKeys();
            
            if(rSet.next()){
                materia.setId_materia(rSet.getInt(1));
                JOptionPane.showMessageDialog(null,"Materia guardada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null,"No se guardo.");
            }
            
            statement.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al guardar una materia.");
        }
    }
    
    public Materia buscarMateria(int id){
        Materia materia = null;
        
        String query = "SELECT * FROM materia WHERE id_materia = ? ";
        
        try {
            PreparedStatement statement = conexion.prepareStatement(query);
            
            statement.setInt(1, id);
            
            ResultSet rSet = statement.executeQuery();
            
            if(rSet.next()){
                materia = new Materia();
                
                materia.setId_materia(rSet.getInt("id_materia"));
                materia.setNombre_materia(rSet.getString("nombre_materia"));
                materia.setAnio(rSet.getInt("año"));
                materia.setEstado(rSet.getBoolean("estado"));
                
            } else {
                JOptionPane.showMessageDialog(null,"Materia no encontrada.");
            }
            
            statement.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al buscar materia.");
        }
        
        return materia;
    }
    
    public ArrayList<Materia> obtenerMaterias(){
        ArrayList<Materia> toRet = new ArrayList<>();
        
        String query = "SELECT * FROM materia";
        
        try {
            PreparedStatement statement = conexion.prepareStatement(query);
            
            ResultSet rSet = statement.executeQuery();
            
            while(rSet.next()){
                Materia materia = new Materia();
                
                materia.setId_materia(rSet.getInt(1));
                materia.setNombre_materia(rSet.getString(2));
                materia.setAnio(rSet.getInt(3));
                materia.setEstado(rSet.getBoolean(4));
                
                toRet.add(materia);
            }
            
            statement.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al buscar materias.");
        }
        
        return toRet;
    }

    public void borrarMateria(int id){
        String query = "UPDATE materia SET estado = false WHERE id_materia = ? ";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
                      
            if(ps.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null,"Materia dada de baja");
            }else {
                JOptionPane.showMessageDialog(null,"La materia a la que le quiere dar la baja no existe");
            }
            
            ps.close();
    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al dar de baja a una materia. SQL");
        }
    }
    
    public void modificarMateria(Materia materia){
        String query = "UPDATE materia SET nombre_materia = ? , año = ? WHERE id_materia = ?";
        
        try {
            PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, materia.getNombre_materia());
            statement.setInt(2, materia.getAnio());
            statement.setInt(3, materia.getId_materia());
            
            if(statement.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null,"Materia actualizada con exito.");
            } else {
                JOptionPane.showMessageDialog(null,"No se actualizo.");
            }
            
            statement.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al guardar una materia.");
        }
    }
}
