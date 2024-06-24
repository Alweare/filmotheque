package fr.eni.tp.filmotheque.bll;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.FilmDAO;
import fr.eni.tp.filmotheque.dal.GenreDAO;
import fr.eni.tp.filmotheque.dal.ParticipantDAO;
@Service

public class FilmServiceImpl implements FilmService {
	
	private FilmDAO filmDAO;
	private GenreDAO genreDAO;
	private ParticipantDAO participantDAO;
	
	

	public FilmServiceImpl(FilmDAO filmDAO, GenreDAO genreDAO, ParticipantDAO participantDAO) {
	
		this.filmDAO = filmDAO;
		this.genreDAO = genreDAO;
		this.participantDAO = participantDAO;
		
	}

	@Override
	public List<Film> consulterFilms() {
		List<Film> listeFilm = filmDAO.findAll();
		listeFilm.forEach(c -> {
			c.setGenre(genreDAO.read(c.getGenre().getId()));
		c.setRealisateur(participantDAO.read(c.getRealisateur().getId()));
		});
	
		
		return  listeFilm;
	}

	@Override
	public Film consulterFilmParId(long id) {
		Film f  = filmDAO.read(id);
		Genre g = genreDAO.read(id);
		
		if (g !=null) {
			f.setGenre(g);
		}
		
		return f;
	}

	@Override
	public List<Genre> consulterGenres() {
		return genreDAO.findAll();
	}

	@Override
	public List<Participant> consulterParticipants() {
		
		return participantDAO.findAll();
	}

	@Override
	public Genre consulterGenreParId(long id) {

		return genreDAO.read(id);
	}

	@Override
	public Participant consulterParticipantParId(long id) {
		Film f = filmDAO.read(id);
		Participant p = participantDAO.read(id);
		if(p != null ) {
			f.setRealisateur(p);
		}
		

		return p;
	}

	@Override
	public void creerFilm(Film film) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String consulterTitreFilm(long id) {
		
		return filmDAO.findTitre(id);
	}

	@Override
	public void publierAvis(Avis avis, long idFilm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Avis> consulterAvis(long idFilm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Participant consulterParticipant(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
