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
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.bo.Participant;

@Controller
@SessionAttributes( {"membreSession","listeActeurs"})
public class FilmController{

	private FilmService filmService;


	public FilmController(FilmService filmService) {
		this.filmService = filmService;
	}


	@GetMapping("/films")
	public String afficherFilms(Model model) {
		List<Film> listeFilms = this.filmService.consulterFilms();
		model.addAttribute("film",listeFilms);

		return "view-films";



	}
	@GetMapping("/details")
	public String afficherUnFilm(@RequestParam("id") long id, Model model) {

		Film filmDetail = this.filmService.consulterFilmParId(id);
		model.addAttribute("filmDetail",filmDetail);

		System.out.printf("%s%n",filmService.consulterFilmParId(id));
		return "view-details";


	}

	@GetMapping("/creation-film")
	public String afficherCreationFilm(@ModelAttribute("membreSession") Membre membre,Model model) {
		if(!membre.isAdmin()) {
			return "redirect:/films";
		}else {
		
		
		
		Film filmCreer = new Film();
		
		model.addAttribute("filmCreer", filmCreer);
	
		return "view-creation-film";
		}
	}
	
	@PostMapping("/creation-film")
	public String creationFilm( @ModelAttribute("filmCreer")Film filmCreer) {
		
		
		this.filmService.creerFilm(filmCreer);
		
		return "redirect:/films";
		
	}
	



	@ModelAttribute("listeGenre")
	public List<Genre> listeGenre() {
		List<Genre> listeGenre = this.filmService.consulterGenres();
	
		return listeGenre;
		


	}
	@ModelAttribute("listeActeurs")
	public List<Participant> listeActeur(){
		List<Participant> listeActeur = this.filmService.consulterParticipants();
		return listeActeur;
	}


}
