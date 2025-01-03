package br.com.airb.room.model.enums;

public enum EnumAddress {
    CITY("City"),
    STATE("State"),
    COUNTRY("Country");

    private final String status;

    EnumAddress(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}

