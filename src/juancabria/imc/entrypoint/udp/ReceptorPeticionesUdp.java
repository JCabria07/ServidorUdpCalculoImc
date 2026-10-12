package juancabria.imc.entrypoint.udp;

import juancabria.imc.adaptadores.red.CanalUdp;
import juancabria.imc.aplicacion.dto.ProcesarPeticionUdpCommand;
import juancabria.imc.aplicacion.puertos.entrada.ProcesarPeticionUdpInputPort;
import juancabria.imc.dominio.modelos.EventoServidor;
import juancabria.imc.dominio.puertos.salida.PuertoNotificacionEvento;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class ReceptorPeticionesUdp {
    private static final int BUFFER_SIZE = 2048;
    private final CanalUdp canalUdp;
    private final ProcesarPeticionUdpInputPort inputPort;
    private final PuertoNotificacionEvento notificador;

    private Thread hiloEscucha;
    private volatile boolean escuchando = false;

    public ReceptorPeticionesUdp(
            final CanalUdp canalUdp,
            final ProcesarPeticionUdpInputPort inputPort,
            final PuertoNotificacionEvento notificador) {
        this.canalUdp = Objects.requireNonNull(canalUdp);
        this.inputPort = Objects.requireNonNull(inputPort);
        this.notificador = Objects.requireNonNull(notificador);
    }

    public synchronized void iniciar() {
        if (escuchando) {
            return;
        }
        escuchando = true;
        hiloEscucha = new Thread(this::cicloEscucha, "Thread-ReceptorPeticionesUdp-" + canalUdp.getPuertoActual());
        hiloEscucha.setDaemon(true);
        hiloEscucha.start();
    }

    public synchronized void detener() {
        escuchando = false;
        if (hiloEscucha != null && hiloEscucha.isAlive()) {
            hiloEscucha.interrupt();
        }
        hiloEscucha = null;
    }

    public boolean isEscuchando() {
        return escuchando;
    }

    private void cicloEscucha() {
        byte[] buffer = new byte[BUFFER_SIZE];
        while (escuchando && canalUdp.isAbierto()) {
            try {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                canalUdp.recibir(paquete);

                String texto = new String(paquete.getData(), 0, paquete.getLength(), StandardCharsets.UTF_8).trim();
                InetAddress direccion = paquete.getAddress();
                int puerto = paquete.getPort();

                ProcesarPeticionUdpCommand comando = new ProcesarPeticionUdpCommand(
                    direccion.getHostAddress(), puerto, texto);

                inputPort.procesar(comando);
            } catch (SocketException ex) {
                if (!escuchando) {
                    break;
                }
                notificarError("Error de socket al recibir paquete: " + ex.getMessage());
            } catch (IOException ex) {
                if (escuchando) {
                    notificarError("Error I/O al recibir datagrama: " + ex.getMessage());
                }
            }
        }
    }

    private void notificarError(final String mensaje) {
        notificador.notificarEvento(new EventoServidor("ERROR", "SOCKET", mensaje));
    }
}
