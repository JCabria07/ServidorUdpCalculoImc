package juancabria.imc.aplicacion.servicios;

import juancabria.imc.aplicacion.mapper.CalculoMapper;
import juancabria.imc.aplicacion.puertos.entrada.CalcularImcInputPort;
import juancabria.imc.dominio.modelos.Calculo;
import juancabria.imc.dominio.modelos.Resultado;
import java.util.Objects;

public final class CalcularImcService implements CalcularImcInputPort {
    private final CalculoMapper calculoMapper;

    public CalcularImcService(final CalculoMapper calculoMapper) {
        this.calculoMapper = Objects.requireNonNull(calculoMapper, "El mapper de cálculo es obligatorio.");
    }

    @Override
    public Resultado calcular(double peso, double altura) {
        Calculo calculo = calculoMapper.toDomain(peso, altura);
        return calculo.calcular();
    }
}
