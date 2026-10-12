package juancabria.imc.aplicacion.mapper;

import juancabria.imc.dominio.modelos.Calculo;
import juancabria.imc.dominio.vo.Altura;
import juancabria.imc.dominio.vo.Peso;

public final class CalculoMapper {
    public Calculo toDomain(final double peso, final double altura) {
        return new Calculo(new Peso(peso), new Altura(altura));
    }
}
