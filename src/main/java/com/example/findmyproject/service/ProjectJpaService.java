package com.example.findmyproject.service;

import com.example.findmyproject.model.*;
import com.example.findmyproject.repository.*;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class ProjectJpaService implements ProjectRepository {
    @Autowired
    public ProjectJpaRepository projectJpaRepository;

    @Autowired
    public ResearcherJpaRepository researcherJpaRepository;

    @Override
    public ArrayList<Project> getProjects() {
        List<Project> projectsList = projectJpaRepository.findAll();
        ArrayList<Project> projects = new ArrayList<>(projectsList);
        return projects;
    }

    @Override
    public Project addProject(Project project) {
        try {
            List<Integer> researcherIds = new ArrayList<>();
            for (Researcher researcher : project.getResearchers()) {
                researcherIds.add(researcher.getResearcherId());
            }

            List<Researcher> researchersList = researcherJpaRepository.findAllById(researcherIds);

            if (researchersList.size() != researcherIds.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            project.setResearchers(researchersList);
            Project savedProject = projectJpaRepository.save(project);
            return savedProject;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Project getProjectById(int projectId) {
        try {
            Project project = projectJpaRepository.findById(projectId).get();
            return project;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Project updateProject(int projectId, Project project) {
        try {
            Project newProject = projectJpaRepository.findById(projectId).get();
            if (project.getProjectName() != null) {
                newProject.setProjectName(project.getProjectName());
            }
            if (project.getBudget() > 0) {
                newProject.setBudget(project.getBudget());
            }
            if (project.getResearchers() != null) {
                List<Integer> researcherIds = new ArrayList<>();

                for (Researcher researcher : project.getResearchers()) {
                    researcherIds.add(researcher.getResearcherId());
                }

                List<Researcher> researchersList = researcherJpaRepository.findAllById(researcherIds);

                if (researchersList.size() != researcherIds.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                newProject.setResearchers(researchersList);
            }
            return projectJpaRepository.save(newProject);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteProject(int projectId) {
        try {
            projectJpaRepository.deleteById(projectId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);

    }

    @Override
    public List<Researcher> getProjectResearchers(int projectId) {
        try {
            Project project = projectJpaRepository.findById(projectId).get();
            return project.getResearchers();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}