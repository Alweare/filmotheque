package fr.eni.tp.filmotheque.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Membre;

@Controller
@RequestMapping("/creation-avis")
@SessionAttributes( {"membreSession"})
public class AvisController {
	
	private FilmService filmService;
	
	
	
	
	public AvisController(FilmService filmService) {

		this.filmService = filmService;
	}




	@GetMapping
	public String afficherCreationAvis(@RequestParam("idFilm")long idFilm, Model model) {
		Film film = this.filmService.consulterFilmParId(idFilm);
		Avis avis = new Avis();
		model.addAttribute("avis", avis);
		model.addAttribute("idFilm", idFilm);
		film.setAvis(avis);
		
		
		return "view-creation-avis";
		
	}
	
	@PostMapping
	public String creerAvis(@ModelAttribute("membreSession")Membre membreSession,Avis avis,@RequestParam("idFilm") long idFilm) {
		
		this.filmService.publierAvis(avis, idFilm);
		avis.setMembre(membreSession);
		
		return "redirect:/films";
		
		
	}

	

}
