package fr.eni.tp.filmotheque.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
@Repository
public class ParticipantDAOImpl implements ParticipantDAO{
	
	private static final String FIND_BY_ID = "SELECT id,nom,prenom FROM PARTICIPANT WHERE id = :id";
	private static final String FIND_ALL = "SELECT id,nom,prenom FROM PARTICIPANT";
	private static final String FIND_ACTEURS = "SELECT id,nom,prenom FROM PARTICIPANT INNER JOIN ACTEURS ON PARTICIPANT.id = ACTEURS.id_participant WHERE id_film = :idFilm";
	private static final String INSERT_ACTEUR = "INSERT INTO ACTEURS (id_film,id_participant) VALUES (:idFilm,:idParticipant)";
	private static final String COUNT_ID_REAL = "SELECT count(*) FROM PARTICIPANT WHERE id = :idRealisateur";
	private static final String COUNT_ID_ACTEUR = "SELECT count(*) FROM PARTICIPANT WHERE id = :idActeur";
	
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	

	public ParticipantDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Participant read(long id) {
		MapSqlParameterSource mapParam = new MapSqlParameterSource();
		mapParam.addValue("id", id);
	
		return jdbcTemplate.queryForObject(FIND_BY_ID, mapParam, new BeanPropertyRowMapper<>(Participant.class));
	}

	@Override
	public List<Participant> findAll() {
		
		return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Participant.class));
	}

	@Override
	public List<Participant> findActeurs(long idFilm) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("idFilm", idFilm);
	
		return jdbcTemplate.query(FIND_ACTEURS, mapSqlParameterSource, new BeanPropertyRowMapper<>(Participant.class));
	}

	@Override
	public void createActeur(long idParticipant, long idFilm) {
	
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("idFilm", idFilm);
		System.out.println(idFilm);
		mapSqlParameterSource.addValue("idParticipant", idParticipant);
		System.out.println(idParticipant);
		
		jdbcTemplate.update(INSERT_ACTEUR, mapSqlParameterSource);
		
	}
	@Override
	public int nbIdReal (long idRealisateur) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("idRealisateur", idRealisateur);
		
		
		
		return jdbcTemplate.queryForObject(COUNT_ID_REAL, mapSqlParameterSource, Integer.class);
		
	}
	@Override
	public int nbIdActeur (long idActeur) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("idActeur", idActeur);
		
		
		return jdbcTemplate.queryForObject(COUNT_ID_ACTEUR, mapSqlParameterSource, Integer.class);
		
	}
	
	

}
