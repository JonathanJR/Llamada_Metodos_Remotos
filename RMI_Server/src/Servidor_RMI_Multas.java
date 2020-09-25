

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Servidor_RMI_Multas {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        try {
            int Puerto = 0;
            Scanner Teclado = new Scanner(System.in);
            System.out.print("Introduce el nº de puerto para comunicarse: ");
            Puerto = Teclado.nextInt();
            Registry registry = LocateRegistry.createRegistry(Puerto);
            MultasImpl obj = new MultasImpl();
            Multas stub = (Multas) UnicastRemoteObject.exportObject(obj, Puerto);
            registry = LocateRegistry.getRegistry(Puerto);
            registry.bind("Multas", stub);
            System.out.println("Servidor Multas esperando peticiones ... ");
        } catch (Exception e) {
            System.out.println("Error en servidor Multas:" + e);
        }

        /*
        try {
            Multas multasStub = new MultasImpl();
            int Puerto = 0;
            Scanner Teclado = new Scanner(System.in);
            System.out.print("Introduce el nº de puerto para comunicarse: ");
            Puerto = Teclado.nextInt();
            Naming.rebind("rmi://localhost:" + Puerto + "/Multas", multasStub);
            System.out.println("Servidor Multas esperando peticiones ... ");    //Naming.unbind("rmi://localhost:"+Puerto+"/Calculadora");} catch(Exception e) {System.out.println("Error en servidor Calculadora:"+e);}  
        } catch (Exception e) {
            System.out.println("Error en servidor Multas:" + e);
        }*/
    }
}
