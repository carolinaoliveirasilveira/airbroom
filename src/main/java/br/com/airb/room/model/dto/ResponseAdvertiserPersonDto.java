package br.com.airb.room.model.dto;

import java.util.List;
import java.util.UUID;

public record ResponseAdvertiserPersonDto(
        UUID id,
        String nome,
        String cpfOuCnpj,
        boolean ativo,
        List<ContatoDto> contatos,
        List<EnderecoDto> enderecos) {
}
