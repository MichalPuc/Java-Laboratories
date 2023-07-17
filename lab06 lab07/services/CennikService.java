package com.lab07.internetprovider.services;


import com.lab07.internetprovider.repositories.CennikRepository;
import com.lab07.internetprovider.tables.Cennik;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CennikService {

    private final CennikRepository cennikRepository;

    public CennikService(CennikRepository cennikRepository) {
        this.cennikRepository = cennikRepository;
    }

    public List<Cennik> getAllCenniki() {
        return cennikRepository.findAll();
    }

    public Cennik getCennikById(Long id) {
        return cennikRepository.findById(id).orElse(null);
    }

    public Cennik createCennik(Cennik cennik) {
        return cennikRepository.save(cennik);
    }

    public Cennik updateCennik(Long id, Cennik updatedCennik) {
        Cennik cennik = cennikRepository.findById(id).orElse(null);
        if (cennik != null) {
            cennik.setTypUslugi(updatedCennik.getTypUslugi());
            cennik.setId(updatedCennik.getId());
            cennik.setCena(updatedCennik.getCena());
            return cennikRepository.save(cennik);
        }
        return null;
    }

    public boolean deleteCennik(Long id) {
        Cennik cennik = cennikRepository.findById(id).orElse(null);
        if (cennik != null) {
            cennikRepository.delete(cennik);
            return true;
        }
        return false;
    }
}

