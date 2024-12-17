package br.com.airb.room.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ResponseReservationDto(
        Long idReservation,
        LocalDate dataInicio,
        LocalDate dataFim,
        BigDecimal valorDiaria,
        BigDecimal valorTotal,
        String formaPagamento,
        Boolean pagamentoAntecipado) {

}
