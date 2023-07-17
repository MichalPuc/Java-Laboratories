package com.lab07.internetprovider.controllers;


import com.lab07.internetprovider.services.InstalacjaService;
import com.lab07.internetprovider.tables.Instalacja;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instalacje")
public class InstalacjaController {

    private final InstalacjaService instalacjaService;

    public InstalacjaController(InstalacjaService instalacjaService) {
        this.instalacjaService = instalacjaService;
    }

    @GetMapping
    public List<Instalacja> getAll() {
        return instalacjaService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Instalacja> getById(@PathVariable Long id) {
        return instalacjaService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Instalacja create(@RequestBody Instalacja instalacja) {
        return instalacjaService.create(instalacja);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        instalacjaService.delete(id);
    }
}


