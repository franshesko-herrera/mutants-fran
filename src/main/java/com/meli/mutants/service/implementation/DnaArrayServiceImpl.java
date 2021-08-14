package com.meli.mutants.service.implementation;

import com.meli.mutants.service.DnaArrayService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DnaArrayServiceImpl implements DnaArrayService {

    public static final List<Character> ALLOWED_NITRO_BASES = Arrays.asList('A', 'C', 'T', 'G');
    public static final List<String> MUTANT_CHAINS = Arrays.asList("AAAA", "CCCC", "TTTT", "GGGG");

    @Override
    public boolean isMutant(ArrayList<String> dna) throws Exception {
        validateDna(dna);
        dna.replaceAll(String::toUpperCase);
        char[][] matrix = buildMatrixFromStringArray(dna);
        int mutantChains = 0;
        for (String chain : MUTANT_CHAINS) {
            mutantChains += findChainInMatrix(chain, matrix);
            if (mutantChains > 1) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    private void validateDna(ArrayList<String> dna) throws Exception {
        if (!dna.stream()
                .map(s -> s.length() == dna.size()
                        && s.length() >= 4
                        && validateAdnNitroBases(s.toUpperCase()))
                .reduce(Boolean::logicalAnd)
                .orElse(Boolean.FALSE)) {
            throw (new Exception("Data Error. (Matrix size / ADN nitro bases)"));
        }
    }

    private char[][] buildMatrixFromStringArray(ArrayList<String> array) {
        char[][] matrix = new char[array.size()][array.size()];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = array.get(i).toCharArray();
        }

        return matrix;
    }

    private int findChainInMatrix(String adnChain, char[][] matrix) {
        int count = 0;
        for (int[] pos : getStartPositionsByChain(adnChain.charAt(0), matrix)) {

            // Horizontal
            String chainFound = chainInMatrix(pos, adnChain.length(), 0, 1, matrix);
            if (chainFound.equals(adnChain)) {
                count++;
            }

            // Vertical
            chainFound = chainInMatrix(pos, adnChain.length(), 1, 0, matrix);
            if (chainFound.equals(adnChain)) {
                count++;
            }

            // Diagonal down - right
            chainFound = chainInMatrix(pos, adnChain.length(), -1, 1, matrix);
            if (chainFound.equals(adnChain)) {
                count++;
            }

            // Diagonal up - right
            chainFound = chainInMatrix(pos, adnChain.length(), 1, 1, matrix);
            if (chainFound.equals(adnChain)) {
                count++;
            }

        }

        return count;
    }

    private int[][] getStartPositionsByChain(char firstLetter, char[][] matrix) {

        List<int[]> positions = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == firstLetter) {
                    positions.add(new int[]{i, j});
                }
            }
        }
        return toArrayInt(positions);
    }

    private int[][] toArrayInt(List<int[]> list) {
        return list.toArray(new int[list.size()][list.get(0).length]);
    }


    private String chainInMatrix(
            int[] initPosition,
            int chainSize,
            int rowMove,
            int columnMove,
            char[][] matrix) {

        StringBuilder chain = new StringBuilder();
        int move = 0;
        int row = initPosition[0];
        int column = initPosition[1];

        while ((move < chainSize) &&
                (row < matrix.length && column < matrix.length) &&
                (row > -1 && column > -1)) {

            chain.append(matrix[row][column]);
            row = row + rowMove;
            column = column + columnMove;
            move++;
        }

        return chain.toString();
    }

    private boolean validateAdnNitroBases(String s) {
        for (char ch : s.toCharArray()) {
            if (!ALLOWED_NITRO_BASES.contains(ch)) {
                return false;
            }
        }
        return true;
    }

}
