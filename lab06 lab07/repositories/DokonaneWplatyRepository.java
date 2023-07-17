package com.lab07.internetprovider.repositories;


import com.lab07.internetprovider.tables.DokonaneWplaty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DokonaneWplatyRepository extends JpaRepository<DokonaneWplaty, Long> {

    List<DokonaneWplaty> findAll();

    Optional<DokonaneWplaty> findById(Long id);

    void deleteById(Long id);

    DokonaneWplaty save(DokonaneWplaty dokonanaWplata);

}

