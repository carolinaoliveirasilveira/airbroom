package br.com.airb.room.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RequestReservationDto(
        LocalDate dataInicio,
        LocalDate dataFim,
        BigDecimal valorDiaria,
        BigDecimal valorTotal,
        String formaPagamento,
        Boolean pagamentoAntecipado) {
}
