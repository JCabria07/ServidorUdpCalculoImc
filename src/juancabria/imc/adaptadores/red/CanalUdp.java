package juancabria.imc.adaptadores.red;

import juancabria.imc.adaptadores.red.response.UdpResponse;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class CanalUdp {
    private DatagramSocket socket;
    private volatile boolean abierto = false;
    private int puertoActual = 0;

    public synchronized void abrir(final int puerto) throws SocketException {
        if (abierto && socket != null && !socket.isClosed()) {
            return;
        }
        socket = new DatagramSocket(puerto);
        puertoActual = puerto;
        abierto = true;
    }

    public synchronized void cerrar() {
        abierto = false;
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        socket = null;
        puertoActual = 0;
    }

    public synchronized boolean isAbierto() {
        return abierto && socket != null && !socket.isClosed();
    }

    public synchronized int getPuertoActual() {
        return puertoActual;
    }

    public void enviar(final UdpResponse respuesta) throws IOException {
        DatagramSocket socketAbierto = obtenerSocketAbierto();
        byte[] buffer = respuesta.getBytes();
        InetAddress direccion = InetAddress.getByName(respuesta.getIpDestino());
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccion, respuesta.getPuertoDestino());
        socketAbierto.send(paquete);
    }

    public void recibir(final DatagramPacket paquete) throws IOException {
        obtenerSocketAbierto().receive(paquete);
    }

    private synchronized DatagramSocket obtenerSocketAbierto() throws SocketException {
        if (socket == null || socket.isClosed()) {
            throw new SocketException("El socket UDP está cerrado.");
        }
        return socket;
    }
}
