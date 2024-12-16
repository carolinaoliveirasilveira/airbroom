package br.com.airb.room.repository;

import br.com.airb.room.model.AdvertiserPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdvertiserPersonRepository extends JpaRepository<AdvertiserPerson, Long> {
}
