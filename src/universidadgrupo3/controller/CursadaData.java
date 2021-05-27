/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo3.controller;

import universidadgrupo3.controller.Context;
import universidadgrupo3.controller.AlumnoData;
import java.sql.Connection;
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
import universidadgrupo3.Models.Cursada;
import universidadgrupo3.Models.Materia;

/**
 *
 * @author PC
 */
public class CursadaData {
    
    private Connection  conexion;
    
    public CursadaData(Context conexion) {
         try {
             this.conexion=conexion.getConexion();
         } catch (SQLException ex) {
             Logger.getLogger(CursadaData.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public void guardarCursada(Cursada cursada){
        String query = "Insert into cursada values (null, ?, ?, ?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cursada.getAlumno().getId_alumno());
            ps.setInt(2, cursada.getMateria().getId_materia());
            ps.setDouble(3, cursada.getNota());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
               cursada.setId_cursada(rs.getInt(1));
               JOptionPane.showMessageDialog(null, "Cursada agregada con existo");
            }
            else{
               JOptionPane.showMessageDialog(null, "Cursada NO se agrego");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Cursada NO se agrego"+ex.getMessage());
        }
        
    }
    
    public List<Cursada> obtenerCursadas(){
        List <Cursada> cursadas = new ArrayList<>();
        
        String query="Select * from cursada";
        try {

            PreparedStatement ps = conexion.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            Context con = new Context();
            AlumnoData alumnoData = new AlumnoData(con);
            MateriaData materiaData = new MateriaData(con);
            
            while(rs.next()){
                Alumno a;
                Materia m;
                a = alumnoData.buscarAlumnoId(rs.getInt("id_alumno"));
                m = materiaData.buscarMateria(rs.getInt("id_materia"));
                Cursada cursada = new Cursada(a, m, 0);
                cursada.setId_cursada(rs.getInt("id_cursada"));
                cursada.setNota(rs.getDouble("nota"));
                cursadas.add(cursada);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al buscar cursadas.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CursadaData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return(cursadas);
    }
    
    public List<Cursada> obtenerCursadasXAlumno(int id){
        List <Cursada> cursadasAlumno = new ArrayList<>();
        
        String query="Select * from cursada where id_alumno=?";
         try {
             
            Context con = new Context();
            AlumnoData alumnoData = new AlumnoData(con);
            MateriaData materiaData = new MateriaData(con);
             
            PreparedStatement ps = conexion.prepareStatement(query);
           
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                do{
                    Alumno a;
                    Materia m;
                    a = alumnoData.buscarAlumnoId(id);
                    m = materiaData.buscarMateria(rs.getInt("id_materia"));
                    Cursada cursada = new Cursada(a, m, 0);
                    cursada.setId_cursada(rs.getInt("id_cursada"));
                    cursada.setNota(rs.getDouble("nota"));
                    cursadasAlumno.add(cursada);
                }while(rs.next());
                ps.close();
            }else{
                JOptionPane.showMessageDialog(null, "No se encontraron cursadas");
            }
            while(rs.next()){
                Alumno a;
                Materia m;
                a = alumnoData.buscarAlumnoId(id);
                m = materiaData.buscarMateria(rs.getInt("id_materia"));
                Cursada cursada = new Cursada(a, m, 0);
                cursada.setId_cursada(rs.getInt("id_cursada"));
                cursada.setNota(rs.getDouble("nota"));
                cursadasAlumno.add(cursada);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al buscar cursadas.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CursadaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return(cursadasAlumno);
    }
     
    public List<Materia> obtenerMateriasCursadas(int id){
        List <Materia> materiasCursadas = new ArrayList<>();
        
        String query="select materia.id_materia, nombre_materia, año, estado from materia, cursada "
                + "where materia.id_materia=cursada.id_materia and cursada.id_alumno=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Materia materia = new Materia();
                
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre_materia(rs.getString("nombre_materia"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                materiasCursadas.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al buscar materias.");
        }
        return(materiasCursadas);
    }
     
    public List<Materia> obtenerMateriasNOCursadas(int id){
        List <Materia> materiasCursadas = new ArrayList<>();
        
        String query="select * from materia where id_materia not in(select materia.id_materia from materia,cursada "
                    + "where materia.id_materia=cursada.id_materia and cursada.id_alumno=?) ";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Materia materia = new Materia();
                
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre_materia(rs.getString("nombre_materia"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                materiasCursadas.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al buscar materias.");
        }
        return(materiasCursadas);
    }
    
    public void borrarCursadaDeUnaMateriaDeUnAlumno(int id_alumno, int id_materia){
        
        String query = "delete from cursada where id_alumno=? and id_materia=?";
        
         try {
             PreparedStatement ps = conexion.prepareStatement(query);
             ps.setInt(1, id_alumno);
             ps.setInt(2, id_materia);
             if(ps.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null,"Cursada borrada.");
             }else {
                JOptionPane.showMessageDialog(null,"Error al borrar cursada");
             }
             ps.close();
         } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al borrar cursada"+ex.getMessage());
         }
        
        
    }
    
    public void actualizarNotaCursada(int id_alumno, int id_materia, double nota){
        
        String query="update cursada set nota=? where id_alumno=? and id_materia=? ";
        
         try {
             PreparedStatement ps = conexion.prepareStatement(query);
             ps.setDouble(1, nota);
             ps.setInt(2, id_alumno);
             ps.setInt(3, id_materia);
             
             if(ps.executeUpdate() ==1){
                 JOptionPane.showMessageDialog(null, "Modificaciones realizadas con exito");
             }else{
                 JOptionPane.showMessageDialog(null, "Error al realizar las modificaciones");
             }
             
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error al realizar las modificaciones"+ex.getMessage());
         }
    }
}
