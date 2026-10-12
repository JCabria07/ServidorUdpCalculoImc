package juancabria.imc.dominio.puertos.salida;

import juancabria.imc.dominio.modelos.RespuestaCliente;

public interface PuertoSalidaRed {
    void enviarRespuesta(RespuestaCliente respuesta);
}
