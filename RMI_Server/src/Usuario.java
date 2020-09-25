
import java.util.Objects;

public class Usuario {

    private Integer Dni;
    private String Matricula;

    public Usuario(Integer Dni, String Matricula) {
        this.Dni = Dni;
        this.Matricula = Matricula;
    }

    public Usuario() {
    }

    public Integer getDni() {
        return Dni;
    }

    public void setDni(Integer Dni) {
        this.Dni = Dni;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String Matricula) {
        this.Matricula = Matricula;
    }

    @Override
    public String toString() {
        return "Usuario con DNI: " + Dni + " y Matricula: " + Matricula;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario) {
            Usuario user = (Usuario) obj;
            return this.Dni.equals(user.Dni) && this.Matricula.equals(user.Matricula);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.Dni);
        hash = 59 * hash + Objects.hashCode(this.Matricula);
        return hash;
    }
}
