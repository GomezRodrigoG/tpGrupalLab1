package universidadgrupo3;

import Controller.AlumnoData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Controller.Context;
import Controller.CursadaData;
import universidadgrupo3.Models.Materia;
import Controller.MateriaData;
import java.time.LocalDate;
import universidadgrupo3.Models.Alumno;
import universidadgrupo3.Models.Cursada;


public class UniversidadGrupo3 {

    
    public static void main(String[] args) {
        
        try{
            Context context = new Context();
            
            //------------------------------------
            //-----------Prueba Alumno-----------
            //------------------------------------
            
            AlumnoData aData = new AlumnoData(context);
            Alumno alumno1 = new Alumno("Ramon","Castillo",LocalDate.of(1998, 6, 13),420,true);
            //FUNCIONA//aData.guardarAlumno(alumno1);
            //FUNCIONA//System.out.println(aData.buscarAlumno(406));
            //NO FUNCIONO//aData.eliminarAlumno(123);
            //FUNCIONA//aData.eliminarAlumnoLogico(123);
            //FUNCIONA//aData.actualizarAlumno(alumno1);
            //FUNCIONA//System.out.println(aData.getAllAlumnos());
            
            //------------------------------------
            //-----------Prueba Materia-----------
            //------------------------------------
           
            MateriaData mData = new MateriaData(context);
            Materia materia = new Materia("Ingles II", 3, true);
            //FUNCIONA//mData.guardarMateria(materia);
            //FUNCIONA//System.out.println(mData.buscarMateria(104));
            //LE DA LA BAJA//mData.borrarMateria(114);
            //NO FUNCIONA//mData.modificarMateria(materia);
            //FUNCIONA//System.out.println(mData.obtenerMaterias());
            
            // Connection con = context.getConexion();
            // Statement st = con.createStatement();
           
            
            /*
            mData.guardarMateria(materia);
            
            if(materia.getId_materia() != 0) {
                JOptionPane.showMessageDialog(null,"Se guardo correctamente con el id: ");
            }
            
            List<Materia> materias =  mData.obtenerMaterias();
            
            for(Materia mat: materias){
                System.out.println(mat.getNombre_materia());
            }*/
            
            //------------------------------------
            //---------Prueba Inscripcion---------
            //------------------------------------
            
            CursadaData cData = new CursadaData(context);
            Cursada incripcion = new Cursada(alumno1,materia,9);
            //NO FUNCIONO//cData.guardarCursada(incripcion);
            //FUNCIONA//cData.borrarCursadaDeUnaMateriaDeUnAlumno(1, 104);
            //NO FUNCIONA//cData.actualizarNotaCursada(3, 101, 1);
            //FUNCIONA PERO PUEDE MEJORAR//System.out.println(cData.obtenerMateriasCursadas(1));
            //FUNCIONA//System.out.println(cData.obtenerMateriasNOCursadas(1));
            
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error al cargar los driver de conexion");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error de conexion");
        }
        
    }
    
}