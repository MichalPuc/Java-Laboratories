package com.lab07.internetprovider.controllers;


import com.lab07.internetprovider.services.NaliczoneNaleznosciService;
import com.lab07.internetprovider.tables.NaliczoneNaleznosci;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/naleznosci")
public class NaliczoneNaleznosciController {

    private final NaliczoneNaleznosciService naliczoneNaleznosciService;

    public NaliczoneNaleznosciController(NaliczoneNaleznosciService naliczoneNaleznosciService) {
        this.naliczoneNaleznosciService = naliczoneNaleznosciService;
    }

    @GetMapping
    public ResponseEntity<List<NaliczoneNaleznosci>> getAllNaliczoneNaleznosci() {
        List<NaliczoneNaleznosci> naleznosciList = naliczoneNaleznosciService.getAllNaliczoneNaleznosci();
        return ResponseEntity.ok(naleznosciList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NaliczoneNaleznosci> getNaliczoneNaleznosciById(@PathVariable Long id) {
        NaliczoneNaleznosci naleznosci = naliczoneNaleznosciService.getNaliczoneNaleznosciById(id);
        if (naleznosci == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(naleznosci);
    }

    @PostMapping
    public ResponseEntity<NaliczoneNaleznosci> createNaliczoneNaleznosci(@RequestBody NaliczoneNaleznosci naleznosci) {
        naleznosci = naliczoneNaleznosciService.createNaliczoneNaleznosci(naleznosci);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(naleznosci.getId())
                .toUri();
        return ResponseEntity.created(location).body(naleznosci);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NaliczoneNaleznosci> updateNaliczoneNaleznosci(@PathVariable Long id, @RequestBody NaliczoneNaleznosci naleznosci) {
        naleznosci.setId(id);
        naleznosci = naliczoneNaleznosciService.updateNaliczoneNaleznosci(naleznosci);
        if (naleznosci == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(naleznosci);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNaliczoneNaleznosci(@PathVariable Long id) {
        boolean deleted = naliczoneNaleznosciService.deleteNaliczoneNaleznosci(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}

