package com.lab07.internetprovider.repositories;


import com.lab07.internetprovider.tables.Instalacja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstalacjaRepository extends JpaRepository<Instalacja, Long> {
    List<Instalacja> findAll();
    Optional<Instalacja> findById(Long id);
    Instalacja save(Instalacja instalacja);
    void deleteById(Long id);
}

