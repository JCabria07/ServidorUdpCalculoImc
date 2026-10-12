package juancabria.imc.aplicacion.servicios;

import juancabria.imc.aplicacion.excepciones.ServidorRedException;
import juancabria.imc.aplicacion.puertos.entrada.GestionarServidorInputPort;
import juancabria.imc.dominio.enums.EstadoServidor;
import juancabria.imc.dominio.modelos.EventoServidor;
import juancabria.imc.dominio.puertos.salida.ControladorServidorRedPort;
import juancabria.imc.dominio.puertos.salida.PuertoNotificacionEvento;
import juancabria.imc.dominio.vo.PuertoRed;
import java.io.IOException;
import java.util.Objects;

public final class GestionarServidorService implements GestionarServidorInputPort {
    private final ControladorServidorRedPort controladorRed;
    private final PuertoNotificacionEvento notificador;

    public GestionarServidorService(final ControladorServidorRedPort controladorRed, final PuertoNotificacionEvento notificador) {
        this.controladorRed = Objects.requireNonNull(controladorRed, "El controlador de red es obligatorio.");
        this.notificador = Objects.requireNonNull(notificador, "El notificador es obligatorio.");
    }

    @Override
    public void iniciarServidor(int puerto) {
        try {
            PuertoRed puertoVo = new PuertoRed(puerto);
            controladorRed.iniciar(puertoVo.valor());
            notificador.notificarCambioEstado(EstadoServidor.ESCUCHANDO, puertoVo.valor());
            notificador.notificarEvento(new EventoServidor("SISTEMA", "", "Servidor disponible en el Puerto " + puertoVo.valor()));
        } catch (IOException e) {
            notificador.notificarCambioEstado(EstadoServidor.ERROR, puerto);
            notificador.notificarEvento(new EventoServidor("ERROR", "", "ERROR al abrir o escuchar en el puerto " + puerto));
            throw new ServidorRedException("No se pudo iniciar el servidor en el puerto " + puerto, e);
        } catch (Exception e) {
            notificador.notificarCambioEstado(EstadoServidor.ERROR, puerto);
            notificador.notificarEvento(new EventoServidor("ERROR", "", e.getMessage()));
            throw new ServidorRedException(e.getMessage(), e);
        }
    }

    @Override
    public void detenerServidor() {
        int puerto = controladorRed.getPuertoActual();
        controladorRed.detener();
        notificador.notificarCambioEstado(EstadoServidor.DETENIDO, puerto);
        notificador.notificarEvento(new EventoServidor("SISTEMA", "", "Servidor detenido. Puerto liberado"));
    }

    @Override
    public boolean estaActivo() {
        return controladorRed.estaEscuchando();
    }
}
