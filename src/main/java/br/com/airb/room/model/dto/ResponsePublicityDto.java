package br.com.airb.room.model.dto;

import java.util.List;
import java.util.UUID;

public record ResponsePublicityDto(
        Long id,
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
        UUID idAnunciante) {

}