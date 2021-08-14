package com.meli.mutants.controller;

import com.meli.mutants.model.Dna;
import com.meli.mutants.model.DnaStats;
import com.meli.mutants.model.request.DnaRequest;
import com.meli.mutants.model.response.Response;
import com.meli.mutants.service.DnaArrayService;
import com.meli.mutants.service.DnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/meli/challenge")
public class MutantController {

    private final DnaService dnaService;
    private final DnaArrayService dnaArrayService;

    @Autowired
    public MutantController(DnaService dnaService, DnaArrayService dnaArrayService) {
        this.dnaService = dnaService;
        this.dnaArrayService = dnaArrayService;
    }

    @PostMapping(path = "/mutant", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> postDna(
            @RequestBody DnaRequest request) {
        try {
            boolean isMutant = dnaArrayService.isMutant(request.getDna());
            dnaService.saveDna(request.toString(), isMutant);
            return isMutant
                    ? ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), "mutant"))
                    : ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                            new Response(HttpStatus.FORBIDDEN.value(), "human"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new Response(HttpStatus.CONFLICT.value(), e.getMessage()));
        }
    }

    @GetMapping(path = "/dna/all")
    public @ResponseBody
    ResponseEntity<Iterable<Dna>> getDnaList() {
        try {
            Iterable<Dna> dnaList = dnaService.getDnaList();
            return ResponseEntity.ok(dnaList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(path = "/stats")
    public @ResponseBody
    ResponseEntity<DnaStats> getStats() {
        try {
            DnaStats stats = dnaService.getStats();
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
