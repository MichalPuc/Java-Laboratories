package com.lab07.internetprovider.controllers;


import com.lab07.internetprovider.services.CennikService;
import com.lab07.internetprovider.tables.Cennik;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cennik")
public class CennikController {

    private final CennikService cennikService;

    public CennikController(CennikService cennikService) {
        this.cennikService = cennikService;
    }

    @GetMapping
    public ResponseEntity<List<Cennik>> getAll() {
        List<Cennik> cenniki = cennikService.getAllCenniki();
        return ResponseEntity.ok(cenniki);
    }

    @PostMapping
    public ResponseEntity<Cennik> createCennik(@RequestBody Cennik cennik) {
        Cennik createdCennik = cennikService.createCennik(cennik);
        return ResponseEntity.created(URI.create("/cennik/" + createdCennik.getId())).body(createdCennik);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cennik> updateCennik(@PathVariable Long id, @RequestBody Cennik cennik) {
        Cennik updatedCennik = cennikService.updateCennik(id, cennik);
        return ResponseEntity.ok(updatedCennik);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCennik(@PathVariable Long id) {
        cennikService.deleteCennik(id);
        return ResponseEntity.noContent().build();
    }
}
