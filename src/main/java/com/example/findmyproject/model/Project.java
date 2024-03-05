package com.example.findmyproject.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int projectId;
    @Column(name = "name")
    private String projectName;
    @Column(name = "budget")
    private double budget;
    @ManyToMany
    @JoinTable(name = "researcher_project", joinColumns = @JoinColumn(name = "projectid"), inverseJoinColumns = @JoinColumn(name = "researcherid"))
    @JsonIgnoreProperties("projects")
    private List<Researcher> researchers;

    public Project() {
    }

    public Project(int projectId, String projectName, double budget, List<Researcher> researchers) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.budget = budget;
        this.researchers = researchers;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public double getBudget() {
        return budget;
    }

    public List<Researcher> getResearchers() {
        return researchers;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setResearchers(List<Researcher> researchers) {
        this.researchers = researchers;
    }
}