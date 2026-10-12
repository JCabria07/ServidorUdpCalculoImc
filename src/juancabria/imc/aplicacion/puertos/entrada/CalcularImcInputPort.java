package juancabria.imc.aplicacion.puertos.entrada;

import juancabria.imc.dominio.modelos.Resultado;

public interface CalcularImcInputPort {
    Resultado calcular(double peso, double altura);
}
