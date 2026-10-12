package juancabria.imc.dominio.vo;

import juancabria.imc.dominio.excepciones.DatoImcIncorrectoException;

public final class Altura {
    private final double valor;

    public Altura(final double valor) {
        if (Double.isNaN(valor) || Double.isInfinite(valor)) {
            throw new DatoImcIncorrectoException("La altura ingresada no es un número válido.");
        }
        if (valor <= 0) {
            throw new DatoImcIncorrectoException("La altura debe ser mayor que 0.");
        }
        if (valor > 3.0) {
            throw new DatoImcIncorrectoException("La altura no puede superar los 3.0 metros.");
        }
        this.valor = valor;
    }

    public double valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Altura altura = (Altura) o;
        return Double.compare(altura.valor, valor) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(valor);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}
