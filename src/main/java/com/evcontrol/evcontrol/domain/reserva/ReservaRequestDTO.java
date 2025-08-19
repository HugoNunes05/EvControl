package com.evcontrol.evcontrol.domain.reserva;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservaRequestDTO(
        @NotBlank @Size(max = 120) String nomeCliente,
        @NotNull @Future LocalDate dataReserva,
        @NotNull @Positive BigDecimal valorCobrado,
        @Size(max = 500) String observacoes
    ) {}
