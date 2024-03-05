package com.example.findmyproject.repository;

import com.example.findmyproject.model.*;

import java.util.*;

public interface ProjectRepository {
    ArrayList<Project> getProjects();

    Project addProject(Project project);

    Project getProjectById(int projectId);

    Project updateProject(int projectId, Project project);

    void deleteProject(int projectId);

    List<Researcher> getProjectResearchers(int projectId);
}