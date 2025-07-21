package com.apisul;

import com.apisul.model.Elevador;
import com.apisul.service.ElevadorService;
import com.apisul.service.IElevadorService;
import com.apisul.util.JsonValidator;

import java.util.List;

public class ApplicationElevatorManagement {
    public static void main(String[] args) {
        List<Elevador> dados = JsonValidator.carregarElevadores();
        IElevadorService service = new ElevadorService(dados);

        //a. Qual é o andar menos utilizado pelos usuários;
        System.out.println("Andar menos utilizado: " + service.andarMenosUtilizado());

        //b. Qual é o elevador mais frequentado e o período que se encontra maior fluxo;
        System.out.println("Elevador mais frequentado: " + service.elevadorMaisFrequentado());

        //c. Qual é o elevador menos frequentado e o período que se encontra menor fluxo;
        System.out.println("Elevador menos frequentado: " + service.elevadorMenosFrequentado());

        //d. Qual o período de maior utilização do conjunto de elevadores;
        List<Character> codigosTurno = service.periodoMaiorUtilizacaoConjuntoElevadores();

        List<String> nomesTurno = codigosTurno.stream()
                .map(c -> com.apisul.model.Turno.fromCodigo(String.valueOf(c)).name())
                .toList();

        System.out.println("Período de maior utilização do conjunto de elevadores: " + nomesTurno);

        //e. Qual o percentual de uso de cada elevador com relação a todos os serviços prestados;
        System.out.printf("Percentual de uso do elevador A: %.2f%%\n", service.percentualDeUsoElevadorA());
        System.out.printf("Percentual de uso do elevador B: %.2f%%\n", service.percentualDeUsoElevadorB());
        System.out.printf("Percentual de uso do elevador C: %.2f%%\n", service.percentualDeUsoElevadorC());
        System.out.printf("Percentual de uso do elevador D: %.2f%%\n", service.percentualDeUsoElevadorD());
        System.out.printf("Percentual de uso do elevador E: %.2f%%\n", service.percentualDeUsoElevadorE());
    }
}
