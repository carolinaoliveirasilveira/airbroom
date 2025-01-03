package br.com.airb.room.service;

import br.com.airb.room.model.Address;
import br.com.airb.room.model.AdvertiserPerson;
import br.com.airb.room.model.Contact;
import br.com.airb.room.model.dto.AddressesDto;
import br.com.airb.room.model.dto.ContactsDto;
import br.com.airb.room.model.dto.RequestAdvertiserPersonDto;
import br.com.airb.room.model.dto.ResponseAdvertiserPersonDto;
import br.com.airb.room.model.enums.ContactEnum;
import br.com.airb.room.repository.AddressRepository;
import br.com.airb.room.repository.AdvertiserPersonRepository;
import br.com.airb.room.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class AdvertiserPersonService {

    @Autowired
    private AdvertiserPersonRepository advertiserPersonRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    public ResponseAdvertiserPersonDto createAdvertiser(RequestAdvertiserPersonDto requestAdvertiserPersonDto) {
        AdvertiserPerson advertiserPerson = toAdvertiserDto(requestAdvertiserPersonDto);

        AdvertiserPerson returnAdvertiserPerson = advertiserPersonRepository.save(advertiserPerson);
        ArrayList<Contact> returnContacts = new ArrayList<>();
        if (requestAdvertiserPersonDto.contacts() != null) {
            requestAdvertiserPersonDto.contacts().forEach(c -> {
                Contact contact = new Contact();
                contact.setTypesContact(ContactEnum.valueOf(c.typeContacts()));
                contact.setContactDescription(c.descriptionContacts());
                contact.setIdAdvertiser(returnAdvertiserPerson.getId());
                returnContacts.add(contactRepository.save(contact));
            });
        }

        ArrayList<Address> returnAddresses = new ArrayList<>();
        if (requestAdvertiserPersonDto.addresses() != null) {
            requestAdvertiserPersonDto.addresses().forEach(a -> {
                Address address = new Address();
                address.setCity(a.city());
                address.setState(a.state());
                address.setCountry(a.country());
                address.setIdAdvertiser(returnAdvertiserPerson.getId()); // Configurando o idAdvertiser
                returnAddresses.add(addressRepository.save(address));
            });
        }


        ResponseAdvertiserPersonDto responseAdvertiserPersonDto = toConvertAdvertiserParaDto(returnAdvertiserPerson, returnContacts, returnAddresses);

        return responseAdvertiserPersonDto;
    }

    private ResponseAdvertiserPersonDto toConvertAdvertiserParaDto(AdvertiserPerson advertiserPerson, ArrayList<Contact> returnContacts, ArrayList<Address> returnAddresses) {
        ArrayList<ContactsDto> contactsDtos = new ArrayList<>();
        returnContacts.forEach(c -> {
            contactsDtos.add(new ContactsDto(c.getTypesContact().toString(), c.getContactDescription()));
        });

        ArrayList<AddressesDto> addressesDtos = new ArrayList<>();
        returnAddresses.forEach(e -> {
            addressesDtos.add(new AddressesDto(e.getCity().toString(), e.getState().toString(), e.getCountry().toString()));
        });


        ResponseAdvertiserPersonDto responseAdvertiserPersonDto =
                new ResponseAdvertiserPersonDto(
                        advertiserPerson.getId(),
                        advertiserPerson.getName(),
                        advertiserPerson.getCpfOrCnpj(),
                        advertiserPerson.getActive(),
                        contactsDtos, addressesDtos);
        return responseAdvertiserPersonDto;
    }

    private AdvertiserPerson toAdvertiserDto(RequestAdvertiserPersonDto requestAdvertiserPersonDto) {
        AdvertiserPerson advertiserPerson = new AdvertiserPerson();
        advertiserPerson.setName(requestAdvertiserPersonDto.name());
        advertiserPerson.setCpfOrCnpj(requestAdvertiserPersonDto.cpfOrCnpj());
        advertiserPerson.setActive(requestAdvertiserPersonDto.active());
        return advertiserPerson;

    }


    public boolean deleteAdvertiser(UUID id) {
        if (advertiserPersonRepository.existsById(id)) {
            advertiserPersonRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

