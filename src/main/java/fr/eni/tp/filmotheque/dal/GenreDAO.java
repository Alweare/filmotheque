package fr.eni.tp.filmotheque.dal;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.eni.tp.filmotheque.bo.Genre;

public interface GenreDAO {
	Genre read(long id);
	List<Genre> findAll();
	int countIdGenre(long id);

}
