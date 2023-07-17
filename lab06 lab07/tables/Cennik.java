package com.lab07.internetprovider.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "cennik")
public class Cennik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cennik")
    private Long id;

    @Column(name = "typ_uslugi")
    private String typUslugi;

    @Column(name = "cena")
    private Double cena;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypUslugi() {
        return typUslugi;
    }

    public void setTypUslugi(String typUslugi) {
        this.typUslugi = typUslugi;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }
}
