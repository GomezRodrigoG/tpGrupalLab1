/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo3.Models;

/**
 *
 * @author Rodrigo
 */
public class Cursada {
    private int id_cursada;
    private Alumno alumno;
    private Materia materia;
    private double nota;

    public Cursada(int id_cursada, Alumno alumno, Materia materia, double nota) {
        this.id_cursada = id_cursada;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }
    
    public Cursada( Alumno alumno, Materia materia, double nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }
    
    public Cursada() { }

    public int getId_cursada() {
        return id_cursada;
    }

    public void setId_cursada(int id_cursada) {
        this.id_cursada = id_cursada;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Cursada alumno: " + alumno.getNombre() + ", materia: " + materia + ", nota: " + nota +"\n";
    }
    
    
}
