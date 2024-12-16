package br.com.airb.room.model.dto;

import java.util.List;

public record RequestAdvertiserPersonDto(
        String nome,
        String cpfOuCnpj,
        boolean ativo,
        List<ContatoDto> contatos,
        List<EnderecoDto> endereco) {
}
