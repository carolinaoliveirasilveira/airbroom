package br.com.airb.room.service;

import br.com.airb.room.model.Reservations;
import br.com.airb.room.model.dto.RequestReservationDto;
import br.com.airb.room.model.dto.ResponseReservationDto;
import br.com.airb.room.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public ResponseReservationDto createReservation(RequestReservationDto requestReservationDto) {
        long calculatingNumberOfDays = calculatingNumberOfDays(requestReservationDto.dataInicio(), requestReservationDto.dataFim());
        BigDecimal calculatingTotalValue = requestReservationDto.valorDiaria()
                .multiply(new BigDecimal(calculatingNumberOfDays));

        Reservations returnReservation =
                reservationRepository.save(toReservationDto(requestReservationDto, calculatingTotalValue));
        return toConverteReservationparaResponseReservationDto(returnReservation);
    }

    private long calculatingNumberOfDays(Date dataInicio, Date dataFim) {

        long diffInMillies = dataFim.getTime() - dataInicio.getTime();

        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    private ResponseReservationDto toConverteReservationparaResponseReservationDto(Reservations returnReservation) {
        return new ResponseReservationDto(
                returnReservation.getId(),
                returnReservation.getDataInicio(),
                returnReservation.getDataFim(),
                returnReservation.getValorDiaria(),
                returnReservation.getValorTotal(),
                returnReservation.getFormaPagamento(),
                returnReservation.getPagamentoAntecipado()
        );
    }


    private Reservations toReservationDto(RequestReservationDto requestReservationDto, BigDecimal calculatingTotalValue) {
        Reservations reservations = new Reservations();
        reservations.setDataInicio(requestReservationDto.dataInicio());
        reservations.setDataFim(requestReservationDto.dataFim());
        reservations.setValorDiaria(requestReservationDto.valorDiaria());
        reservations.setValorTotal(calculatingTotalValue);
        reservations.setFormaPagamento(requestReservationDto.formaPagamento());
        reservations.setPagamentoAntecipado(requestReservationDto.pagamentoAntecipado());
        return reservations;
    }
}


