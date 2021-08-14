package com.meli.mutants.service.implementation;

import com.meli.mutants.service.DnaArrayService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DnaArrayServiceImplTest {

    private DnaArrayService dnaArrayService = new DnaArrayServiceImpl();

    @Test
    void exceptionBySize() {
        ArrayList<String> dna = new ArrayList<String>();
        dna.add("atcg");

        Assertions.assertThrows(Exception.class, () -> {
            dnaArrayService.isMutant(dna);
        });

    }

    @Test
    void isMutant() throws Exception {
        ArrayList<String> dna = new ArrayList<String>();
        dna.add("atcg");
        dna.add("cccc");
        dna.add("tttt");
        dna.add("atcg");

        Assertions.assertTrue(dnaArrayService.isMutant(dna));

    }

    @Test
    void isMutantVertical() throws Exception {
        ArrayList<String> dna = new ArrayList<String>();
        dna.add("atcg");
        dna.add("atcc");
        dna.add("attt");
        dna.add("atcg");

        Assertions.assertTrue(dnaArrayService.isMutant(dna));

    }

    @Test
    void isHuman() throws Exception {
        ArrayList<String> dna = new ArrayList<String>();
        dna.add("atcg");
        dna.add("cctc");
        dna.add("ttat");
        dna.add("atcg");

        Assertions.assertFalse(dnaArrayService.isMutant(dna));

    }

    @Test
    void exceptionByNitroBases() {
        ArrayList<String> dna = new ArrayList<String>();
        dna.add("xxxx");
        dna.add("cctc");
        dna.add("ttat");
        dna.add("atcg");

        Assertions.assertThrows(Exception.class, () -> {
            dnaArrayService.isMutant(dna);
        });

    }

}