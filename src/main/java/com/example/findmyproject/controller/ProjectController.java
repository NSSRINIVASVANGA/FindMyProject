package com.example.findmyproject.controller;

import com.example.findmyproject.model.*;
import com.example.findmyproject.service.*;
import com.example.findmyproject.repository.*;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ProjectController {
    @Autowired
    public ProjectJpaService projectJpaService;

    @GetMapping("/researchers/projects")
    public ArrayList<Project> getProjects() {
        return projectJpaService.getProjects();
    }

    @PostMapping("/researchers/projects")
    public Project addProject(@RequestBody Project project) {
        return projectJpaService.addProject(project);
    }

    @GetMapping("/researchers/projects/{projectId}")
    public Project getProjectById(@PathVariable("projectId") int projectId) {
        return projectJpaService.getProjectById(projectId);
    }

    @PutMapping("/researchers/projects/{projectId}")
    public Project updateProject(@PathVariable("projectId") int projectId, @RequestBody Project project) {
        return projectJpaService.updatePrject(projectId, project);
    }

    @DeleteMapping("/researchers/projects/{projectId}")
    public void deleteProject(@PathVariable("projectId") int projectId) {
        projectJpaService.deleteProject(projectId);
    }

    @GetMapping("/projects/{projectId}/researchers")
    public List<Researcher> getProjectResearchers(@PathVariable("projectId") int projectId) {
        return projectJpaService.getProjectResearchers(projectId);
    }
}