package juancabria.imc.aplicacion.excepciones;

public final class ComandoInvalidoException extends RuntimeException {
    public ComandoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
