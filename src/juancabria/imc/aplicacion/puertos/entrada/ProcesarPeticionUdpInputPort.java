package juancabria.imc.aplicacion.puertos.entrada;

import juancabria.imc.aplicacion.dto.ProcesarPeticionUdpCommand;

public interface ProcesarPeticionUdpInputPort {
    void procesar(ProcesarPeticionUdpCommand comando);
}
