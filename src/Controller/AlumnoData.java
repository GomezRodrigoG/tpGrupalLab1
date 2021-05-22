/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadgrupo3.Models.Alumno;

/**
 *
 * @author Rodrigo
 */
public class AlumnoData {
    
    private Connection conexion ;

    public AlumnoData(Context conexion) throws SQLException {
        this.conexion = conexion.getConexion();
    }
    
    
  public void guardarAlumno(Alumno a){
       
        String query = "Insert Into alumno (nombre, apellido, fecha_nac, legajo, estado) Values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps=conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
           
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());    
            ps.setDate(3, Date.valueOf(a.getFecha_nac()));
            ps.setInt(4, a.getLegajo());
            ps.setBoolean(5, true);
           
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
                
                if(rs.next()){
                    a.setId_alumno(rs.getInt("id"));    
                    JOptionPane.showMessageDialog(null, "Alumno agregado Con Exíto!");
                 
                }
                else{
                    JOptionPane.showMessageDialog(null, "NO se pudo guardar");
                }
           
                ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al guardar el alumno.");
        }  
    }
    
    public Alumno buscarAlumno(int legajo){
        String query="SELECT * FROM alumno WHERE alumno.legajo = ?";
        Alumno alum= null;
        try {
            PreparedStatement ps = conexion.prepareStatement(query); 
                ps.setInt(1,legajo);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    alum = new Alumno();
                    alum.setId_alumno(rs.getInt("id_alumno"));
                    alum.setNombre(rs.getString("nombre"));
                    alum.setApellido(rs.getString("apellido"));
                    alum.setFecha_nac(rs.getDate("fecha_nac").toLocalDate());
                    alum.setLegajo(rs.getInt("legajo"));
                    alum.setEstado(rs.getBoolean("estado"));
                
            }
                ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return alum;
        
    }
    
    public List<Alumno> getAllAlumnos(){
        String query="SELECT * FROM alumno ";
        Alumno alum;
        List<Alumno> alumno = new ArrayList<>();
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query); 
                 ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    alum = new Alumno();
                    alum.setId_alumno(rs.getInt("id_alumno"));
                    alum.setNombre(rs.getString("nombre"));
                    alum.setApellido(rs.getString("apellido"));
                    alum.setFecha_nac(rs.getDate("fecha_nac").toLocalDate());
                    alum.setLegajo(rs.getInt("legajo"));
                    alum.setEstado(rs.getBoolean("estado"));
                    alumno.add(alum);
                
            }
             ps.close();
            
        } catch (SQLException ex ) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return alumno;
        
    }
    
    public void actualizarAlumno(Alumno a) {
         String query = "UPDATE alumno SET nombre=?,apellido=?,fecha_nac=? WHERE legajo = ?  ";

        try {
            PreparedStatement ps=conexion.prepareStatement(query);
           
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());    
            ps.setDate(3, Date.valueOf(a.getFecha_nac()));
            ps.setInt(4, a.getLegajo());
           
           
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"El alumno ingresado se actualizo");
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"El alumno ingresado no se pudo actualizar");
        }  
    
    }
    
    public void eliminarAlumnoLogico(int legajo){
    String query = "UPDATE alumno SET estado=false WHERE legajo = ?  ";

        try {
            PreparedStatement ps=conexion.prepareStatement(query);
           
            ps.setInt(1,legajo);
                
                       
           
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alumno Dada de Baja");
                ps.close();
        } catch (SQLException ex ) {
            JOptionPane.showMessageDialog(null,"No se pudo eliminar el alumno. SQL");
        }  
    
    }
    
     public void eliminarAlumno(int legajo){
        String query = "DELETE FROM alumno WHERE legajo = ?  ";

        try {
            PreparedStatement ps=conexion.prepareStatement(query);
           
            ps.setInt(1,legajo);
                
                       
           
            ps.executeUpdate();
            
                ps.close();
        } catch (SQLException ex ) {
            JOptionPane.showMessageDialog(null,"No se pudo eliminar el alumno.");
        }  
    
    }
    
}
