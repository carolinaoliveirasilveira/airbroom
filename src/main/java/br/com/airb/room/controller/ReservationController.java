package br.com.airb.room.controller;

import br.com.airb.room.model.Publicity;
import br.com.airb.room.model.dto.RequestReservationDto;
import br.com.airb.room.model.dto.ResponseReservationDto;
import br.com.airb.room.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create/{publicityId}")
    public ResponseReservationDto createReservation(
            @RequestBody RequestReservationDto requestReservationDto,
            @PathVariable Long publicityId ) {
        return reservationService.createReservation(requestReservationDto, publicityId);
    }

    @GetMapping("/all")
    public List<ResponseReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseReservationDto getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseReservationDto updateReservation(
            @PathVariable Long id,
            @RequestBody RequestReservationDto requestReservationDto) {
        return reservationService.updateReservation(id, requestReservationDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok("Reserva com ID " + id + " foi deletada com sucesso.");
    }

}
