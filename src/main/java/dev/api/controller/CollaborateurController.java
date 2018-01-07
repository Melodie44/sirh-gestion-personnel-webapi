package dev.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.api.entite.BanqueInfos;
import dev.api.entite.Collaborateur;
import dev.api.repository.BanqueInfosRepository;
import dev.api.repository.CollaborateurRepository;
import dev.api.repository.DepartementRepository;

@RestController
@RequestMapping("/api/collaborateurs")
public class CollaborateurController {

	@Autowired
	private CollaborateurRepository collaborateurRepo;

	@Autowired
	private DepartementRepository departementRepo;
	
	@Autowired
	private BanqueInfosRepository banqueInfosRepo;

	@GetMapping
	public List<Collaborateur> ListeCollaborateurs() {

		return collaborateurRepo.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/")
	public List<Collaborateur> ListeCollaborateursIdDepartement(@RequestParam("departement") Integer idDepartement) {

		return collaborateurRepo.findByDepartement(departementRepo.findOne(idDepartement));
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{matricule}")
	public Collaborateur InfosCollaborateur(@PathVariable String matricule) {

		return collaborateurRepo.findByMatricule(matricule);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{matricule}")
	public Collaborateur ModifierCollaborateur(@PathVariable String matricule,
			@RequestBody Collaborateur collaborateur) {

		Collaborateur collab = collaborateurRepo.findByMatricule(matricule);
		collab.setMatricule(collaborateur.getMatricule());
		collab.setNom(collaborateur.getNom());
		collab.setPrenom(collaborateur.getPrenom());
		collab.setDepartement(collaborateur.getDepartement());
		collab.setBanqueInfos(collaborateur.getBanqueInfos());

		return collaborateurRepo.save(collab);

	}

	@RequestMapping(method = RequestMethod.GET, path = "/{matricule}/banque")
	public BanqueInfos InfosBanqueCollaborateur(@PathVariable String matricule) {

		Collaborateur collab = collaborateurRepo.findByMatricule(matricule);
		BanqueInfos banqueInfos = banqueInfosRepo.findOne(collab.getBanqueInfos().getId());

		return banqueInfos;

	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{matricule}/banque")
	public BanqueInfos ModifierInfosBancairesCollaborateur(@PathVariable String matricule,
			@RequestBody BanqueInfos banqueInfos) {
		
		Collaborateur collab = collaborateurRepo.findByMatricule(matricule);
		BanqueInfos infosBanque = banqueInfosRepo.findOne(collab.getBanqueInfos().getId());
		infosBanque.setNom(banqueInfos.getNom());
		infosBanque.setBic(banqueInfos.getBic());
		infosBanque.setIban(banqueInfos.getIban());
		
		return banqueInfosRepo.save(infosBanque);
		
	}

}
