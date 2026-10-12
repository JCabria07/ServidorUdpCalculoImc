package juancabria.imc.dominio.modelos;

import java.util.Locale;
import java.util.Objects;

public final class Resultado {
    private final double imc;
    private final String imcFormateado;
    private final String mensaje;
    private final String interpretacion;

    public Resultado(final double imc, final String mensaje, final String interpretacion) {
        this.imc = imc;
        this.imcFormateado = String.format(Locale.US, "%.2f", imc);
        this.mensaje = Objects.requireNonNull(mensaje, "El mensaje no puede ser nulo.");
        this.interpretacion = Objects.requireNonNull(interpretacion, "La interpretación no puede ser nula.");
    }

    public double getImc() {
        return imc;
    }

    public String getImcFormateado() {
        return imcFormateado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getInterpretacion() {
        return interpretacion;
    }

    @Override
    public String toString() {
        return "Resultado{imc=" + imcFormateado + ", mensaje='" + mensaje + "'}";
    }
}
