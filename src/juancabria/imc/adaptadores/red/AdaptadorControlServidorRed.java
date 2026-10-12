package juancabria.imc.adaptadores.red;

import juancabria.imc.dominio.puertos.salida.ControladorServidorRedPort;
import java.io.IOException;
import java.util.Objects;

public final class AdaptadorControlServidorRed implements ControladorServidorRedPort {
    private final CanalUdp canalUdp;
    private final Runnable accionIniciarReceptor;
    private final Runnable accionDetenerReceptor;

    public AdaptadorControlServidorRed(final CanalUdp canalUdp, final Runnable accionIniciarReceptor, final Runnable accionDetenerReceptor) {
        this.canalUdp = Objects.requireNonNull(canalUdp);
        this.accionIniciarReceptor = Objects.requireNonNull(accionIniciarReceptor);
        this.accionDetenerReceptor = Objects.requireNonNull(accionDetenerReceptor);
    }

    @Override
    public void iniciar(final int puerto) throws IOException {
        canalUdp.abrir(puerto);
        accionIniciarReceptor.run();
    }

    @Override
    public void detener() {
        accionDetenerReceptor.run();
        canalUdp.cerrar();
    }

    @Override
    public boolean estaEscuchando() {
        return canalUdp.isAbierto();
    }

    @Override
    public int getPuertoActual() {
        return canalUdp.getPuertoActual();
    }
}
