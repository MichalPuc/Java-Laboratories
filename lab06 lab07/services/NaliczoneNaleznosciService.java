package com.lab07.internetprovider.services;


import com.lab07.internetprovider.repositories.NaliczoneNaleznosciRepository;
import com.lab07.internetprovider.tables.Klient;
import com.lab07.internetprovider.tables.NaliczoneNaleznosci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  NaliczoneNaleznosciService {

    @Autowired
    private NaliczoneNaleznosciRepository naliczoneNaleznosciRepository;


    public List<NaliczoneNaleznosci> getAllNaliczoneNaleznosci() {
        return naliczoneNaleznosciRepository.findAll();
    }

    public NaliczoneNaleznosci getNaliczoneNaleznosciById(Long id) {
        Optional<NaliczoneNaleznosci> naliczoneNaleznosci = naliczoneNaleznosciRepository.findById(id);
        if (naliczoneNaleznosci.isPresent()) {
            return naliczoneNaleznosci.get();
        } else {
            throw new RuntimeException("Naliczone naleznosci not found with id " + id);
        }
    }

    public NaliczoneNaleznosci createNaliczoneNaleznosci(NaliczoneNaleznosci naliczoneNaleznosci) {
        return naliczoneNaleznosciRepository.save(naliczoneNaleznosci);
    }

    public NaliczoneNaleznosci updateNaliczoneNaleznosci(NaliczoneNaleznosci naliczoneNaleznosci) {
        naliczoneNaleznosciRepository.save(naliczoneNaleznosci);
        return naliczoneNaleznosci;
    }

    public boolean deleteNaliczoneNaleznosci(Long id) {
        naliczoneNaleznosciRepository.deleteById(id);
        return true;
    }
}

