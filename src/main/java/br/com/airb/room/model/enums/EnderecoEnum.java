package br.com.airb.room.model.enums;

public enum EnderecoEnum {
    CIDADE("Cidade"),
    ESTADO("Estado"),
    PAIS("Pais");

    private final String status;

    // Construtor
    EnderecoEnum(String status) {
        this.status = status;
    }

    // Método para obter o valor do status
    public String getStatus() {
        return status;
    }

}

