package com.example.findmyproject.controller;

import com.example.findmyproject.model.*;
import com.example.findmyproject.repository.*;
import com.example.findmyproject.service.*;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ResearcherController {
    @Autowired
    public ResearcherJpaService researcherJpaService;

    @GetMapping("/researchers")
    public ArrayList<Researcher> getresearchers() {
        return researcherJpaService.getResearchers();
    }

    @PostMapping("/researchers")
    public Researcher addResearcher(@RequestBody Researcher researcher) {
        return researcherJpaService.addResearcher(researcher);
    }

    @GetMapping("/researchers/{researcherId}")
    public Researcher getResearcherById(@PathVariable("researcherId") int researcherId) {
        return researcherJpaService.getResearcherById(researcherId);
    }

    @PutMapping("/researchers/{researcherId}")
    public Researcher updateResearcher(@PathVariable("researcherId") int researcherId,
            @RequestBody Researcher researcher) {
        return researcherJpaService.updateResearcher(researcherId, researcher);
    }

    @DeleteMapping("/researchers/{researcherId}")
    public void deleteResearcher(@PathVariable("researcherId") int researcherId) {
        researcherJpaService.deleteResearcher(researcherId);
    }

    @GetMapping("/researchers/{researcherId}/projects")
    public List<Project> getResearcherProjects(@PathVariable("researcherId") int researcherId) {
        return researcherJpaService.getResearcherProjects(researcherId);
    }

}