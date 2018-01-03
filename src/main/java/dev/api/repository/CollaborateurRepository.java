package dev.api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.api.entite.Collaborateur;
import dev.api.entite.Departement;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Integer>{

	public List<Collaborateur> findByDepartement(Departement departement);
	
	public Collaborateur findByMatricule(String matricule);
	
	@Query("SELECT c.banque, c.bic, c.iban FROM Collaborateur c WHERE c.matricule = :matricule")
	public String findInfosBancaires(@Param("matricule") String matricule);
}
