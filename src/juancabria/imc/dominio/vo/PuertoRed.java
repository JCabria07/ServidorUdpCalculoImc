package juancabria.imc.dominio.vo;

import juancabria.imc.dominio.excepciones.PuertoIncorrectoException;

public final class PuertoRed {
    private final int valor;

    public PuertoRed(final int valor) {
        if (valor < 1024 || valor > 65535) {
            throw new PuertoIncorrectoException("El puerto de red debe estar entre 1024 y 65535. Valor: " + valor);
        }
        this.valor = valor;
    }

    public int valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuertoRed puertoRed = (PuertoRed) o;
        return valor == puertoRed.valor;
    }

    @Override
    public int hashCode() {
        return valor;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}
