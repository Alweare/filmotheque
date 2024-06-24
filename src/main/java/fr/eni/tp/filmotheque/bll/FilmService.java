package fr.eni.tp.filmotheque.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;

@Service
public interface FilmService {
	
	List<Film> consulterFilms();
	
	Film consulterFilmParId(long id);
	
	List<Genre> consulterGenres();
	
	List<Participant> consulterParticipants();
	
	Genre consulterGenreParId(long id);
	Participant consulterParticipantParId(long id);
	void creerFilm(Film film);
	Participant consulterParticipant(long id);
	String consulterTitreFilm(long id);
	void publierAvis(Avis avis, long idFilm);
	List<Avis> consulterAvis(long idFilm);
	


}
