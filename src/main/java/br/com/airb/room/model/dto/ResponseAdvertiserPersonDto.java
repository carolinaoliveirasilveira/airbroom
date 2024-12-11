package br.com.airb.room.model.dto;

public record ResponseAdvertiserPersonDto(
        Long id,
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