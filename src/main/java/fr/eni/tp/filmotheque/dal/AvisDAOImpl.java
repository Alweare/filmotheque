package fr.eni.tp.filmotheque.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.tp.filmotheque.bo.Avis;

import fr.eni.tp.filmotheque.bo.Membre;



@Repository
public class AvisDAOImpl implements AvisDAO{
	private static final String FIND_BY_ID_FILM = "SELECT id,note,commentaire,id_membre,id_film FROM AVIS WHERE id_film = :idFilm";
	private static final String FBIF ="SELECT AV.id, AV.note, AV.commentaire, FL.id AS id_film  FROM AVIS AV INNER JOIN FILM FL ON AV.id_film = FL.id WHERE id_film = :idFilm";
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	

	public AvisDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void create(Avis avis, long idFilm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Avis> findByFilm(long idFilm) {
	MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
	mapSqlParameterSource.addValue("idFilm", idFilm);
		return jdbcTemplate.query(FIND_BY_ID_FILM, mapSqlParameterSource, new AvisDAORowMapper());
	}
	
	class AvisDAORowMapper implements RowMapper<Avis> {

		@Override
		public Avis mapRow(ResultSet rs, int rowNum) throws SQLException {
			Avis a = new Avis();
			a.setId(rs.getLong("id"));
			a.setCommentaire(rs.getString("commentaire"));
			a.setNote(rs.getInt("note"));


			// Association
			Membre membre = new Membre();
			membre.setId(rs.getLong("id_membre"));
			a.setMembre(membre);
			


			return a;
		}
	}
	

	

}
