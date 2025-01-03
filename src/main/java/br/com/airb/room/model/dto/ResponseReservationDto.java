package br.com.airb.room.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public record ResponseReservationDto(
        Long idReservation,
        Date checkin,
        Date checkout,
        BigDecimal dailyValue,
        BigDecimal totalValue,
        String paymentMethod,
        Boolean advancePayment) {

}
