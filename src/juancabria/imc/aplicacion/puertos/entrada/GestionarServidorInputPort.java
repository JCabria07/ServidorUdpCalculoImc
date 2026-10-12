package juancabria.imc.aplicacion.puertos.entrada;

public interface GestionarServidorInputPort {
    void iniciarServidor(int puerto);
    void detenerServidor();
    boolean estaActivo();
}
