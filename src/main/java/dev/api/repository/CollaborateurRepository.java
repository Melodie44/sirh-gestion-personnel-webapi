package dev.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.api.entite.Collaborateur;
import dev.api.entite.Departement;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Integer>{

	public List<Collaborateur> findByDepartement(Departement departement);
	
	public Collaborateur findByMatricule(String matricule);

}
