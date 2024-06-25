package fr.eni.tp.filmotheque.dal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Membre;
@Repository
public class MembreDAoImpl implements MembreDAO {
	private static final String FIND_BY_ID ="SELECT id,nom,prenom,email,admin FROM MEMBRE WHERE id =:id";
	private static final String FIND_BY_EMAIL ="SELECT id,nom,prenom,email,admin FROM MEMBRE WHERE email =:email";

	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	
	public MembreDAoImpl(NamedParameterJdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Membre read(long id) {
	MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
	mapSqlParameterSource.addValue("id", id);
		return jdbcTemplate.queryForObject(FIND_BY_ID, mapSqlParameterSource, new MembreDAORowMapper());
	}

	@Override
	public Membre read(String email) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("email", email);
		return jdbcTemplate.queryForObject(FIND_BY_EMAIL, mapSqlParameterSource,new MembreDAORowMapper());
	}
	
	class MembreDAORowMapper implements RowMapper<Membre> {

		@Override
		public Membre mapRow(ResultSet rs, int rowNum) throws SQLException {
			Membre m = new Membre();
			m.setId(rs.getLong("id"));
			m.setNom(rs.getString("nom"));
			m.setPrenom(rs.getString("prenom"));
			m.setPseudo(rs.getString("email"));
			m.setAdmin(rs.getBoolean("admin"));



			return m;
		}
	}
}
