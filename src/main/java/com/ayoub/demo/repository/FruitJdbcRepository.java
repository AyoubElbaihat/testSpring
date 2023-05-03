package com.ayoub.demo.repository;

import com.ayoub.demo.entity.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FruitJdbcRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private RowMapper<Fruit> rowMapper = new RowMapper<Fruit>() {
        @Override
        public Fruit mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Fruit(
                    rs.getInt("id"),
                    rs.getString("name")
            );
        }
    };
    public List<Fruit> findAll(){
        String querySql = "SELECT * FROM fruits";
        List<Fruit> fruitList = jdbcTemplate.query(querySql, rowMapper);
        return fruitList;
    }
    public Optional<Fruit> findById(Integer id) {
        String querySql = "SELECT * FROM fruits WHERE id=?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(querySql, rowMapper, id));
    }
    public Fruit addFruit(Fruit entity) {
        jdbcTemplate.update("INSERT INTO fruits (name) VALUES (?)", new Object[]{entity.getName()});
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        Fruit createdFruit = new Fruit(id, entity.getName());
        return createdFruit;
    }
}
