package com.apisul.model;

/**
 * Representa um elevador com informações sobre andar, identificação do elevador e turno de uso.
 * <p>
 * Esta classe é utilizada para armazenar e manipular dados relacionados ao uso de elevadores
 * possui os metodos:
 * <ul>
 *     <li>getAndar(): Retorna o andar atual do elevador.</li>
 *     <li>setAndar(int andar): Define o andar atual do elevador.</li>
 *     <li>getElevador(): Retorna a identificação do elevador (A, B, C, D, E).</li>
 *     <li>setElevador(char elevador): Define a identificação do elevador.</li>
 *     <li>getTurno(): Retorna o turno de uso do elevador (MANHA, VESPERTINO, NOITE).</li>
 *     <li>setTurno(Turno turno): Define o turno de uso do elevador.</li>
 *     <li>Construtor padrão: Inicializa um objeto Elevator com valores padrão.</li>
 *     <li>Construtor com parâmetros: Permite a criação de um objeto Elevator com valores específicos para andar, elevador e turno.</li>
 *     </ul>
 * </p>
 */
public class Elevador {
    private int andar;
    private char elevador;
    private Turno turno;

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public char getElevador() {
        return elevador;
    }

    public void setElevador(char elevador) {
        this.elevador = elevador;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}