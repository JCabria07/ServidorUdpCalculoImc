package juancabria.imc.adaptadores.notificacion;

import juancabria.imc.adaptadores.red.ObservadorServidor;
import juancabria.imc.dominio.enums.EstadoServidor;
import juancabria.imc.dominio.modelos.EventoServidor;
import juancabria.imc.dominio.puertos.salida.PuertoNotificacionEvento;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class AdaptadorNotificacionEvento implements PuertoNotificacionEvento {
    private final List<ObservadorServidor> observadores = new CopyOnWriteArrayList<>();

    public void registrarObservador(final ObservadorServidor observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void removerObservador(final ObservadorServidor observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarEvento(final EventoServidor evento) {
        for (ObservadorServidor obs : observadores) {
            obs.onEvento(evento);
        }
    }

    @Override
    public void notificarCambioEstado(final EstadoServidor estado, final int puerto) {
        for (ObservadorServidor obs : observadores) {
            obs.onCambioEstado(estado, puerto);
        }
    }
}
