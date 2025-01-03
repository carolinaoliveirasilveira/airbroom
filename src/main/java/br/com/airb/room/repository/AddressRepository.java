package br.com.airb.room.repository;

import br.com.airb.room.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
