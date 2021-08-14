package com.meli.mutants.model.request;

import java.util.ArrayList;

public class DnaRequest {

    private ArrayList<String> dna;

    public ArrayList<String> getDna() {
        return dna;
    }

    public void setDna(ArrayList<String> dna) {
        this.dna = dna;
    }

    @Override
    public String toString() {
        dna.replaceAll(String::toUpperCase);
        return String.join(",", dna);
    }
}
