package com.example.findmyproject.repository;

import com.example.findmyproject.model.*;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ResearcherJpaRepository extends JpaRepository<Researcher, Integer> {

}