package br.com.airb.room.service;

import br.com.airb.room.model.Reservations;
import br.com.airb.room.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public Reservations criarReserva(LocalDate dataInicio, LocalDate dataFim, BigDecimal valorDiaria,
                                     String formaPagamento, Boolean pagamentoAntecipado) {
        // Calcula o valor total diretamente ao criar a reserva
        BigDecimal valorTotal = calcularValorTotal(dataInicio, dataFim, valorDiaria, formaPagamento, pagamentoAntecipado);

        // Cria e retorna a reserva
        return new Reservations(dataInicio, dataFim, valorDiaria, formaPagamento, pagamentoAntecipado, valorTotal);
    }

    private BigDecimal calcularValorTotal(LocalDate dataInicio, LocalDate dataFim, BigDecimal valorDiaria,
                                          String formaPagamento, Boolean pagamentoAntecipado) {
        long dias = dataInicio.until(dataFim).getDays(); // Calcula o número de dias
        BigDecimal valorTotal = valorDiaria.multiply(BigDecimal.valueOf(dias)); // Calcula o valor total sem desconto

        if (pagamentoAntecipado != null && pagamentoAntecipado) {
            valorTotal = valorTotal.multiply(BigDecimal.valueOf(0.9)); // 10% de desconto
        }

        if ("PIX".equalsIgnoreCase(formaPagamento)) {
            valorTotal = valorTotal.multiply(BigDecimal.valueOf(0.95)); // 5% de desconto para PIX
        }

        return valorTotal;
    }
}

