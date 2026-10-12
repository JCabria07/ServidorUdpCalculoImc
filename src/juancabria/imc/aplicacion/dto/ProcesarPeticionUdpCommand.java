package juancabria.imc.aplicacion.dto;

public final class ProcesarPeticionUdpCommand {
    private final String ipCliente;
    private final int puertoCliente;
    private final String mensaje;

    public ProcesarPeticionUdpCommand(final String ipCliente, final int puertoCliente, final String mensaje) {
        this.ipCliente = ipCliente;
        this.puertoCliente = puertoCliente;
        this.mensaje = mensaje;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public int getPuertoCliente() {
        return puertoCliente;
    }

    public String getMensaje() {
        return mensaje;
    }
}
