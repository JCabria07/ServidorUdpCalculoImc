package juancabria.imc;

import juancabria.imc.adaptadores.notificacion.AdaptadorNotificacionEvento;
import juancabria.imc.adaptadores.red.AdaptadorControlServidorRed;
import juancabria.imc.adaptadores.red.AdaptadorSalidaUdp;
import juancabria.imc.adaptadores.red.CanalUdp;
import juancabria.imc.adaptadores.red.mapper.UdpNetworkMapper;
import juancabria.imc.aplicacion.mapper.CalculoMapper;
import juancabria.imc.aplicacion.mapper.PeticionMapper;
import juancabria.imc.aplicacion.puertos.entrada.GestionarServidorInputPort;
import juancabria.imc.aplicacion.puertos.entrada.ProcesarPeticionUdpInputPort;
import juancabria.imc.aplicacion.servicios.GestionarServidorService;
import juancabria.imc.aplicacion.servicios.ProcesarPeticionUdpService;
import juancabria.imc.dominio.puertos.salida.ControladorServidorRedPort;
import juancabria.imc.dominio.puertos.salida.PuertoSalidaRed;
import juancabria.imc.entrypoint.gui.VentanaPrincipal;
import juancabria.imc.entrypoint.udp.ReceptorPeticionesUdp;
import javax.swing.SwingUtilities;

public class Principal {
    public static void main(String[] args) {
        CanalUdp canalUdp = new CanalUdp();
        AdaptadorNotificacionEvento notificador = new AdaptadorNotificacionEvento();
        UdpNetworkMapper networkMapper = new UdpNetworkMapper();
        PuertoSalidaRed puertoSalidaRed = new AdaptadorSalidaUdp(canalUdp, networkMapper, notificador);
        PeticionMapper peticionMapper = new PeticionMapper();
        CalculoMapper calculoMapper = new CalculoMapper();

        ProcesarPeticionUdpInputPort procesarInputPort = new ProcesarPeticionUdpService(
            puertoSalidaRed, notificador, peticionMapper, calculoMapper);

        ReceptorPeticionesUdp receptor = new ReceptorPeticionesUdp(canalUdp, procesarInputPort, notificador);

        ControladorServidorRedPort controladorRed = new AdaptadorControlServidorRed(
            canalUdp, receptor::iniciar, receptor::detener);

        GestionarServidorInputPort gestionarServidorPort = new GestionarServidorService(controladorRed, notificador);

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal(gestionarServidorPort);
            notificador.registrarObservador(ventana);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
