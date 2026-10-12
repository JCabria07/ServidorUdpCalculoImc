package juancabria.imc.adaptadores.red;

import juancabria.imc.dominio.enums.EstadoServidor;
import juancabria.imc.dominio.modelos.EventoServidor;

public interface ObservadorServidor {
    void onEvento(EventoServidor evento);
    void onCambioEstado(EstadoServidor estado, int puerto);
}
