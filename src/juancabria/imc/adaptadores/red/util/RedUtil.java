package juancabria.imc.adaptadores.red.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class RedUtil {
    private RedUtil() {}

    public static String obtenerIpLocal() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }
}
