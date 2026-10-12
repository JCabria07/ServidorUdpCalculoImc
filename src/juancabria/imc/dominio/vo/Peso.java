package juancabria.imc.dominio.vo;

import juancabria.imc.dominio.excepciones.DatoImcIncorrectoException;

public final class Peso {
    private final double valor;

    public Peso(final double valor) {
        if (Double.isNaN(valor) || Double.isInfinite(valor)) {
            throw new DatoImcIncorrectoException("El peso ingresado no es un número válido.");
        }
        if (valor <= 0) {
            throw new DatoImcIncorrectoException("El peso debe ser mayor que 0.");
        }
        if (valor > 600) {
            throw new DatoImcIncorrectoException("El peso no puede superar los 600 kg.");
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
        Peso peso = (Peso) o;
        return Double.compare(peso.valor, valor) == 0;
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
