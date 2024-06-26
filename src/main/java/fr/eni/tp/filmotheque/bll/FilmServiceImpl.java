package fr.eni.tp.filmotheque.bll;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.AvisDAO;
import fr.eni.tp.filmotheque.dal.FilmDAO;
import fr.eni.tp.filmotheque.dal.GenreDAO;
import fr.eni.tp.filmotheque.dal.MembreDAO;
import fr.eni.tp.filmotheque.dal.ParticipantDAO;
import fr.eni.tp.filmotheque.exceptions.BusinessException;
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
	
	// Vérifier que le genre du film existe bien en bdd 
	private boolean checkGenreExist(long idGenre, BusinessException be) {
		boolean isValid = false;
		int nbIdFilm = genreDAO.countIdGenre(idGenre); 
		if(nbIdFilm == 1 ) {
			isValid = true;
		}else {
			be.add("Erreur, le genre de votre film n'existe pas !");
			
		}
		return isValid;
		
		
	}
	// verifier que le réalisateur existe bien en bdd

	private boolean checkDirector(long id, BusinessException be) {
		boolean isValid = false;
		
		int testIdDirector =  participantDAO.nbIdReal(id);
		if(testIdDirector == 1) {
			isValid = true; 
			
		}else {
			be.add("Le réalisateur n'est pas reconnu en bdd");
		}
		return isValid;
		
	}
	// vérifier que les acteurs du film existent bien en bdd
	private boolean checkActor(long id, BusinessException be) {
		boolean isValid = false;
		int testActor = participantDAO.nbIdActeur(id);
		
		if(testActor != 0) {
			isValid = true;
			
		}else {
			be.add("Les acteurs ne sont pas en base !");
		}
		
		
		return isValid;
		
	}
	//Vérifier que le titre du film est unique. Interdit de créer 2 films du même titre.
	private boolean checkTitle(long id, BusinessException be) {
		boolean isValid =false;
		String title = filmDAO.findTitre(id);
		
		if(title == null) {
			isValid = true;
			
		}else {
			be.add("Erreur, le titre est déjà connu en base. Interdis d'en mettre 2 !");
		}
		
		
		
		
		return isValid;
		
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
//		Film f = filmDAO.read(id);
//		Participant p = participantDAO.read(id);
//		if(p != null ) {
//			f.setRealisateur(p);
//		}
//		

		return participantDAO.read(id);
	}

	@Override
	public void creerFilm(Film film)throws BusinessException {
		BusinessException be = new BusinessException();
//		film.setGenre(new Genre(999, "inconnu"));
		boolean isValid = checkGenreExist(film.getGenre().getId(),be);
		isValid &= checkDirector(film.getRealisateur().getId(), be);
	//	isValid &= checkActor(film.getActeurs().forEach(a -> a.getId()), be);
		isValid &= checkTitle(film.getId(), be);
		
	    if (be.hasError()) {
	        throw be;
	    }
		
		System.out.println(isValid);
		
		if(isValid) {
			try {
				filmDAO.create(film);
				System.out.println(film);
				film.getActeurs().forEach(a -> participantDAO.createActeur(a.getId(),film.getId()));
				
			} catch (Exception e) {
				be.add("erreur le film n'est pas créer, un problème de mise en base est survenu !");
				
			}
			
			
		}else {
			throw be;
		}
		
		
		
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
