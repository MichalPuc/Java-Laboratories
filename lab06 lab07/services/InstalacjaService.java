package com.lab07.internetprovider.services;


import com.lab07.internetprovider.repositories.InstalacjaRepository;
import com.lab07.internetprovider.tables.Instalacja;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstalacjaService {

    private final InstalacjaRepository instalacjaRepository;

    public InstalacjaService(InstalacjaRepository instalacjaRepository) {
        this.instalacjaRepository = instalacjaRepository;
    }

    public Instalacja create(Instalacja instalacja) {
        return instalacjaRepository.save(instalacja);
    }

    public Optional<Instalacja> getById(Long id) {
        return instalacjaRepository.findById(id);
    }

    public void delete(Long id) {
        instalacjaRepository.deleteById(id);
    }

    public List<Instalacja> getAll() {
        return instalacjaRepository.findAll();
    }
}

