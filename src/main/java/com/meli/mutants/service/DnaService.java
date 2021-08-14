package com.meli.mutants.service;

import com.meli.mutants.model.Dna;
import com.meli.mutants.model.DnaStats;

public interface DnaService {

    void saveDna(String dna, boolean isMutant);

    Iterable<Dna> getDnaList();

    DnaStats getStats();
}
