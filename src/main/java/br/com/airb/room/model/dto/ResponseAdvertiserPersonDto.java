package br.com.airb.room.model.dto;

import java.util.List;
import java.util.UUID;

public record ResponseAdvertiserPersonDto(
        UUID id,
        String name,
        String cpfOrCnpj,
        Boolean active,
        List<ContactsDto> contacts,
        List<AddressesDto> addresses) {
}
