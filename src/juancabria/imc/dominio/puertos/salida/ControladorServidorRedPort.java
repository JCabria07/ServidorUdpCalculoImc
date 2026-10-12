package juancabria.imc.dominio.puertos.salida;

import java.io.IOException;

public interface ControladorServidorRedPort {
    void iniciar(int puerto) throws IOException;
    void detener();
    boolean estaEscuchando();
    int getPuertoActual();
}
