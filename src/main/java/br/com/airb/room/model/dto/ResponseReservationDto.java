package br.com.airb.room.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public record ResponseReservationDto(
        Long idReservation,
        Date dataInicio,
        Date dataFim,
        BigDecimal valorDiaria,
        BigDecimal valorTotal,
        String formaPagamento,
        Boolean pagamentoAntecipado) {

}
