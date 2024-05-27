package ar.edu.et7.wallet;

import java.io.*;
import java.util.Scanner;

public class Wallet {

    // Función para leer el saldo desde el archivo
    public static double leerSaldo() {
        double saldo = 0.0;
        try (BufferedReader br = new BufferedReader(new FileReader("billetera.txt"))) {
            String linea = br.readLine();
            if (linea != null) {
                saldo = Double.parseDouble(linea);
            }
        } catch (IOException e) {
            System.err.println("No se pudo leer el archivo. Usando saldo inicial 0.");
        }
        return saldo;
    }

    // Función para escribir el saldo en el archivo
    public static void escribirSaldo(double saldo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("billetera.txt"))) {
            bw.write(Double.toString(saldo));
        } catch (IOException e) {
            System.err.println("No se pudo abrir el archivo para escribir el saldo.");
        }
    }

    // Función para realizar un depósito
    public static void deposito(Scanner scanner) {
        System.out.print("Ingrese el importe a depositar: ");
        double importe = scanner.nextDouble();
        
        double saldo = leerSaldo();
        saldo += importe;
        
        escribirSaldo(saldo);
        System.out.println("Depósito realizado con éxito. Saldo actual: " + saldo);
    }

    // Función para realizar una extracción
    public static void extraccion(Scanner scanner) {
        System.out.print("Ingrese el importe a extraer: ");
        double importe = scanner.nextDouble();
        
        double saldo = leerSaldo();
        if (saldo >= importe) {
            saldo -= importe;
            escribirSaldo(saldo);
            System.out.println("Extracción realizada con éxito. Saldo actual: " + saldo);
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    // Función para realizar una transferencia
    public static void transferencia(Scanner scanner) {
        System.out.print("Ingrese el importe a transferir: ");
        double importe = scanner.nextDouble();
        System.out.print("Ingrese el número de celular del destinatario: ");
        String numeroCelular = scanner.next();
        
        double saldo = leerSaldo();
        if (saldo >= importe) {
            saldo -= importe;
            escribirSaldo(saldo);
            System.out.println("Transferencia realizada con éxito a " + numeroCelular + ". Saldo actual: " + saldo);
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    // Función para mostrar el menú y manejar las opciones
    public static void mostrarMenu(Scanner scanner) {
        char opcion;
        do {
            System.out.println("\n--- Menú Billetera Electrónica ---");
            System.out.println("D - Depósito");
            System.out.println("E - Extracción");
            System.out.println("T - Transferencia");
            System.out.println("S - Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.next().charAt(0);
            
            switch (opcion) {
                case 'D':
                case 'd':
                    deposito(scanner);
                    break;
                case 'E':
                case 'e':
                    extraccion(scanner);
                    break;
                case 'T':
                case 't':
                    transferencia(scanner);
                    break;
                case 'S':
                case 's':
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 'S' && opcion != 's');
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        mostrarMenu(scanner);
    }
}
