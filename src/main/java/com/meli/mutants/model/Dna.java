package com.meli.mutants.model;

import javax.persistence.*;

@Entity
@Table(
        name="dna",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"dna"})
)
public class Dna {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String dna;
    private boolean mutant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }
}
