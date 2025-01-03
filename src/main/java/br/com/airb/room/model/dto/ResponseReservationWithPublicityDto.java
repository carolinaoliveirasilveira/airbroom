package br.com.airb.room.model.dto;

import br.com.airb.room.model.Publicity;

public record ResponseReservationWithPublicityDto(
        ResponseReservationDto reservation,
        PublicityDto publicity) {

    public record PublicityDto(
            java.util.UUID id,
            String title,
            String description,
            String location) {

        public PublicityDto(Publicity publicity) {
            this(publicity.getIdAdvertiser(), publicity.getTitle(), publicity.getDescription(), publicity.getLocation());
        }
    }
}
