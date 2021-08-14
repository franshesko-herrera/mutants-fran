package com.meli.mutants.service.implementation;

import com.meli.mutants.model.Dna;
import com.meli.mutants.model.DnaStats;
import com.meli.mutants.repository.DnaRepository;
import com.meli.mutants.service.DnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DnaServiceImpl implements DnaService {

    private final DnaRepository dnaRepository;

    @Autowired
    public DnaServiceImpl(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    @Override
    public void saveDna(String dnaArray, boolean isMutant) {
        Dna dna = new Dna();
        dna.setDna(dnaArray);
        dna.setMutant(isMutant);
        dnaRepository.save(dna);
    }

    @Override
    public Iterable<Dna> getDnaList() {
        return dnaRepository.findAll();
    }

    @Override
    public DnaStats getStats() {
        int mutants = dnaRepository.getMutantsFromDna().size();
        int humans = dnaRepository.getHumansFromDna().size();
        float ratio = humans > 0 ? (float) mutants / (float) humans : mutants;
        return new DnaStats(mutants, humans, ratio);
    }
}
