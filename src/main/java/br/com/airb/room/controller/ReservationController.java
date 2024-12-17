package br.com.airb.room.controller;

import br.com.airb.room.model.dto.RequestReservationDto;
import br.com.airb.room.model.dto.ResponseReservationDto;
import br.com.airb.room.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    public ResponseReservationDto createReservation(@RequestBody RequestReservationDto requestReservationDto) {
        return reservationService.createReservation(requestReservationDto);
    }
}
