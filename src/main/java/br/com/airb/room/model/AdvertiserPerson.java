package br.com.airb.room.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "advertiser_person")
public class AdvertiserPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpfOuCnpj;
    private String endereco;
    private String cidade;
    private String estado;
    private String pais;
    private boolean ativo;

    @OneToMany(mappedBy = "anunciante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Publicity> anuncios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public List<Publicity> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Publicity> anuncios) {
        this.anuncios = anuncios;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String mostrarDetalhes() {
        return new StringBuilder()
                .append("Nome: ").append(nome)
                .append("\nEmail: ").append(email)
                .append("\nTelefone: ").append(telefone)
                .append("\nCPF/CNPJ: ").append(cpfOuCnpj)
                .append("\nEndereço: ").append(endereco)
                .append("\nCidade: ").append(cidade)
                .append("\nEstado: ").append(estado)
                .append("\nPaís: ").append(pais)
                .append("\nStatus: ").append(ativo ? "Ativo" : "Inativo")
                .append("\nTotal de Anúncios: ").append(anuncios != null ? anuncios.size() : 0)
                .toString();
    }
}

