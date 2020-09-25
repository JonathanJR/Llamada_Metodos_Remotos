

import java.io.Serializable;

public class Sancion implements Serializable{

    private String Matricula, Fecha;
    private Integer puntos;

    public Sancion(String Matricula, String Fecha, Integer puntos) {
        this.Matricula = Matricula;
        this.Fecha = Fecha;
        this.puntos = puntos;
    }

    public Sancion() {
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String Matricula) {
        this.Matricula = Matricula;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Sancion a vehiculo con Matricula: " + Matricula + ", Fecha: " + Fecha + ", Puntos: " + puntos;
    }

}
