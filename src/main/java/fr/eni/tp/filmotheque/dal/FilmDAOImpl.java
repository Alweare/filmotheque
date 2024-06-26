package fr.eni.tp.filmotheque.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
@Repository
public class FilmDAOImpl implements FilmDAO {
	private static final String FIND_ALL = "SELECT id,titre,annee,duree,synopsis,id_realisateur,id_genre FROM FILM";
	private static final String FIND_BY_ID = "SELECT id,titre,annee,duree,synopsis,id_realisateur,id_genre FROM FILM WHERE id =:id";
	private static final String FIND_BY_TITLE = "SELECT titre FROM FILM WHERE id = :id";
	private static final String COUNT_BY_ID ="SELECT count(*) FROM FILM WHERE id = :id";
	private static final String INSERT ="INSERT INTO FILM (titre,annee,duree,synopsis,id_realisateur,id_genre) VALUES (:titre,:annee,:duree,:synopsis,:id_realisateur,:id_genre)";
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	


	public FilmDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public void create(Film film) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("titre", film.getTitre());
		mapSqlParameterSource.addValue("annee", film.getAnnee());
		mapSqlParameterSource.addValue("duree", film.getDuree());
		mapSqlParameterSource.addValue("synopsis", film.getSynopsis());
		mapSqlParameterSource.addValue("id_genre", film.getGenre().getId());
		mapSqlParameterSource.addValue("id_realisateur", film.getRealisateur().getId());
		jdbcTemplate.update(INSERT, mapSqlParameterSource);
		
	}

	@Override
	public Film read(long id) {
		MapSqlParameterSource mapParameter = new MapSqlParameterSource();
		mapParameter.addValue("id", id);
		
		return jdbcTemplate.queryForObject(FIND_BY_ID, mapParameter, new FilmDAORowMapper());
	}

	@Override
	public List<Film> findAll() {

		return jdbcTemplate.query(FIND_ALL, new FilmDAORowMapper());
	}

	@Override
	public String findTitre(long id) {
		MapSqlParameterSource mapParameter = new MapSqlParameterSource();
		mapParameter.addValue("id", id);
	
		return jdbcTemplate.queryForObject(FIND_BY_TITLE, mapParameter, String.class);
	}
	class FilmDAORowMapper implements RowMapper<Film> {

		@Override
		public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
			Film f = new Film();
			f.setId(rs.getLong("id"));
			f.setTitre(rs.getString("titre"));
			f.setAnnee(rs.getInt("annee"));
			f.setDuree(rs.getInt("duree"));
			f.setSynopsis(rs.getString("synopsis"));
			

			// Association
			Genre genre = new Genre();
			genre.setId(rs.getLong("id_genre"));
			f.setGenre(genre);
			
			Participant realisateur = new Participant();
			realisateur.setId(rs.getLong("id_realisateur"));

			f.setRealisateur(realisateur);

			return f;
		}
	}
	@Override
	public int nbIdFilm(long id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id",id);
		
		return jdbcTemplate.queryForObject(COUNT_BY_ID, mapSqlParameterSource, Integer.class);
	}
	}


