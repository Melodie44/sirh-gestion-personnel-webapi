package dev.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dev.api.repository.DepartementRepository;

@Controller
@RequestMapping("/api/departements")
public class DepartementController {

	@Autowired
	private DepartementRepository departementRepo;

	@GetMapping
	public ModelAndView afficherAccueil() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("api/departements");
		mv.addObject("listeDepartements", departementRepo.findAll());
		return mv;
	}
}
