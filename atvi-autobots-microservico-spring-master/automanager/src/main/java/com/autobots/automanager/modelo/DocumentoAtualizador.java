package com.autobots.automanager.modelo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Documento;

@Component
public class DocumentoAtualizador {
    public void atualizar(Documento documento, Documento atualizacao) {
        if (atualizacao != null) {
            if (atualizacao.getTipo() != null) {
                documento.setTipo(atualizacao.getTipo());
            }
            if (atualizacao.getNumero() != null) {
                documento.setNumero(atualizacao.getNumero());
            }
        }
    }

    public void atualizar(List<Documento> documentos, List<Documento> atualizacoes) {
        for (Documento atualizacao : atualizacoes) {
            for (Documento documento : documentos) {
                if (atualizacao.getId() != null) {
                    if (atualizacao.getId().equals(documento.getId())) {
                        atualizar(documento, atualizacao);
                    }
                }
            }
        }
    }
}
