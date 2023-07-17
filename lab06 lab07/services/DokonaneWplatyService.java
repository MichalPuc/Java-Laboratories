package com.lab07.internetprovider.services;


import com.lab07.internetprovider.repositories.DokonaneWplatyRepository;
import com.lab07.internetprovider.tables.DokonaneWplaty;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DokonaneWplatyService {

    private final DokonaneWplatyRepository dokonanaWplataRepository;

    public DokonaneWplatyService(DokonaneWplatyRepository dokonanaWplataRepository) {
        this.dokonanaWplataRepository = dokonanaWplataRepository;
    }

    public DokonaneWplaty findById(Long id) {
        Optional<DokonaneWplaty> dokonanaWplataOptional = dokonanaWplataRepository.findById(id);
        if (dokonanaWplataOptional.isPresent()) {
            return dokonanaWplataOptional.get();
        } else {
            throw new EntityNotFoundException("Dokonana wplata with id " + id + " not found");
        }
    }

    public List<DokonaneWplaty> findAll() {
        return dokonanaWplataRepository.findAll();
    }

    public DokonaneWplaty create(DokonaneWplaty dokonanaWplata) {
        return dokonanaWplataRepository.save(dokonanaWplata);
    }

    public DokonaneWplaty update(DokonaneWplaty dokonanaWplata) {
        if (dokonanaWplata.getId() == null) {
            throw new IllegalArgumentException("Id cannot be null when updating a DokonanaWplata entity");
        }
        return dokonanaWplataRepository.save(dokonanaWplata);
    }

    public void deleteById(Long id) {
        dokonanaWplataRepository.deleteById(id);
    }
}
