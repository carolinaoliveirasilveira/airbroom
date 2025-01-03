package br.com.airb.room.model.dto;

import java.util.Date;
import java.util.List;

public record RequestPublicityDto(
        String location,
        double size,
        List<String> furnitureAvailable,
        int amountPeople,
        Date checkin,
        Date checkout,
        boolean acceptsPets,
        boolean acceptschildren,
        String accessibility,
        List<String> photos,
        double value,
        String title,
        String description) {
}
