package dev.api.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import dev.api.entite.BanqueInfos;
import dev.api.entite.Collaborateur;
import dev.api.entite.Departement;
import dev.api.repository.BanqueInfosRepository;
import dev.api.repository.CollaborateurRepository;
import dev.api.repository.DepartementRepository;

@Component
public class AppliStartupListener implements ApplicationListener<ApplicationReadyEvent>{

	@Autowired
	DepartementRepository depRepo;
	@Autowired
	CollaborateurRepository collabRepo;
	@Autowired
	BanqueInfosRepository banqueInfosRepo;

	/**
	 * This event is executed as late as conceivably possible to indicate that the
	 * application is ready to service requests.
	 */
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		Departement dep1 = new Departement();
		dep1.setNom("Informatique");
		Departement dep2 = new Departement();
		dep2.setNom("Administration");
		Departement dep3 = new Departement();
		dep3.setNom("Ressources Humaines");
		Departement dep4 = new Departement();
		dep4.setNom("Comptabilité");
		Departement dep5 = new Departement();
		dep4.setNom("Direction");
		List<Departement> departements = new ArrayList<>();
		departements.add(dep1);
		departements.add(dep2);
		departements.add(dep3);
		departements.add(dep4);
		departements.add(dep5);
		depRepo.save(departements);
		
		BanqueInfos banqueInfo1 = new BanqueInfos();
		banqueInfo1.setNom("Société Générale");
		banqueInfo1.setBic("SOGEFRPP");
		banqueInfo1.setIban("IBAN 2006 3053 3309");
		banqueInfosRepo.save(banqueInfo1);
		Collaborateur collab1 = new Collaborateur();
		collab1.setMatricule("M01");
		collab1.setNom("Dupont");
		collab1.setPrenom("Jean-Jacques");
		collab1.setBanqueInfos(banqueInfo1);
		collab1.setDepartement(dep1);

		collabRepo.save(collab1);
		
		BanqueInfos banqueInfo2 = new BanqueInfos();
		banqueInfo2.setNom("LCL");
		banqueInfo2.setBic("LCLFRPP");
		banqueInfo2.setIban("IBAN 2006 3053 4452");
		banqueInfosRepo.save(banqueInfo2);
		Collaborateur collab2 = new Collaborateur();
		collab2.setMatricule("M02");
		collab2.setNom("Ricard");
		collab2.setPrenom("Daniel");
		collab2.setBanqueInfos(banqueInfo2);
		collab2.setDepartement(dep4);
		
		collabRepo.save(collab2);
		
	}

}
