package br.com.airb.room.repository;

import br.com.airb.room.model.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservations, Long> {
}
