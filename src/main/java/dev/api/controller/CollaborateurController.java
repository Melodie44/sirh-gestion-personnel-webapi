package dev.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.api.entite.Collaborateur;
import dev.api.repository.CollaborateurRepository;
import dev.api.repository.DepartementRepository;

@RestController
@RequestMapping("/api/collaborateurs")
public class CollaborateurController {

	@Autowired
	private CollaborateurRepository collaborateurRepo;

	@Autowired
	private DepartementRepository departementRepo;

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
	public @ResponseBody Collaborateur ModifierCollaborateur(@PathVariable String matricule,
			@RequestBody Collaborateur collaborateur) {

		Collaborateur collab = collaborateurRepo.findByMatricule(matricule);
		collab.setMatricule(collaborateur.getMatricule());
		collab.setNom(collaborateur.getNom());
		collab.setPrenom(collaborateur.getPrenom());
		collab.setDepartement(collaborateur.getDepartement());
		collab.setBanque(collaborateur.getBanque());
		collab.setBic(collaborateur.getBic());
		collab.setIban(collaborateur.getIban());

		collaborateurRepo.save(collab);

		return collab;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{matricule}/banque")
	public String InfosBanqueCollaborateur(@PathVariable String matricule) throws JSONException {

		String infosBancaires = collaborateurRepo.findInfosBancaires(matricule);

		JSONObject json = new JSONObject();

		String[] infos = infosBancaires.split(",");

		if (!infosBancaires.isEmpty()) {

			if (infos[0] != null) {
				json.put("banque", infos[0]);
			}

			if (infos[1] != null) {
				json.put("bic", infos[1]);
			}

			if (infos[2] != null) {
				json.put("iban", infos[2]);
			}

			if (json.length() == 0) {
				return "";
			} else {
				return json.toString();
			}
		} else {
			return "";
		}

	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{matricule}/banque")
	public @ResponseBody Collaborateur ModifierInfosBancairesCollaborateur(@PathVariable String matricule,
			@RequestBody Collaborateur collaborateur) {
		
		Collaborateur collab = new Collaborateur();
		collab.setBanque(collaborateur.getBanque());
		collab.setBic(collaborateur.getBic());
		collab.setIban(collaborateur.getIban());
		
		collaborateurRepo.save(collab);
		
		return collab;
	}

}
