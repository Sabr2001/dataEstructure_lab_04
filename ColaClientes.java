import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class ColaClientes {
    private Queue<Cliente> cola;
    private int clientesAtendidos;

    public ColaClientes() {
        cola = new LinkedList<>(); //Crea la cola de clientes como lista enlazada
        clientesAtendidos = 0;
    }

    public void encolar(Cliente c) {
        cola.add(c); //añade un cliente
    }

    public Cliente atender() {
        if (!cola.isEmpty()) {
            clientesAtendidos++; //suma en el contador el cliente atendido.
            return cola.poll(); //vacia la cola
        }
        return null; // si esta vacia retorna null
    }

    public ArrayList<String> obtenerColaComoTexto() {
        ArrayList<String> lista = new ArrayList<>();
        for (Cliente cliente : cola) {
            lista.add(cliente.toString());
        }
        return lista;
    }

    public int getClientesAtendidos() {
        return clientesAtendidos;
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public String importarCola() {

        String respuesta = "";
        String rutaDescargas = System.getProperty("user.home") + File.separator + "Desktop";
        File archivo = new File(rutaDescargas,"clientes.txt");

        try(BufferedWriter editorDeArchivo = new BufferedWriter(new FileWriter(archivo))) {
            for (Cliente cliente : cola) {

            editorDeArchivo.write(cliente.toString());
            editorDeArchivo.newLine();
            }
            respuesta = "Archivo guardado en: " + archivo.getAbsolutePath();
             

        } catch (IOException e) {
            System.err.println("Error!! \n No se pudo realizar la importacion de los datos");
            respuesta = "Error!! \n No se pudo realizar la importacion de los datos";
        }
        return respuesta;  
    }
}
 