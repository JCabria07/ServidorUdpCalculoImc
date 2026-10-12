package juancabria.imc.dominio.vo;

import juancabria.imc.dominio.excepciones.DestinatarioIncorrectoException;
import java.util.Objects;

public final class Destinatario {
    private final String ip;
    private final PuertoRed puerto;

    public Destinatario(final String ip, final PuertoRed puerto) {
        if (ip == null || ip.trim().isEmpty()) {
            throw new DestinatarioIncorrectoException("La dirección IP del destinatario es obligatoria.");
        }
        this.ip = ip.trim();
        this.puerto = Objects.requireNonNull(puerto, "El puerto del destinatario es obligatorio.");
    }

    public String ip() {
        return ip;
    }

    public int puerto() {
        return puerto.valor();
    }

    public String endpoint() {
        return ip + ":" + puerto.valor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destinatario that = (Destinatario) o;
        return ip.equals(that.ip) && puerto.equals(that.puerto);
    }

    @Override
    public int hashCode() {
        return 31 * ip.hashCode() + puerto.hashCode();
    }

    @Override
    public String toString() {
        return endpoint();
    }
}
