package br.com.airb.room.model;

import br.com.airb.room.model.enums.ContactEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private ContactEnum typesContact;
    private String contactDescription;
    private UUID idAdvertiser;

    public String getContactDescription() {
        return contactDescription;
    }

    public void setContactDescription(String contactDescription) {
        this.contactDescription = contactDescription;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdAdvertiser() {
        return idAdvertiser;
    }

    public void setIdAdvertiser(UUID idAdvertiser) {
        this.idAdvertiser = idAdvertiser;
    }

    public ContactEnum getTypesContact() {
        return typesContact;
    }

    public void setTypesContact(ContactEnum typesContact) {
        this.typesContact = typesContact;
    }
}
