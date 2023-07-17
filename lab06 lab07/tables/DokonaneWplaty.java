package com.lab07.internetprovider.tables;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "dokonane_wplaty")
public class DokonaneWplaty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "termin_wplaty")
    private Date terminWplaty;

    @Column(name = "kwota_wplaty")
    private Integer kwotaWplaty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "naliczone_naleznosci_id")
    private NaliczoneNaleznosci naliczoneNaleznosci;

    // konstruktory, gettery i settery
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}

