import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;


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
        ArrayList<String> lista = new ArrayList<>(); //Instanciamos una  nueva lista
        for (Cliente cliente : cola) {              //Recorremos la lista que almacena los datos
            lista.add(cliente.toString());         //cada dato recorrido se guarda como un String
        }
        return lista;                           //Devolvemos la lista que contiene los datos formateado
    }

    public int getClientesAtendidos() {
        return clientesAtendidos;  //Obtenemos la cantidad de clientes antendidos
    }

    public boolean estaVacia() {
        return cola.isEmpty();  //validamos si la cola esta vacia
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
<<<<<<< Updated upstream
=======
    }

    public ArrayList<String> dividirPorTipo() {
        Map <String, List<String>> listaPorTipo = new HasMap<>();
            for (Cliente cliente : cola) {
                System.out.println("[DEBUG] Cliente: " + cliente.getServicio());
                listaPorTipo.add(cliente.toString());
            }
        return listaPorTipo;
>>>>>>> Stashed changes
    }

    public ArrayList<String> dividirPorTipo() {
        Map <String, List<String>> listaPorTipo = new HashMap<>();
            for (Cliente cliente : cola) {
                String tipo = cliente.getServicio();
                String clienteData = cliente.getCedula() + cliente.getNombre();

                System.out.println("[DEBUG] Cliente: " + cliente.getServicio());
                
                listaPorTipo.putIfAbsent(tipo, new ArrayList<>());

                listaPorTipo.get(tipo).add(clienteData);
            }
            return listaPorTipo;
    }
}
 