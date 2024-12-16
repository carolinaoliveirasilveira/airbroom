package br.com.airb.room.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private BigDecimal valorDiaria;
    private BigDecimal valorTotal;
    private String formaPagamento;
    private Boolean pagamentoAntecipado;

    public Reservations(LocalDate dataInicio, LocalDate dataFim, BigDecimal valorDiaria,
                        String formaPagamento, Boolean pagamentoAntecipado, BigDecimal valorTotal) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorDiaria = valorDiaria;
        this.formaPagamento = formaPagamento;
        this.pagamentoAntecipado = pagamentoAntecipado;
        this.valorTotal = valorTotal;
    }


    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Boolean getPagamentoAntecipado() {
        return pagamentoAntecipado;
    }

    public void setPagamentoAntecipado(Boolean pagamentoAntecipado) {
        this.pagamentoAntecipado = pagamentoAntecipado;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
