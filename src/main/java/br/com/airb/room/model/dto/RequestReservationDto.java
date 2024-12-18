package br.com.airb.room.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public record RequestReservationDto(
        Date dataInicio,
        Date dataFim,
        BigDecimal valorDiaria,
        String formaPagamento,
        Boolean pagamentoAntecipado,
        Long publicityId) {
}
