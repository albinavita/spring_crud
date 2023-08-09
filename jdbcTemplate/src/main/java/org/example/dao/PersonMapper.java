package org.example.dao;

import org.example.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Маппер можно создать вручную, но есть
 * в Spring и библиотечный маппер
 * класс называется BeanPropertyRowMapper
 * import org.springframework.jdbc.core.BeanPropertyRowMapper;
 */

public class PersonMapper implements RowMapper {
    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setEmail(resultSet.getString("email"));
        person.setAge(resultSet.getInt("age"));

        return person;
    }
}
