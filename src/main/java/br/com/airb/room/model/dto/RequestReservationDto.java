package br.com.airb.room.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public record RequestReservationDto(
        Date checkin,
        Date checkout,
        BigDecimal dailyValue,
        String paymentMethod,
        Boolean advancePayment,
        Long publicityId) {
}
