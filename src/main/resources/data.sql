DROP TABLE IF EXISTS mutants;

CREATE TABLE dna (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  dna VARCHAR(50) NOT NULL,
  mutant BOOLEAN
);
