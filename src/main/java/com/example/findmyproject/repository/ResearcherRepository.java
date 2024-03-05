package com.example.findmyproject.repository;

import com.example.findmyproject.model.*;

import java.util.*;

public interface ResearcherRepository {

    ArrayList<Researcher> getResearchers();

    Researcher addResearcher(Researcher researcher);

    Researcher getResearcherById(int researcherId);

    Researcher updateResearcher(int researcherId, Researcher researcher);

    void deleteResearcher(int researcherId);

    List<Project> getResearcherProjects(int researcherId);
}