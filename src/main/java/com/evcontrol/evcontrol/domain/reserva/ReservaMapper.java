package com.evcontrol.evcontrol.domain.reserva;

public class ReservaMapper {
    public static ReservaResponseDTO toDTO(Reserva r) {
        return new ReservaResponseDTO(
                r.getId(), r.getNomeCliente(), r.getDataReserva(), r.getValorCobrado(), r.getObservacoes()
        );
    }
}
