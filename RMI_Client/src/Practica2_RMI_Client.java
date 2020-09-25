

import java.rmi.Naming;
import java.util.LinkedList;
import java.util.Scanner;

public class Practica2_RMI_Client {

    private static Scanner sc = new Scanner(System.in);

    public static int menu() {
        int opcion;
        do {
            System.out.println("\n\nMenu Consultas");
            System.out.println("*********************\n");
            System.out.println("1.- Identificación");
            System.out.println("2.- Consultar puntos");
            System.out.println("3.- Consultar multas");
            System.out.println("4.- Salir\n");
            opcion = sc.nextInt();
        } while (opcion < 1 || opcion > 4);

        return opcion;
    }

    public static void main(String[] args) {
        try {
            int Puerto = 0;
            String Host;
            System.out.print("Introduce el nº de puerto para comunicarse: ");
            Puerto = sc.nextInt();
            System.out.print("Introduce el nombre del host: ");
            Host = sc.next();

            //Obtiene el stub del rmiregistry
            Multas multasStub = (Multas) Naming.lookup("rmi://" + Host + ":" + Puerto + "/Multas");

            //Variables
            Integer res, opcion, dni, puntosSancion;
            String password, matricula, fecha;
            LinkedList<Sancion> sanciones;

            do {
                opcion = menu();
                switch (opcion) {
                    case 1:
                        System.out.println("Introducir password: ");
                        password = sc.next();

                        res = multasStub.Identificacion(password);
                        if (res == 1) {
                            System.out.println("Identificación correcta");
                            do {
                                System.out.println("\n\nMenú Gestión");
                                System.out.println("*********************\n");
                                System.out.println("1.- Poner Multa");
                                System.out.println("2.- Quitar Multa");
                                System.out.println("3.- Alta de un Vehículo");
                                System.out.println("4.- Baja de un Vehículo");
                                System.out.println("5.- Salir\n");
                                opcion = sc.nextInt();

                                switch (opcion) {
                                    case 1:
                                        System.out.println("Introducir matrícula: ");
                                        matricula = sc.next();
                                        System.out.println("Introducir fecha: ");
                                        fecha = sc.next();
                                        System.out.println("Introducir número de puntos de la sanción: ");
                                        puntosSancion = sc.nextInt();
                                        res = multasStub.PonerMulta(matricula, fecha, puntosSancion);
                                        if (res == 1) {
                                            System.out.println("Multa añadida correctamente. Vehículo con matricula: " + matricula);
                                        } else {
                                            System.out.println("La multa no se ha añadido");
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Introducir matrícula: ");
                                        matricula = sc.next();
                                        System.out.println("Introducir fecha: ");
                                        fecha = sc.next();
                                        res = multasStub.QuitarMulta(matricula, fecha);
                                        if (res == 1) {
                                            System.out.println("Multa borrada del sistema");
                                        } else {
                                            System.out.println("No se ha podido eliminar la multa");
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Introducir dni: ");
                                        dni = sc.nextInt();
                                        System.out.println("Introducir matrícula: ");
                                        matricula = sc.next();
                                        res = multasStub.AltaVehiculo(dni, matricula);
                                        if (res == 1) {
                                            System.out.println("Vehículo dado de alta correctamente");
                                        } else {
                                            System.out.println("El Vehículo no se ha dado de alta");
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Introducir dni: ");
                                        dni = sc.nextInt();
                                        System.out.println("Introducir matrícula: ");
                                        matricula = sc.next();
                                        res = multasStub.BajaVehiculo(dni, matricula);
                                        switch (res) {
                                            case 1:
                                                System.out.println("Vehículo dado de baja correctamente");
                                                break;
                                            case 0:
                                                System.out.println("El Vehículo no se ha dado de baja ya que tiene multas pendientes");
                                                break;
                                            default:
                                                System.out.println("Vehículo no encontrado en el sistema");
                                                break;
                                        }
                                        break;
                                }
                            } while (opcion != 5);
                        } else {
                            System.out.println("Password incorrecto");
                        }
                        break;
                    case 2:
                        System.out.println("Introducir dni: ");
                        dni = sc.nextInt();
                        System.out.println("Introducir matrícula: ");
                        matricula = sc.next();
                        res = multasStub.ComprobarPuntos(dni, matricula);
                        if (res == -1) {
                            System.out.println("Usuario no encontrado en el sistema");
                        } else {
                            System.out.println("El usuario con DNI: " + dni + ", tiene " + res + " puntos");
                        }
                        break;
                    case 3:
                        System.out.println("Introducir dni: ");
                        dni = sc.nextInt();
                        System.out.println("Introducir matrícula: ");
                        matricula = sc.next();
                        sanciones = multasStub.ComprobarMultas(dni, matricula);
                        if (!sanciones.isEmpty()) {
                            System.out.println("Vehiculo con " + sanciones.size() + " multas");
                            for (Sancion s : sanciones) {
                                System.out.println(s + "\n");
                            }
                        } else {
                            System.out.println("El Vehículo indicado no tiene multas");
                        }
                        break;
                }
            } while (opcion != 4);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}
