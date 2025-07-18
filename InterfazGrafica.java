import java.awt.*;
import javax.swing.*;

public class InterfazGrafica extends JFrame {
    private ColaClientes cola = new ColaClientes();
    private JTextField nombreField, cedulaField, buscarField;
    private JTextArea areaCola, areaHistorial;
    private JLabel labelAtendidos;

    public InterfazGrafica() {
        setTitle("Atención al Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelIngreso = new JPanel(new GridLayout(5, 2, 5, 5));
        nombreField = new JTextField();
        cedulaField = new JTextField();
        buscarField = new JTextField();

        panelIngreso.add(new JLabel("Nombre:"));
        panelIngreso.add(nombreField);
        panelIngreso.add(new JLabel("Cédula:"));
        panelIngreso.add(cedulaField);

        JButton btnAgregar = new JButton("Agregar Cliente");
        JButton btnAtender = new JButton("Atender Cliente");
        panelIngreso.add(btnAgregar);
        panelIngreso.add(btnAtender);

        panelIngreso.add(new JLabel("Buscar Cliente (nombre o cédula):"));
        panelIngreso.add(buscarField);
        JButton btnBuscar = new JButton("Buscar Cliente");
        panelIngreso.add(btnBuscar);

        add(panelIngreso, BorderLayout.NORTH);

        // Panel central y la cola y el Historial
        JPanel panelCentro = new JPanel(new GridLayout(1, 2));

        areaCola = new JTextArea();
        areaCola.setEditable(false);
        panelCentro.add(new JScrollPane(areaCola));

        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        panelCentro.add(new JScrollPane(areaHistorial));

        add(panelCentro, BorderLayout.CENTER);

        // Panel inferior
        JPanel panelInferior = new JPanel(new GridLayout(1, 2));
        labelAtendidos = new JLabel("Clientes atendidos: 0");
        JButton btnPromedio = new JButton("Ver Promedio Espera");

        panelInferior.add(labelAtendidos);
        panelInferior.add(btnPromedio);

        add(panelInferior, BorderLayout.SOUTH);

        // Acción para agregar cliente
        btnAgregar.addActionListener(e -> {
            String nombre = nombreField.getText().trim();
            String cedula = cedulaField.getText().trim();
            if (!nombre.isEmpty() && !cedula.isEmpty()) {
                Cliente c = new Cliente(nombre, cedula);
                cola.encolar(c);
                actualizarVista();
                nombreField.setText("");
                cedulaField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.");
            }
        });

        // Acción para atender cliente
        btnAtender.addActionListener(e -> {
            Cliente atendido = cola.atenderCliente();
            if (atendido != null) {
                int tiempo = cola.getUtimoTiempo();
                JOptionPane.showMessageDialog(this, "Atendiendo a: " + atendido + "\nTiempo estimado: " + tiempo + " minutos");
            } else {
                JOptionPane.showMessageDialog(this, "No hay clientes en la cola.");
            }
            actualizarVista();
        });

        // Acción para ver el promedio
        btnPromedio.addActionListener(e -> {
            double promedio = cola.mostrarPromedioEspera();
            JOptionPane.showMessageDialog(this, "Promedio de espera: " + String.format("%.2f", promedio) + " minutos");
        });

        // Acción para buscar cliente
        btnBuscar.addActionListener(e -> {
            String criterio = buscarField.getText().trim();
            if (!criterio.isEmpty()) {
                boolean encontrado = cola.buscarCliente(criterio);
                String mensaje = encontrado ? "El cliente está en la cola." : "El cliente NO está en la cola.";
                JOptionPane.showMessageDialog(this, mensaje);
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese un nombre o cédula para buscar.");
            }
        });

        setVisible(true);
    }

    private void actualizarVista() {
        areaCola.setText("Clientes en cola:\n");
        for (String c : cola.obtenerClientesEnColaComoTexto()) {
            areaCola.append(c + "\n");
        }

        areaHistorial.setText("Historial de atendidos:\n");
        for (String h : cola.obtenerHistorialComoTexto()) {
            areaHistorial.append(h + "\n");
        }

        labelAtendidos.setText("Clientes atendidos: " + cola.getClientesAtendidos());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazGrafica());
    }
}
