package br.com.airb.room.model.dto;

import java.util.UUID;

public record EnderecoDto(
        String cidade,
        String estado,
        String pais
) {
}

