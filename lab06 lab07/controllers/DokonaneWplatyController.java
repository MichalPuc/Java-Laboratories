package com.lab07.internetprovider.controllers;

import com.lab07.internetprovider.services.DokonaneWplatyService;
import com.lab07.internetprovider.tables.DokonaneWplaty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dokonane-wplaty")
public class DokonaneWplatyController {

    private final DokonaneWplatyService dokonaneWplatyService;

    public DokonaneWplatyController(DokonaneWplatyService dokonaneWplatyService) {
        this.dokonaneWplatyService = dokonaneWplatyService;
    }

    @GetMapping
    public List<DokonaneWplaty> findAll() {
        return dokonaneWplatyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DokonaneWplaty> findById(@PathVariable Long id) {
        Optional<DokonaneWplaty> foundDokonanaWplata = Optional.ofNullable(dokonaneWplatyService.findById(id));
        if (foundDokonanaWplata.isPresent()) {
            return ResponseEntity.ok(foundDokonanaWplata.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DokonaneWplaty> create(@RequestBody DokonaneWplaty dokonanaWplata) {
        DokonaneWplaty createdDokonanaWplata = dokonaneWplatyService.create(dokonanaWplata);
        return ResponseEntity.created(URI.create("/dokonane-wplaty/" + createdDokonanaWplata.getId()))
                .body(createdDokonanaWplata);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DokonaneWplaty> update(@PathVariable Long id, @RequestBody DokonaneWplaty dokonanaWplata) {
        Optional<DokonaneWplaty> foundDokonanaWplata = Optional.ofNullable(dokonaneWplatyService.findById(id));
        if (foundDokonanaWplata.isPresent()) {
            dokonanaWplata.setId(id);
            DokonaneWplaty updatedDokonanaWplata = dokonaneWplatyService.update(dokonanaWplata);
            return ResponseEntity.ok(updatedDokonanaWplata);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<DokonaneWplaty> foundDokonanaWplata = Optional.ofNullable(dokonaneWplatyService.findById(id));
        if (foundDokonanaWplata.isPresent()) {
            dokonaneWplatyService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

