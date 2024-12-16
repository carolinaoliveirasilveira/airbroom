package br.com.airb.room.controller;

import br.com.airb.room.model.Reservations;
import br.com.airb.room.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@RestController
@RequestMapping("/reservation")
public class ReservationController {

        @Autowired
        private ReservationService reservationService;

        @PostMapping("/criar")
        public BigDecimal criarReserva(@RequestBody LocalDate dataInicio,
                                       @RequestBody LocalDate dataFim,
                                       @RequestBody BigDecimal valorDiaria,
                                       @RequestBody String formaPagamento,
                                       @RequestBody Boolean pagamentoAntecipado) {
            Reservations reservas = reservationService.criarReserva(dataInicio, dataFim, valorDiaria, formaPagamento, pagamentoAntecipado);
            return reservas.getValorTotal();
        }
    }

