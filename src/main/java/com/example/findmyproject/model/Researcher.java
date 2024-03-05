package com.example.findmyproject.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

@Entity
@Table(name = "researcher")
public class Researcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int researcherId;
    @Column(name = "name")
    private String researcherName;
    @Column(name = "specialization")
    private String specialization;
    @ManyToMany(mappedBy = "researchers")
    @JsonIgnoreProperties("researchers")
    private List<Project> projects;

    public Researcher() {
    }

    public Researcher(int researcherId, String researcherName, String specialization, List<Project> projects) {
        this.researcherId = researcherId;
        this.researcherName = researcherName;
        this.specialization = specialization;
        this.projects = projects;
    }

    public int getResearcherId() {
        return researcherId;
    }

    public String getResearcherName() {
        return researcherName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setResearcherId(int researcherId) {
        this.researcherId = researcherId;
    }

    public void setResearcherName(String researcherName) {
        this.researcherName = researcherName;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}