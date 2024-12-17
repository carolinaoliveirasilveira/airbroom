package br.com.airb.room.service;

import br.com.airb.room.model.Reservations;
import br.com.airb.room.model.dto.RequestReservationDto;
import br.com.airb.room.model.dto.ResponseReservationDto;
import br.com.airb.room.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public ResponseReservationDto criarReserva(RequestReservationDto requestReservationDto, Long idReservation) {
        Reservations reservations = toReservationDto(requestReservationDto);
        reservations.setIdReservation(idReservation);
        Reservations returnReservation = reservationRepository.save(reservations);

        ResponseReservationDto responseReservationDto = toConverteReservationparaResponseReservationDto(returnReservation);
        return responseReservationDto;
    }

    private ResponseReservationDto toConverteReservationparaResponseReservationDto(Reservations returnReservation) {
        ResponseReservationDto responseReservationDto = new ResponseReservationDto(
                returnReservation.getIdReservation(),
                returnReservation.getDataInicio(),
                returnReservation.getDataFim(),
                returnReservation.getValorDiaria(),
                returnReservation.getValorTotal(),
                returnReservation.getFormaPagamento(),
                returnReservation.getPagamentoAntecipado()
        );

        return responseReservationDto;
    }


    private Reservations toReservationDto(RequestReservationDto requestReservationDto) {
        Reservations reservations = new Reservations();
        reservations.setDataInicio(requestReservationDto.dataInicio());
        reservations.setDataFim(requestReservationDto.dataFim());
        reservations.setValorDiaria(requestReservationDto.valorDiaria());
        reservations.setValorTotal(requestReservationDto.valorTotal());
        reservations.setFormaPagamento(requestReservationDto.formaPagamento());
        reservations.setPagamentoAntecipado(requestReservationDto.pagamentoAntecipado());

        return reservations;
    }
}


