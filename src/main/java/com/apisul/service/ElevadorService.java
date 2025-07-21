package com.apisul.service;

import com.apisul.model.Elevador;
import com.apisul.model.Turno;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de gerenciamento de elevadores.
 * Esta classe fornece métodos que ajudam a analisar o uso de elevadores:
 * <p>
 *     <ul>
 *         <li>andarMenosUtilizado(): Retorna uma lista com o(s) andar(es) menos utilizado(s).</li>
 *         <li>elevadorMaisFrequentado(): Retorna uma lista com o(s) elevador(es) mais frequentado(s).</li>
 *         <li>periodoMaiorFluxoElevadorMaisFrequentado(): Retorna uma lista com o(s) período(s) de maior fluxo dos
 *         elevadores mais frequentados.</li>
 *         <li>elevadorMenosFrequentado(): Retorna uma lista com o(s) elevador(es) menos frequentado(s).</li>
 *         <li>periodoMenorFluxoElevadorMenosFrequentado(): Retorna uma lista com o(s) período(s) de menor fluxo dos
 *         elevadores menos frequentados.</li>
 *         <li>periodoMaiorUtilizacaoConjuntoElevadores(): Retorna uma lista com o(s) período(s) de maior utilização do
 *         conjunto de elevadores.</li>
 *         <li>percentualDeUsoElevadorA(): Retorna o percentual de uso do elevador A em relação a todos os
 *         serviços prestados.</li>
 *         <li>percentualDeUsoElevadorB(): Retorna o percentual de uso do elevador B em relação a todos os
 *         serviços prestados.</li>
 *         <li>percentualDeUsoElevadorC(): Retorna o percentual de uso do elevador C em relação a todos os
 *         serviços prestados.</li>
 *         <li>percentualDeUsoElevadorD(): Retorna o percentual de uso do elevador D em relação a todos os
 *         serviços prestados.</li>
 *         <li>percentualDeUsoElevadorE(): Retorna o percentual de uso do elevador E em relação a todos os
 *         serviços prestados.</li>
 *         </ul>
 * </p>
 */
public class ElevadorService implements IElevadorService {

    private final List<Elevador> registros;

    public ElevadorService(List<Elevador> registros) {
        this.registros = registros;
    }

    @Override
    public List<Integer> andarMenosUtilizado() {
        Map<Integer, Long> contagem = registros.stream()
                .collect(Collectors.groupingBy(Elevador::getAndar, Collectors.counting()));
        long min = contagem.values().stream().min(Long::compare).orElse(0L);
        return contagem.entrySet().stream()
                .filter(e -> e.getValue() == min)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<Character> elevadorMaisFrequentado() {
        Map<Character, Long> contagem = registros.stream()
                .collect(Collectors.groupingBy(Elevador::getElevador, Collectors.counting()));
        long max = contagem.values().stream().max(Long::compare).orElse(0L);
        return contagem.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<Character> periodoMaiorFluxoElevadorMaisFrequentado() {
        List<Character> elevadoresMais = elevadorMaisFrequentado();
        Set<Character> resultado = new HashSet<>();

        for (char elevador : elevadoresMais) {
            Map<Turno, Long> contagem = registros.stream()
                    .filter(e -> e.getElevador() == elevador)
                    .collect(Collectors.groupingBy(Elevador::getTurno, Collectors.counting()));
            long max = contagem.values().stream().max(Long::compare).orElse(0L);
            contagem.entrySet().stream()
                    .filter(e -> e.getValue() == max)
                    .map(Map.Entry::getKey)
                    .map(this::convertTurnoToChar)
                    .forEach(resultado::add);
        }
        return new ArrayList<>(resultado);
    }

    @Override
    public List<Character> elevadorMenosFrequentado() {
        Map<Character, Long> contagem = registros.stream()
                .collect(Collectors.groupingBy(Elevador::getElevador, Collectors.counting()));
        long min = contagem.values().stream().min(Long::compare).orElse(0L);
        return contagem.entrySet().stream()
                .filter(e -> e.getValue() == min)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<Character> periodoMenorFluxoElevadorMenosFrequentado() {
        List<Character> elevadoresMenos = elevadorMenosFrequentado();
        Set<Character> resultado = new HashSet<>();

        for (char elevador : elevadoresMenos) {
            Map<Turno, Long> contagem = registros.stream()
                    .filter(e -> e.getElevador() == elevador)
                    .collect(Collectors.groupingBy(Elevador::getTurno, Collectors.counting()));
            long min = contagem.values().stream().min(Long::compare).orElse(0L);
            contagem.entrySet().stream()
                    .filter(e -> e.getValue() == min)
                    .map(Map.Entry::getKey)
                    .map(this::convertTurnoToChar)
                    .forEach(resultado::add);
        }
        return new ArrayList<>(resultado);
    }

    @Override
    public List<Character> periodoMaiorUtilizacaoConjuntoElevadores() {
        Map<Turno, Long> contagem = registros.stream()
                .collect(Collectors.groupingBy(Elevador::getTurno, Collectors.counting()));
        long max = contagem.values().stream().max(Long::compare).orElse(0L);
        return contagem.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Map.Entry::getKey)
                .map(Turno::getCodigo)
                .collect(Collectors.toList());

    }

    private float calcularPercentual(char elevador) {
        long total = registros.size();
        long count = registros.stream().filter(r -> r.getElevador() == elevador).count();
        return Math.round((count * 10000.0f / total)) / 100.0f;
    }

    @Override public float percentualDeUsoElevadorA() { return calcularPercentual('A'); }
    @Override public float percentualDeUsoElevadorB() { return calcularPercentual('B'); }
    @Override public float percentualDeUsoElevadorC() { return calcularPercentual('C'); }
    @Override public float percentualDeUsoElevadorD() { return calcularPercentual('D'); }
    @Override public float percentualDeUsoElevadorE() { return calcularPercentual('E'); }

    private char convertTurnoToChar(Turno turno) {
        switch (turno) {
            case MANHA: return 'M';
            case VESPERTINO: return 'V';
            case NOITE: return 'N';
            default: throw new IllegalArgumentException("Turno inválido");
        }
    }
}
