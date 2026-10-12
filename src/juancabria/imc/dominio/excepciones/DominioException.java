package juancabria.imc.dominio.excepciones;

public abstract class DominioException extends RuntimeException {
    protected DominioException(String mensaje) {
        super(mensaje);
    }
}
