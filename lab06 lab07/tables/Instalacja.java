package com.lab07.internetprovider.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "instalacja")
public class Instalacja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "adres")
    private String adres;

    @Column(name = "numer_routera")
    private String numerRoutera;

    @Column(name = "typ_uslugi")
    private String typUslugi;

    public Instalacja() {}

    public Instalacja(String adres, String numerRoutera, String typUslugi) {
        this.adres = adres;
        this.numerRoutera = numerRoutera;
        this.typUslugi = typUslugi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getNumerRoutera() {
        return numerRoutera;
    }

    public void setNumerRoutera(String numerRoutera) {
        this.numerRoutera = numerRoutera;
    }

    public String getTypUslugi() {
        return typUslugi;
    }

    public void setTypUslugi(String typUslugi) {
        this.typUslugi = typUslugi;
    }

}

