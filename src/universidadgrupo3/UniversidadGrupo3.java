/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Controller.Context;
import universidadgrupo3.Models.Materia;
import Controller.MateriaData;

/**
 *
 * @author Rodrigo
 */
public class UniversidadGrupo3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            Context context = new Context();
            // Connection con = context.getConexion();
            // Statement st = con.createStatement();
            
            Materia materia = new Materia("Ingles", 1, true);
            
            MateriaData mData = new MateriaData(context);
            
            mData.guardarMateria(materia);
            
            if(materia.getId_materia() != 0) {
                JOptionPane.showMessageDialog(null,"Se guardo correctamente con el id: ");
            }
            
            List<Materia> materias =  mData.obtenerMaterias();
            
            for(Materia mat: materias){
                System.out.println(mat.getNombre_materia());
            }
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error al cargar los driver de conexion");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error de conexion");
        }
        
    }
    
}