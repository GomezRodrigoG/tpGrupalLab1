/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo3.Vistas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import universidadgrupo3.Models.Alumno;
import universidadgrupo3.Models.Cursada;
import universidadgrupo3.Models.Materia;
import universidadgrupo3.controller.AlumnoData;
import universidadgrupo3.controller.Context;
import universidadgrupo3.controller.CursadaData;
import universidadgrupo3.controller.MateriaData;

/**
 *
 * @author PC
 */
public class InscripcionView extends javax.swing.JInternalFrame {
private DefaultTableModel modelo;
    private ArrayList<Cursada> listaCursadas;
    private ArrayList<Materia> listaMaterias;
    private CursadaData cursadaData;
    private MateriaData materiaData;
    private AlumnoData alumnoData;
    private ArrayList<Alumno> listaAlumnos;
     private List<Materia> listaNoCursadas;
    private Materia materia;
    private Alumno alumno;
    
    /**
   
     * @param conexion
     * @throws java.sql.SQLException
     */
    public InscripcionView(Context conexion) throws SQLException {
        initComponents();

        modelo = new DefaultTableModel();
        cursadaData = new CursadaData(conexion);
        listaCursadas = (ArrayList)cursadaData.obtenerCursadas();
        materiaData = new MateriaData(conexion);
        listaMaterias = (ArrayList)materiaData.obtenerMaterias();
        alumnoData = new AlumnoData(conexion);
        listaAlumnos = (ArrayList)alumnoData.getAllAlumnos();
        
        cargarAlumnos();
        armarCabeceraTabla();
        //cargarDatos();
        //cargarNoInscriptas();
        
    }
    
   
    public void cargarAlumnos(){
        for(Alumno item:listaAlumnos){
            
            if(item.isEstado()){
                jcbAlumnos.addItem(item);
              
            }
        }
    }
    public void armarCabeceraTabla(){
        ArrayList <Object> columnas = new ArrayList<>();
        columnas.add("ID");
        columnas.add("Materia");
        columnas.add("Año");
        columnas.add("Estado");
        
        for(Object it:columnas){
            modelo.addColumn(it);
        }
        jMaterias.setModel(modelo);
    }
    
      public void borrarFilasTabla(){
       int a = modelo.getRowCount()-1;
       for(int i=a; i>=0; i--){
           modelo.removeRow(i);
       }
    }
      
      public void cargarNoInscriptas(){
          //carga datos de las materias no cursadas.
          borrarFilasTabla();
        Alumno al = (Alumno)jcbAlumnos.getSelectedItem();
        
        for(Materia i:cursadaData.obtenerMateriasNOCursadas(al.getId_alumno())){
            if(i.isEstado()){
          modelo.addRow(new Object[]{i.getId_materia(), i.getNombre_materia(), i.getAnio(),i.isEstado() });
                    }
        }
    }
      
    public void cargarDatos(){
        //carga datos de las materias cursadas
        borrarFilasTabla();
        Alumno al = (Alumno)jcbAlumnos.getSelectedItem();
        for(Cursada i:cursadaData.obtenerCursadas()){
            if(i.getAlumno().getId_alumno()==al.getId_alumno()){
                modelo.addRow(new Object[]{i.getMateria().getId_materia(),i.getMateria().getNombre_materia(),i.getMateria().getAnio(),i.getMateria().isEstado() });
            }
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jrbInscriptas = new javax.swing.JRadioButton();
        jrbNoInscriptas = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jMaterias = new javax.swing.JTable();
        jbinscribir = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jbAnular = new javax.swing.JButton();
        jcbAlumnos = new javax.swing.JComboBox<>();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("FORMULARIO DE INSCRIPCION");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("ALUMNOS");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("LISTADO DE MATERIAS");

        jrbInscriptas.setText("Inscriptas");
        jrbInscriptas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbInscriptasActionPerformed(evt);
            }
        });

        jrbNoInscriptas.setText("No Inscriptas");
        jrbNoInscriptas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbNoInscriptasActionPerformed(evt);
            }
        });

        jMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jMaterias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMateriasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jMaterias);

        jbinscribir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbinscribir.setText("INSCRIBIR");
        jbinscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbinscribirActionPerformed(evt);
            }
        });

        jbSalir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbSalir.setText("SALIR");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        jbAnular.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbAnular.setText("ANULAR INSCRIPCION");
        jbAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnularActionPerformed(evt);
            }
        });

        jcbAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAlumnosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jbinscribir)
                        .addGap(34, 34, 34)
                        .addComponent(jbAnular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jrbInscriptas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jrbNoInscriptas)
                                .addGap(73, 73, 73))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jcbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(123, 123, 123))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbInscriptas)
                    .addComponent(jrbNoInscriptas))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbinscribir)
                    .addComponent(jbSalir)
                    .addComponent(jbAnular))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbinscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbinscribirActionPerformed
       
          Alumno alu = (Alumno)jcbAlumnos.getSelectedItem();
          //obtengo el alumno seleccionado 
            alumno=alumnoData.buscarAlumnoId(alu.getId_alumno());
            //creo una cursada
            Cursada cur = new Cursada(alumno,materia,0);
            //Inscribo el alumno a es materia  
            cursadaData.guardarCursada(cur);
            cargarNoInscriptas();
        
// TODO add your handling code here:
    }//GEN-LAST:event_jbinscribirActionPerformed

    private void jMateriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMateriasMouseClicked
       int seleccion=jMaterias.getSelectedRow();
       //Obtengo la materia seleccionada.
       materia= materiaData.buscarMateria(Integer.parseInt(jMaterias.getValueAt(seleccion,0).toString()));
    }//GEN-LAST:event_jMateriasMouseClicked

    private void jcbAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAlumnosActionPerformed
      if(jrbInscriptas.isSelected()){
      cargarDatos();
      }else
          if(jrbNoInscriptas.isSelected()){
              cargarNoInscriptas();
              
          }
       
        
      
        
    }//GEN-LAST:event_jcbAlumnosActionPerformed

    private void jrbInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbInscriptasActionPerformed
      //carga materias no inscriptas
        cargarDatos();  // TODO add your handling code here:
    }//GEN-LAST:event_jrbInscriptasActionPerformed

    private void jrbNoInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbNoInscriptasActionPerformed
    //carga materias no inscriptas
        cargarNoInscriptas();   
// TODO add your handling code here:
    }//GEN-LAST:event_jrbNoInscriptasActionPerformed

    private void jbAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnularActionPerformed
      Alumno alu = (Alumno)jcbAlumnos.getSelectedItem();
      //Elimina la cursada de la materia y alumno selecionado.
          cursadaData.borrarCursadaDeUnaMateriaDeUnAlumno(alu.getId_alumno(),materia.getId_materia());
    }//GEN-LAST:event_jbAnularActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTable jMaterias;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbAnular;
    private javax.swing.JButton jbSalir;
    private javax.swing.JButton jbinscribir;
    private javax.swing.JComboBox<Alumno> jcbAlumnos;
    private javax.swing.JRadioButton jrbInscriptas;
    private javax.swing.JRadioButton jrbNoInscriptas;
    // End of variables declaration//GEN-END:variables
}
