package fr.eni.tp.filmotheque.bll;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.AvisDAO;
import fr.eni.tp.filmotheque.dal.FilmDAO;
import fr.eni.tp.filmotheque.dal.GenreDAO;
import fr.eni.tp.filmotheque.dal.MembreDAO;
import fr.eni.tp.filmotheque.dal.ParticipantDAO;
@Service

public class FilmServiceImpl implements FilmService {
	
	private FilmDAO filmDAO;
	private GenreDAO genreDAO;
	private ParticipantDAO participantDAO;
	private AvisDAO avisDAO;
	private MembreDAO membreDAO;
	
	

	public FilmServiceImpl(FilmDAO filmDAO, GenreDAO genreDAO, ParticipantDAO participantDAO, AvisDAO avisDAO,MembreDAO membreDAO) {
	
		this.filmDAO = filmDAO;
		this.genreDAO = genreDAO;
		this.participantDAO = participantDAO;
		this.avisDAO = avisDAO;
		this.membreDAO = membreDAO;
		
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
		Genre g = genreDAO.read(f.getGenre().getId());
		Participant p = participantDAO.read(f.getRealisateur().getId());
		List<Avis> listeAvis = avisDAO.findByFilm(f.getId());
	
		f.setGenre(g);
		f.setRealisateur(p);
		f.setActeurs(participantDAO.findActeurs(f.getId()));
		if(listeAvis != null) {
			listeAvis.forEach(a -> a.setMembre(membreDAO.read(a.getMembre().getId())));
			f.setAvis(listeAvis);	
		}
		
	
		
		
		return f;
	}
	
	// Vérifier que le genre dy film existe bien en bdd 
	private boolean checkFilmExist(long id) {
		boolean isValid = false;
		int nbIdFilm = filmDAO.nbIdFilm(id);
		if(nbIdFilm == 0) {
			isValid = true;
		}
		
		
		return isValid;
		
		
	}
	// verifier que le réalisateur existe bien en bdd
	// vérifier que les acteurs du film existent bien en bdd
	//Vérifier que le titre du film est unique. Interdit de créer 2 films du même titre.

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
//		Film f = filmDAO.read(id);
//		Participant p = participantDAO.read(id);
//		if(p != null ) {
//			f.setRealisateur(p);
//		}
//		

		return participantDAO.read(id);
	}

	@Override
	public void creerFilm(Film film) {
		filmDAO.create(film);
		film.setGenre(genreDAO.read(film.getGenre().getId()));
		film.setRealisateur(participantDAO.read(film.getRealisateur().getId()));
		film.setActeurs(participantDAO.findActeurs(film.getId()));
		film.setAvis(avisDAO.findByFilm(film.getId()));
		
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
		
		
		return avisDAO.findByFilm(idFilm);
	}

	@Override
	public Participant consulterParticipant(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
