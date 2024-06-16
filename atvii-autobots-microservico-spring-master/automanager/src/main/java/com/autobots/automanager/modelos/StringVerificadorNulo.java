package com.autobots.automanager.modelos;

public class StringVerificadorNulo {
    public boolean verificar(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
