package com.lab07.internetprovider.repositories;


import com.lab07.internetprovider.tables.NaliczoneNaleznosci;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NaliczoneNaleznosciRepository extends JpaRepository<NaliczoneNaleznosci, Long> {
    List<NaliczoneNaleznosci> findAll();
    Optional<NaliczoneNaleznosci> findById(Long id);
    void deleteById(Long id);

    NaliczoneNaleznosci save(NaliczoneNaleznosci NaliczonaNaleznosc);
}
