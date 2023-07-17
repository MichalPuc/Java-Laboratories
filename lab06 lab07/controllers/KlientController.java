package com.lab07.internetprovider.controllers;


import com.lab07.internetprovider.services.KlientService;
import com.lab07.internetprovider.tables.Klient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class KlientController {

    @Autowired
    private  KlientService klientService;

    @PostMapping("/addKlient")
    public Klient addKlient(@RequestBody Klient klient)
    {
        return klientService.saveKlient(klient);
    }

    @PostMapping("/addKlients")
    public List<Klient> addKlients(@RequestBody List<Klient> klients)
    {
        return klientService.saveKlients(klients);
    }

    @GetMapping("/Klients")
    public List<Klient> findAllKlients()
    {
        return klientService.getKlients();
    }

    @GetMapping("/Klient/{id}")
    public Klient findKlientById(@PathVariable Long id)
    {
        return klientService.getKlientById(id);
    }

    @PutMapping("/update")
    public Klient updateKlient(@RequestBody Klient klient)
    {
        return klientService.updateKlient(klient);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteKlient(@PathVariable Long id)
    {
        return klientService.deleteKlient(id);
    }
}
