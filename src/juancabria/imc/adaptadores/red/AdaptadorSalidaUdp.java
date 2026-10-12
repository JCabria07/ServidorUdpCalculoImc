package juancabria.imc.adaptadores.red;

import juancabria.imc.adaptadores.red.mapper.UdpNetworkMapper;
import juancabria.imc.adaptadores.red.response.UdpResponse;
import juancabria.imc.dominio.modelos.EventoServidor;
import juancabria.imc.dominio.modelos.RespuestaCliente;
import juancabria.imc.dominio.puertos.salida.PuertoNotificacionEvento;
import juancabria.imc.dominio.puertos.salida.PuertoSalidaRed;
import java.io.IOException;
import java.util.Objects;

public final class AdaptadorSalidaUdp implements PuertoSalidaRed {
    private final CanalUdp canalUdp;
    private final UdpNetworkMapper mapper;
    private final PuertoNotificacionEvento notificador;

    public AdaptadorSalidaUdp(final CanalUdp canalUdp, final UdpNetworkMapper mapper, final PuertoNotificacionEvento notificador) {
        this.canalUdp = Objects.requireNonNull(canalUdp);
        this.mapper = Objects.requireNonNull(mapper);
        this.notificador = Objects.requireNonNull(notificador);
    }

    @Override
    public void enviarRespuesta(final RespuestaCliente respuesta) {
        try {
            UdpResponse udpResponse = mapper.toNetworkResponse(respuesta);
            canalUdp.enviar(udpResponse);
        } catch (IOException e) {
            notificador.notificarEvento(new EventoServidor("ERROR", respuesta.getDestinatario().endpoint(), "Error al enviar datagrama UDP: " + e.getMessage()));
        }
    }
}
