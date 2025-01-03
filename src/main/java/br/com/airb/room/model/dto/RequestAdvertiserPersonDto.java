package br.com.airb.room.model.dto;

import java.util.List;

public record RequestAdvertiserPersonDto(
        String name,
        String cpfOrCnpj,
        Boolean active,
        List<ContactsDto> contacts,
        List<AddressesDto> addresses) {
}
