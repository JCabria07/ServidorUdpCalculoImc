package juancabria.imc.aplicacion.excepciones;

public final class ServidorRedException extends RuntimeException {
    public ServidorRedException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
    public ServidorRedException(String mensaje) {
        super(mensaje);
    }
}
