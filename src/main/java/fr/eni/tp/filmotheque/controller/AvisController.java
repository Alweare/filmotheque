package fr.eni.tp.filmotheque.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Avis;

@Controller
@RequestMapping("/creation-avis")
public class AvisController {
	
	private FilmService filmService;
	
	
	
	
	public AvisController(FilmService filmService) {

		this.filmService = filmService;
	}




	@GetMapping
	public String afficherCreationAvis(@RequestParam("id")long id, Model model) {
		Avis avis = new Avis();
		model.addAttribute("avis", avis);
		model.addAttribute("idFilm", id);
		
		
		return "view-creation-avis";
		
	}
	
	@PostMapping
	public String creerAvis(Avis avis,@RequestParam("id") long id) {
		
		this.filmService.publierAvis(avis, id);
		
		return "redirect:/films";
		
		
	}

	

}
