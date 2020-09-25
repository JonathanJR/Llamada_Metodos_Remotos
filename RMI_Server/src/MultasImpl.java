
import java.rmi.RemoteException;
import java.util.LinkedList;

public class MultasImpl implements Multas {

    private Usuario usuario;
    private Sancion sancion;
    private LinkedList<Sancion> sanciones;
    private LinkedList<Sancion> aux;
    private LinkedList<Usuario> usuarios;

    public MultasImpl() {
        this.usuario = new Usuario();
        this.sancion = new Sancion();
        this.sanciones = new LinkedList<>();
        this.usuarios = new LinkedList<>();
    }

    public boolean existeUsuario(Usuario u) {
        return usuarios.stream().anyMatch((user) -> (user.equals(u)));
    }

    /*
    Dado los datos de un usuario (Dni y Matricula) la función comprobarán que 
    el vehículo está dado de alta y devolverá los puntos asociados al usuario. 
    Si no se encuentra el usuario devolverá -1
     */
    @Override
    public int ComprobarPuntos(int Dni, String Matricula) throws RemoteException {
        int i = 0;
        usuario = new Usuario(Dni, Matricula);

        if (existeUsuario(usuario)) {
            for (Sancion s : sanciones) {
                if (s.getMatricula().equals(Matricula)) {
                    i = i + s.getPuntos();
                }
            }
            return 15 - i;
        } else {
            return -1;
        }
    }

    /*
    Dado los datos de un usuario (Dni y Matricula) la función comprobará que 
    el vehículo está dado de alta y devolverá los datos de las multas mediante 
    una lista de objetos que contienen los atributos Matricula, Fecha 
    y Puntos perdidos.
     */
    @Override
    public LinkedList ComprobarMultas(int Dni, String Matricula) throws RemoteException {
        aux = new LinkedList<>();
        usuario = new Usuario(Dni, Matricula);

        if (existeUsuario(usuario)) {
            for (Sancion s : sanciones) {
                if (s.getMatricula().equals(Matricula)) {
                    sancion = new Sancion(Matricula, s.getFecha(), s.getPuntos());
                    aux.add(sancion);
                    System.out.println(sancion);
                }
            }
        }
        return aux;
    }

    /*
    Dado el password del agente la función comprobará si coincide con el 
    541293AGP. Si coincide devuelve 1 si no devuelve 0
     */
    @Override
    public int Identificacion(String Password) throws RemoteException {
        if (Password.equals("1")) {
            return 1;
        } else {
            return 0;
        }
    }

    /*
    Dado la sanción (Matricula, Fecha y Puntos perdidos) la almacenará en los 
    datos del Servidor, siempre y cuando la matricula exista. Devuelve 1 si se 
    ha puesto la multa y 0 en caso contrario.
     */
    @Override
    public int PonerMulta(String Matricula, String Fecha, int Puntos) throws RemoteException {
        sancion = new Sancion(Matricula, Fecha, Puntos);
        for (Usuario u : usuarios) {
            if (u.getMatricula().equals(Matricula)) {
                sanciones.add(sancion);
                System.out.println("Multa puesta correctamente: " + sancion);
                return 1;
            }
        }
        return 0;
    }

    /*
    Dado una Matricula y una Fecha, la función restaurará los puntos perdidos 
    por la sanción al usuario del vehículo y eliminado la sanción de los datos 
    del servidor. Devuelve 1 si se ha quitado la multa y 0 en caso contrario
     */
    @Override
    public int QuitarMulta(String Matricula, String Fecha) throws RemoteException {
        aux = sanciones;
        for (Usuario u : usuarios) {
            if (u.getMatricula().equals(Matricula)) {
                for (Sancion s : aux) {
                    if (s.getMatricula().equals(Matricula) && s.getFecha().equals(Fecha)) {
                        sanciones.remove(s);
                        System.out.println("Multa quitada correctamente");
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    /*
    Dado los datos de un usuario (Dni y Matricula) se da de alta al vehículo 
    en los datos del servidor si previamente no existía dicha matricula. 
    Si se ha dado de alta devolverá 1 en caso contrario 0.
     */
    @Override
    public int AltaVehiculo(int Dni, String Matricula) throws RemoteException {
        usuario = new Usuario(Dni, Matricula);
        if (!existeUsuario(usuario)) {
            usuarios.add(usuario);
            System.out.println(usuario);
            return 1;
        } else {
            return 0;
        }
    }

    /*
    Dado los datos de un usuario (Dni y Matricula) se da de baja al vehículo en 
    los datos del servidor si previamente existe y no tiene ninguna multa. 
    Si se ha dado de baja devolverá 1 en caso contrario 0.
     */
    @Override
    public int BajaVehiculo(int Dni, String Matricula) throws RemoteException {
        usuario = new Usuario(Dni, Matricula);
        if (existeUsuario(usuario)) {
            for (Sancion s : sanciones) {
                if (s.getMatricula().equals(Matricula)) {
                    return 0;
                }
            }
            usuarios.remove(usuario);
            System.out.println("Usuario " + usuario + " eliminador");
            return 1;
        } else {
            return 0;
        }
    }
}
