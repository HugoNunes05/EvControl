package com.evcontrol.evcontrol.domain.reserva;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findAllByNomeClienteContainingIgnoreCase(String nome);
    boolean existsByDataReserva(LocalDate dataReserva);
    boolean existsByDataReservaAndIdNot(LocalDate dataReserva, Long id);
    List<Reserva> findAllByDataReservaBetween(LocalDate inicio, LocalDate fim);
}
