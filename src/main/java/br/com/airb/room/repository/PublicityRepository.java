package br.com.airb.room.repository;

import br.com.airb.room.model.Publicity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicityRepository extends JpaRepository<Publicity, Long> {

    }
