package com.apisul.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Turno {
    MANHA('M'),
    VESPERTINO('V'),
    NOITE('N');

    private final char codigo;

    Turno(char codigo) {
        this.codigo = codigo;
    }

    public char getCodigo() {
        return codigo;
    }

    /**
     * Converte um char para o enum correspondente.
     * @param codigo 'M' = MANHA, 'V' = VESPERTINO ou 'N' = NOITE
     * @return o enum correspondente
     * @throws IllegalArgumentException se o código não for válido
     */
    @JsonCreator
    public static Turno fromCodigo(String codigo) {
        if (codigo == null || codigo.length() != 1) {
            throw new IllegalArgumentException("Código inválido para turno: " + codigo);
        }
        char c = Character.toUpperCase(codigo.charAt(0));
        for (Turno t : Turno.values()) {
            if (t.getCodigo() == c) {
                return t;
            }
        }
        throw new IllegalArgumentException("Turno inválido: " + codigo);
    }
}
