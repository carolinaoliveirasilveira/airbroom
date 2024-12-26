package br.com.airb.room.model.dto;

import br.com.airb.room.model.Publicity;

public record ResponseReservationWithPublicityDto(
        ResponseReservationDto reservation,
        PublicityDto publicity) {

    public record PublicityDto(
            Long id,
            String titulo,
            String descricao,
            String localizacao) {

        public PublicityDto(Publicity publicity) {
            this(publicity.getId(), publicity.getTitulo(), publicity.getDescricao(), publicity.getLocalizacao());
        }
    }
}
