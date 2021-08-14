package com.meli.mutants.repository;

import com.meli.mutants.model.Dna;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface DnaRepository extends CrudRepository<Dna, Integer> {

    @Query(value = "select * from dna where mutant = true", nativeQuery = true)
    Collection<Dna> getMutantsFromDna();

    @Query(value = "select * from dna where mutant = false", nativeQuery = true)
    Collection<Dna> getHumansFromDna();
}
