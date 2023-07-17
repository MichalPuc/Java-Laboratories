package com.lab07.internetprovider.services;


import com.lab07.internetprovider.repositories.KlientRepository;
import com.lab07.internetprovider.tables.Klient;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KlientService {
    @Autowired
    private KlientRepository klientRepository;

    public Klient saveKlient(Klient klient)
    {
        return klientRepository.save(klient);
    }

    public List<Klient> saveKlients(List<Klient> klients)
    {
        return klientRepository.saveAll(klients);
    }
    public List<Klient> getKlients()
    {
        return klientRepository.findAll();
    }
    public Klient getKlientById(Long id)
    {
        return klientRepository.findById(id).orElse(null);
    }
    public String deleteKlient(Long id)
    {
        klientRepository.deleteById(id);
        return "Klient deleted !! "+id;
    }

    public Klient updateKlient(Klient klient)
    {
        Klient existingKlient=klientRepository.findById(klient.getIdKlienta()).orElse(null);
        existingKlient.setImie(klient.getImie());
        existingKlient.setNazwisko(klient.getNazwisko());
        existingKlient.setNumerKlienta(klient.getNumerKlienta());
        return klientRepository.save(existingKlient);
    }

}
