/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo3;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadgrupo3.Models.Context;

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
            Connection con = context.getConexion();
            
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error al cargar los driver de conexion");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error de conexion");
        }
        
    }
    
}
