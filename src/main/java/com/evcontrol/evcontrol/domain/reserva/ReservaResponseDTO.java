package com.evcontrol.evcontrol.domain.reserva;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservaResponseDTO(
        Long id,
        String nomeCliente,
        LocalDate dataReserva,
        BigDecimal valorCobrado,
        String observacoes
) {
}
