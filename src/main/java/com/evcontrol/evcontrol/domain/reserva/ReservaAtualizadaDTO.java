package com.evcontrol.evcontrol.domain.reserva;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservaAtualizadaDTO(
        String nomeCliente,
        LocalDate dataReserva,
        BigDecimal valorCobrado,
        String observacoes

) { }
