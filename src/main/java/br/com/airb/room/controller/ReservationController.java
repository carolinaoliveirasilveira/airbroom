package br.com.airb.room.controller;

import br.com.airb.room.model.dto.RequestReservationDto;
import br.com.airb.room.model.dto.ResponseReservationDto;
import br.com.airb.room.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create/{idReservation}")
    public ResponseEntity<ResponseReservationDto> criarReserva(
            @RequestBody RequestReservationDto requestReservationDto,
            @PathVariable Long idReservation) {

        ResponseReservationDto responseReservationDto = reservationService.criarReserva(requestReservationDto, idReservation);

        return new ResponseEntity<>(responseReservationDto, HttpStatus.CREATED);
    }
}
