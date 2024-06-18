package fr.eni.tp.filmotheque.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Membre;

@Controller
@SessionAttributes( {"listeGenre","membreSession"})
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
	public String afficherGenre() {
		
		
		
		return "view-creation-film";
		
	}



	@ModelAttribute("listeGenre")
	public List<Genre> listeGenre() {
		List<Genre> listeGenre = this.filmService.consulterGenres();
		System.out.println("model attribute de la liste de genre");
	
		return listeGenre;
		


	}

}
