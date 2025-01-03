package br.com.airb.room.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "publicity")
public class Publicity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private double size;
    private List<String> furnitureAvailable;
    private int amountPeople;
    private Date checkin;
    private Date checkout;
    private boolean acceptsPets;
    private boolean acceptschildren;
    private String accessibility;
    private UUID idAdvertiser;
    private List<String> photos;
    private double value;
    private String title;
    private String description;

    @OneToMany(mappedBy = "publicity")
    private List<Reservations> reservations;

    @ManyToOne
    @JoinColumn(name = "advertiser_id")
    private AdvertiserPerson advertiser;

    public boolean isAcceptschildren() {
        return acceptschildren;
    }

    public void setAcceptschildren(boolean acceptschildren) {
        this.acceptschildren = acceptschildren;
    }

    public boolean isAcceptsPets() {
        return acceptsPets;
    }

    public void setAcceptsPets(boolean acceptsPets) {
        this.acceptsPets = acceptsPets;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public int getAmountPeople() {
        return amountPeople;
    }

    public void setAmountPeople(int amountPeople) {
        this.amountPeople = amountPeople;
    }

    public AdvertiserPerson getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(AdvertiserPerson advertiser) {
        this.advertiser = advertiser;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getFurnitureAvailable() {
        return furnitureAvailable;
    }

    public void setFurnitureAvailable(List<String> furnitureAvailable) {
        this.furnitureAvailable = furnitureAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getIdAdvertiser() {
        return idAdvertiser;
    }

    public void setIdAdvertiser(UUID idAdvertiser) {
        this.idAdvertiser = idAdvertiser;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservations> reservations) {
        this.reservations = reservations;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
