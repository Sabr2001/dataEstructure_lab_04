import java.awt.*;
import javax.swing.*;

public class InterfazGrafica extends JFrame {
    private ColaClientes cola = new ColaClientes();
    private JTextField nombreField, cedulaField, servicioField;
    private JTextArea areaCola;
    private JLabel labelAtendidos;

    public InterfazGrafica() {
        setTitle("Atención al Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());

        // Panel superior con formularios
        JPanel panelIngreso = new JPanel(new GridLayout(4, 2));
        nombreField = new JTextField();
        cedulaField = new JTextField();
        servicioField = new JTextField();
        panelIngreso.add(new JLabel("Nombre:"));
        panelIngreso.add(nombreField);
        panelIngreso.add(new JLabel("Cédula:"));
        panelIngreso.add(cedulaField);
        panelIngreso.add(new JLabel("Servicio:"));
        panelIngreso.add(servicioField);

        JButton btnAgregar = new JButton("Agregar Cliente");
        JButton btnAtender = new JButton("Atender Cliente");
        panelIngreso.add(btnAgregar);
        panelIngreso.add(btnAtender);

        add(panelIngreso, BorderLayout.NORTH);

        // Panel central con el área de cola
        areaCola = new JTextArea(15, 50);
        areaCola.setEditable(false);
        add(new JScrollPane(areaCola), BorderLayout.CENTER);

        // Panel inferior con contador
        JPanel panelInferior = new JPanel();
        labelAtendidos = new JLabel("Clientes atendidos: 0");
        panelInferior.add(labelAtendidos);
        add(panelInferior, BorderLayout.SOUTH);

        // Acciones de botones
        btnAgregar.addActionListener(e -> {
            String nombre = nombreField.getText();
            String cedula = cedulaField.getText();
            String servicio = servicioField.getText();
            if (!nombre.isEmpty() && !cedula.isEmpty() && !servicio.isEmpty()) {
                Cliente c = new Cliente(nombre, cedula, servicio);
                cola.encolar(c);
                actualizarVista();
                nombreField.setText("");
                cedulaField.setText("");
                servicioField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Completa todos los campos.");
            }
        });

        btnAtender.addActionListener(e -> {
            Cliente atendido = cola.atender();
            if (atendido != null) {
                JOptionPane.showMessageDialog(this, "Atendiendo a: " + atendido);
            } else {
                JOptionPane.showMessageDialog(this, "No hay clientes en la cola.");
            }
            actualizarVista();
        });

        setVisible(true);
    }

    private void actualizarVista() {
        areaCola.setText("");
        for (String s : cola.obtenerColaComoTexto()) {
            areaCola.append(s + "\n");
        }
        labelAtendidos.setText("Clientes atendidos: " + cola.getClientesAtendidos());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazGrafica());
    }
}