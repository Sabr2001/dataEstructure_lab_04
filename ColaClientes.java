import java.util.*;

public class ColaClientes {
    private Queue<Cliente> cola = new LinkedList<>();
    private List<Cliente> historial = new ArrayList<>();
    private List<Integer> tiemposEspera = new ArrayList<>();
    private Random random = new Random();
    private int ultimoTiempo = 0;

    public void encolar(Cliente c) {
        cola.offer(c);
    }

    public Cliente atenderCliente() {
        Cliente cliente = cola.poll();
        if (cliente != null) {
            historial.add(cliente);
            ultimoTiempo = random.nextInt(11) + 5; // entre 5 y 15 min
            tiemposEspera.add(ultimoTiempo);
        }
        return cliente;
    }

    public int getUtimoTiempo() {
        return ultimoTiempo;
    }

    public int getClientesAtendidos() {
        return historial.size();
    }

    public List<String> obtenerClientesEnColaComoTexto() {
        List<String> resultado = new ArrayList<>();
        for (Cliente c : cola) {
            resultado.add(c.toString());
        }
        return resultado;
    }

    public List<String> obtenerHistorialComoTexto() {
        List<String> resultado = new ArrayList<>();
        for (Cliente c : historial) {
            resultado.add(c.toString());
        }
        return resultado;
    }

    public boolean buscarCliente(String criterio) {
        for (Cliente c : cola) {
            if (c.getNombre().equalsIgnoreCase(criterio) || c.getCedula().equals(criterio)) {
                return true;
            }
        }
        return false;
    }

    public double mostrarPromedioEspera() {
        if (tiemposEspera.isEmpty()) return 0;
        int suma = 0;
        for (int t : tiemposEspera) suma += t;
        return (double) suma / tiemposEspera.size();
    }

    public boolean estaVacia() {
        
        throw new UnsupportedOperationException("Unimplemented method 'estaVacia'");
    }

    public String[] obtenerClientesEnCola() {
        
        throw new UnsupportedOperationException("Unimplemented method 'obtenerClientesEnCola'");
    }
}
