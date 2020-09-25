

import java.rmi.*;
import java.util.LinkedList;

public interface Multas extends Remote {

    int ComprobarPuntos(int Dni, String Matricula) throws RemoteException;

    LinkedList ComprobarMultas(int Dni, String Matricula) throws RemoteException;

    int Identificacion(String Password) throws RemoteException;

    int PonerMulta(String Matricula, String Fecha, int Puntos) throws RemoteException;

    int QuitarMulta(String Matricula, String Fecha) throws RemoteException;

    int AltaVehiculo(int Dni, String Matricula) throws RemoteException;

    int BajaVehiculo(int Dni, String Matricula) throws RemoteException;
}
