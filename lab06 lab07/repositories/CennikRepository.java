package com.lab07.internetprovider.repositories;

import com.lab07.internetprovider.tables.Cennik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CennikRepository extends JpaRepository<Cennik, Long> {
    List<Cennik> findAll();
    Optional<Cennik> findById(Long id);

    Cennik save(Cennik cennik);
    void deleteById(Long id);
}

