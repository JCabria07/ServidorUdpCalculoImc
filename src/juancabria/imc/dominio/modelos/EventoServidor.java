package juancabria.imc.dominio.modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class EventoServidor {
    private final String tipo;
    private final String origen;
    private final String descripcion;
    private final Date timestamp;

    public EventoServidor(final String tipo, final String origen, final String descripcion) {
        this.tipo = tipo;
        this.origen = origen;
        this.descripcion = descripcion;
        this.timestamp = new Date();
    }

    public String getTipo() {
        return tipo;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getFormatoLog() {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        if (origen == null || origen.trim().isEmpty()) {
            return f.format(timestamp) + " - " + descripcion;
        }
        return origen + " -> " + f.format(timestamp) + " - " + descripcion;
    }
}
