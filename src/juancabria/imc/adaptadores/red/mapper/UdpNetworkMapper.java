package juancabria.imc.adaptadores.red.mapper;

import juancabria.imc.adaptadores.red.response.UdpResponse;
import juancabria.imc.dominio.excepciones.RespuestaInvalidaException;
import juancabria.imc.dominio.modelos.RespuestaCliente;
import juancabria.imc.dominio.modelos.Resultado;

public final class UdpNetworkMapper {
    public UdpResponse toNetworkResponse(final RespuestaCliente respuesta) {
        if (respuesta == null) {
            throw new RespuestaInvalidaException("No se puede serializar una respuesta nula.");
        }

        String payload;
        switch (respuesta.getTipo()) {
            case CONECTADO:
                payload = "CONECTADO_OK;" + (respuesta.getMensaje() != null ? respuesta.getMensaje() : "");
                break;
            case DESCONECTADO:
                payload = "DESCONECTADO_OK;" + (respuesta.getMensaje() != null ? respuesta.getMensaje() : "");
                break;
            case OK_CALCULO:
                payload = buildPayloadCalculo(respuesta.getResultado());
                break;
            case ERROR:
            default:
                payload = "ERROR;" + (respuesta.getMensaje() != null ? respuesta.getMensaje() : "Error desconocido.");
                break;
        }

        return new UdpResponse(payload, respuesta.getDestinatario().ip(), respuesta.getDestinatario().puerto());
    }

    private static String buildPayloadCalculo(final Resultado resultado) {
        if (resultado == null) {
            throw new RespuestaInvalidaException("No se puede serializar un cálculo sin resultado.");
        }
        return "OK_CALCULO;" + resultado.getImcFormateado() + ";" + resultado.getMensaje() + ";" + resultado.getInterpretacion();
    }
}
