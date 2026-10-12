package juancabria.imc.aplicacion.mapper;

import juancabria.imc.aplicacion.dto.ProcesarPeticionUdpCommand;
import juancabria.imc.dominio.vo.Destinatario;
import juancabria.imc.dominio.vo.PuertoRed;

public final class PeticionMapper {
    public Destinatario toDestinatario(final ProcesarPeticionUdpCommand comando) {
        return new Destinatario(comando.getIpCliente(), new PuertoRed(comando.getPuertoCliente()));
    }
}
