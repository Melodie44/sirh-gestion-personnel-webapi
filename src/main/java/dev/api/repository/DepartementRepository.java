package dev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.api.entite.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Integer>{

}
