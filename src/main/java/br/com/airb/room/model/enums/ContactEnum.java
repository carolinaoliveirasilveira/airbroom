package br.com.airb.room.model.enums;

public enum ContactEnum {
    EMAIL("EMAIL"),
    TELEPHONE("TELEPHONE");

    private final String status;

    ContactEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
