package com.lab07.internetprovider.tables;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "naliczone_naleznosci")
public class NaliczoneNaleznosci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "termin_platnosci")
    private Date terminPlatnosci;

    @Column(name = "kwota_do_zaplaty")
    private Integer kwotaDoZaplaty;

    // konstruktory, gettery, settery, equals, hashCode, toString
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}

