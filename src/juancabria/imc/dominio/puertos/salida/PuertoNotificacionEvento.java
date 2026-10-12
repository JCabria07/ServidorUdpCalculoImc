package juancabria.imc.dominio.puertos.salida;

import juancabria.imc.dominio.enums.EstadoServidor;
import juancabria.imc.dominio.modelos.EventoServidor;

public interface PuertoNotificacionEvento {
    void notificarEvento(EventoServidor evento);
    void notificarCambioEstado(EstadoServidor estado, int puerto);
}
