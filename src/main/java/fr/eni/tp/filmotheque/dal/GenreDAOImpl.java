package fr.eni.tp.filmotheque.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import fr.eni.tp.filmotheque.bo.Genre;

@Repository
public class GenreDAOImpl implements GenreDAO{
	private static final String FIND_BY_ID ="SELECT id,titre FROM GENRE WHERE id = :id";
	private static final String FIND_ALL = "SELECT id,titre FROM GENRE";
	private static final String COUNT_BY_IDGENRE = "SELECT count(*) FROM GENRE WHERE id = :id";
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	

	public GenreDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Genre read(long id) {
		MapSqlParameterSource mapSqlParam = new MapSqlParameterSource();
		mapSqlParam.addValue("id", id);
		
		return jdbcTemplate.queryForObject(FIND_BY_ID, mapSqlParam, new BeanPropertyRowMapper<>(Genre.class));
	}

	@Override
	public List<Genre> findAll() {

		return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Genre.class));
	}
	
	public int countIdGenre(long id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		
		
		
		
		return jdbcTemplate.queryForObject(COUNT_BY_IDGENRE, mapSqlParameterSource, Integer.class);
		
	}

}
