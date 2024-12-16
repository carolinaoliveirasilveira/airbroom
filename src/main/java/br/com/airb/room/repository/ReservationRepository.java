package br.com.airb.room.repository;

import br.com.airb.room.model.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservations, UUID> {
}
