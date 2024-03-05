package com.example.findmyproject.service;

import com.example.findmyproject.repository.*;
import com.example.findmyproject.model.*;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class ResearcherJpaService implements ResearcherRepository {
    @Autowired
    public ResearcherJpaRepository researcherJpaRepository;

    @Autowired
    public ProjectJpaRepository projectJpaRepository;

    @Override
    public ArrayList<Researcher> getResearchers() {
        List<Researcher> researchersList = researcherJpaRepository.findAll();
        ArrayList<Researcher> researchers = new ArrayList<>(researchersList);
        return researchers;
    }

    @Override
    public Researcher addResearcher(Researcher researcher) {
        try {
            List<Integer> projectIds = new ArrayList<>();
            for (Project project : researcher.getProjects()) {
                projectIds.add(project.getProjectId());
            }
            List<Project> projects = projectJpaRepository.findAllById(projectIds);

            for (Project project : projects) {
                project.getResearchers().add(researcher);
            }
            researcher.setProjects(projects);
            Researcher savedResearcher = researcherJpaRepository.save(researcher);
            projectJpaRepository.saveAll(projects);
            return savedResearcher;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Researcher getResearcherById(int researcherId) {
        try {
            Researcher researcher = researcherJpaRepository.findById(researcherId).get();
            return researcher;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Researcher updateResearcher(int researcherId, Researcher researcher) {
        try {
            Researcher newResearcher = researcherJpaRepository.findById(researcherId).get();
            if (researcher.getResearcherName() != null) {
                newResearcher.setResearcherName(researcher.getResearcherName());
            }
            if (researcher.getSpecialization() != null) {
                newResearcher.setSpecialization(researcher.getSpecialization());
            }
            if (researcher.getProjects() != null) {
                List<Project> projects = newResearcher.getProjects();

                for (Project project : projects) {
                    project.getResearchers().remove(newResearcher);
                }
                projectJpaRepository.saveAll(projects);

                List<Integer> projectIds = new ArrayList<>();

                for (Project project : researcher.getProjects()) {
                    projectIds.add(project.getProjectId());
                }

                List<Project> newProjects = projectJpaRepository.findAllById(projectIds);

                for (Project project : newProjects) {
                    project.getResearchers().add(newResearcher);
                }
                projectJpaRepository.saveAll(newProjects);
                newResearcher.setProjects(newProjects);
            }
            return researcherJpaRepository.save(newResearcher);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteResearcher(int researcherId) {
        try {
            Researcher researcher = researcherJpaRepository.findById(researcherId).get();
            List<Project> projects = researcher.getProjects();
            for (Project project : projects) {
                project.getResearchers().remove(researcher);
            }
            projectJpaRepository.saveAll(projects);
            researcherJpaRepository.deleteById(researcherId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Project> getResearcherProjects(int researcherId) {
        try {
            Researcher researcher = researcherJpaRepository.findById(researcherId).get();
            return researcher.getProjects();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
