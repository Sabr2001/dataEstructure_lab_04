import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ColaClientes cola = new ColaClientes();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Servicio ---");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Atender cliente");
            System.out.println("3. Ver cola");
            System.out.println("4. Ver total de clientes atendidos");
            System.out.println("5. Opcion");
            System.out.print("0. Salir: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                 System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Cedula: ");
                String cedula = sc.nextLine();
                System.out.print("Servicio: ");
                String servicio = sc.nextLine();
               cola.encolar(new Cliente(nombre, cedula, servicio));
               System.out.println("Servicio importado: " + nombre + " / cedula: " + cedula + " / servicio " + servicio); 
               
    break;
                case 2:
                    Cliente atendido = cola.atenderCliente();
                    if (atendido != null)
                        System.out.println("Atendiendo a: " + atendido);
                    else
                        System.out.println("No hay clientes en la cola.");
                    break;
                case 3:
                    if (cola.estaVacia()) {
                        System.out.println("La cola está vacía.");
                    } else {
                        System.out.println("Clientes en espera:");
                        for (String s : cola.obtenerClientesEnCola()) {
                            System.out.println("- " + s);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Clientes atendidos: " + cola.getClientesAtendidos());
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
        sc.close();
    }
}
