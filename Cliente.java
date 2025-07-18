public class Cliente {
    private String nombre;
    private String cedula;
    private String servicio;

    public Cliente(String nombre, String cedula, String servicio1) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.servicio = servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getServicio() {
        return servicio;
    }

    @Override
    public String toString() {
        return nombre + " | Cédula: " + cedula + " | Servicio: " + servicio;
    }
}