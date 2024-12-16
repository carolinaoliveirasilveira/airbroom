package br.com.airb.room.model.dto;

import java.util.List;

public record RequestPublicityDto(
        String localizacao,
        double tamanho,
        List<String> mobiliaDisponivel,
        int quantidadePessoas,
        int diaInicio,
        int mesInicio,
        int diaFim,
        int mesFim,
        boolean aceitaPets,
        boolean aceitaCriancas,
        String acessibilidade,
        List<String> fotos,
        double valor) {
}
