package br.com.airb.room.model.dto;

public record RequestAdvertiserPersonDto(
        String nome,
        String email,
        String telefone,
        String cpfOuCnpj,
        String endereco,
        String cidade,
        String estado,
        String pais,
        boolean ativo) {
}
