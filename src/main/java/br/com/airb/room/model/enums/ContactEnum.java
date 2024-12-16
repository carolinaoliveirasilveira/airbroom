package br.com.airb.room.model.enums;

public enum ContactEnum {
    EMAIL("E-mail"),
    TELEFONE("Telefone");

    private final String status;

    // Construtor
    ContactEnum(String status) {
        this.status = status;
    }

    // Método para obter o valor do status
    public String getStatus() {
        return status;
    }

}
