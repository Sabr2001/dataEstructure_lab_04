import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class InterfazGrafica extends JFrame {
    private ColaClientes cola = new ColaClientes();
    private JTextField nombreField, cedulaField, servicioField;
    private JTextArea areaCola;
    private JLabel labelAtendidos;

    public InterfazGrafica() {
        setTitle("Atención al Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
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
        JButton btnImportar = new JButton("Importar Clientes");
        JButton btnCategorizar = new JButton("Ordenar Cola por Tipo");
        JButton btnEstadisticas = new JButton("Ver Estadísticas");

        panelIngreso.add(btnAgregar);
        panelIngreso.add(btnAtender);

        add(panelIngreso, BorderLayout.NORTH);

        // Panel contenedor central que incluye el botón y el área de texto
        JPanel panelCentro = new JPanel(new BorderLayout());

        // Botón categorizar en la parte superior del centro
        JPanel panelBotones = new JPanel(); // Por defecto usa FlowLayout (horizontal)
        panelBotones.add(btnCategorizar);
        panelBotones.add(btnEstadisticas);

        // Agregar ese panel al norte del centro
        panelCentro.add(panelBotones, BorderLayout.NORTH);
        // Área de texto con scroll en el centro del panel
        areaCola = new JTextArea(15, 50);
        areaCola.setEditable(false);
        panelCentro.add(new JScrollPane(areaCola), BorderLayout.CENTER);

        // Finalmente, agregamos TODO el panel al centro del frame
        add(panelCentro, BorderLayout.CENTER);

        // Panel inferior con contador
        JPanel panelInferior = new JPanel();
        labelAtendidos = new JLabel("Clientes atendidos: 0");
        panelInferior.add(labelAtendidos);
        panelInferior.add(btnImportar);
          
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
        
        btnImportar.addActionListener(e->{
            if(cola.estaVacia()){ 
                JOptionPane.showMessageDialog(this, "La cola está vacía.");                
            }else{
                JOptionPane.showMessageDialog(this,cola.importarCola());
            }
        });

        btnCategorizar.addActionListener(e->{
            if(cola.estaVacia()){ 
                JOptionPane.showMessageDialog(this, "La cola está vacía.");                
            }else{
                mostrarClientesPorTipoInterface();
            }
        });

        btnEstadisticas.addActionListener(e -> {
            String resultado = cola.generarEstadisticasASCII();
            areaCola.setText(resultado);
        });
    }

    private void mostrarClientesPorTipoInterface() {
        areaCola.setText("");
        Map<String, List<String>> agrupados = cola.dividirPorTipo();

        for (Map.Entry<String,List<String>> fila : agrupados.entrySet()){
           areaCola.append("Tipo de servicio "+ fila.getKey());

            for (String valor : fila.getValue()) {
                areaCola.append(" - "+ valor);
            }
            areaCola.append("\n");
        }
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