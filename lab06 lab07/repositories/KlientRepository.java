package com.lab07.internetprovider.repositories;

import com.lab07.internetprovider.tables.Klient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface KlientRepository extends JpaRepository<Klient, Long> {

}
