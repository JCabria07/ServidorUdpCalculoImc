package juancabria.imc.dominio.modelos;

import juancabria.imc.dominio.excepciones.CalculoInvalidoException;
import juancabria.imc.dominio.vo.Altura;
import juancabria.imc.dominio.vo.Peso;
import java.util.Locale;
import java.util.Objects;

public final class Calculo {
    private final Peso peso;
    private final Altura altura;

    public Calculo(final Peso peso, final Altura altura) {
        this.peso = Objects.requireNonNull(peso, "El peso es obligatorio.");
        this.altura = Objects.requireNonNull(altura, "La altura es obligatoria.");
    }

    public Resultado calcular() {
        double valorPeso = peso.valor();
        double valorAltura = altura.valor();
        double imc = valorPeso / (valorAltura * valorAltura);

        if (Double.isNaN(imc) || Double.isInfinite(imc)) {
            throw new CalculoInvalidoException("El cálculo de IMC arrojó un valor no numérico.");
        }

        String interpretacion;
        if (imc < 18.5) {
            interpretacion = "Debes consultar un Medico, tu peso es muy bajo";
        } else if (imc <= 24.9) {
            interpretacion = "Estas bien de peso";
        } else if (imc <= 29.9) {
            interpretacion = "Debes bajar un poco de peso";
        } else {
            interpretacion = "Debes consultar un Medico, tu peso es muy alto";
        }

        String mensaje = "Tu IMC es de " + String.format(Locale.US, "%.2f", imc) + " - " + interpretacion;
        return new Resultado(imc, mensaje, interpretacion);
    }

    public Peso getPeso() {
        return peso;
    }

    public Altura getAltura() {
        return altura;
    }
}
