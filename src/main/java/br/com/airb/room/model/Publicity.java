package br.com.airb.room.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "publicity")
public class Publicity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String localizacao;
    private double tamanho;

    @ElementCollection
    @CollectionTable(name = "mobilia", joinColumns = @JoinColumn(name = "publicity_id"))
    @Column(name = "item")
    private List<String> mobiliaDisponivel;

    private int quantidadePessoas;
    private int diaInicio;
    private int mesInicio;
    private int diaFim;
    private int mesFim;
    private boolean aceitaPets;
    private boolean aceitaCriancas;
    private String acessibilidade;

    @ElementCollection
    @CollectionTable(name = "fotos", joinColumns = @JoinColumn(name = "publicity_id"))
    @Column(name = "foto")
    private List<String> fotos;

    private double valor;

    @ManyToOne
    @JoinColumn(name = "anunciante_id")
    private AdvertiserPerson anunciante;

    // Getters e Setters
    public int getDiaFim() {
        return diaFim;
    }

    public void setDiaFim(int diaFim) {
        this.diaFim = diaFim;
    }

    public int getMesFim() {
        return mesFim;
    }

    public void setMesFim(int mesFim) {
        this.mesFim = mesFim;
    }

    public boolean isAceitaCriancas() {
        return aceitaCriancas;
    }

    public void setAceitaCriancas(boolean aceitaCriancas) {
        this.aceitaCriancas = aceitaCriancas;
    }

    public boolean isAceitaPets() {
        return aceitaPets;
    }

    public void setAceitaPets(boolean aceitaPets) {
        this.aceitaPets = aceitaPets;
    }

    public String getAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(String acessibilidade) {
        this.acessibilidade = acessibilidade;
    }

    public AdvertiserPerson getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(AdvertiserPerson anunciante) {
        this.anunciante = anunciante;
    }

    public int getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(int diaInicio) {
        this.diaInicio = diaInicio;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(int mesInicio) {
        this.mesInicio = mesInicio;
    }

    public List<String> getMobiliaDisponivel() {
        return mobiliaDisponivel;
    }

    public void setMobiliaDisponivel(List<String> mobiliaDisponivel) {
        this.mobiliaDisponivel = mobiliaDisponivel;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
