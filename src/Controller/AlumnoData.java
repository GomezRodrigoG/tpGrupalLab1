/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import universidadgrupo3.Models.Alumno;

/**
 *
 * @author Rodrigo
 */
public class AlumnoData {
    
    private Connection conexion ;

    public AlumnoData(Connection conexion) {
        this.conexion = conexion;
    }
    
    
    public void guardarAlumno(Alumno a){
        String query = "INSERT INTO alumno()";
        
        try{
            PreparedStatement ps = conexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getNombre());
            
        } catch(SQLException ex){
            // comentario para probar PR
        }
    }
}
