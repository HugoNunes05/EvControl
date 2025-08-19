package com.evcontrol.evcontrol.domain.reserva;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reservas",
    uniqueConstraints = {
    @UniqueConstraint(name = "UK_reservas_data", columnNames = {"data_reserva"})
    })
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 120)
    @Column(name = "nome_cliente", nullable = false, length = 120)
    private String nomeCliente;

    @NotNull
    @Future
    @Column(name = "data_reserva", nullable = false)
    private LocalDate dataReserva;

    @NotNull
    @Positive
    @Column(name = "valor_cobrado", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorCobrado;

    @Size(max = 500)
    @Column(name = "observacoes", length = 500)
    private String observacoes;

    public Reserva(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public BigDecimal getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(BigDecimal valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
