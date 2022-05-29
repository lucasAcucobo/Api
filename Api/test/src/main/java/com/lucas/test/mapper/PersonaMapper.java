package com.lucas.test.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lucas.test.models.PersonaModel;

import org.springframework.jdbc.core.RowMapper;

public class PersonaMapper implements RowMapper<PersonaModel> {

    @Override
    public PersonaModel mapRow(ResultSet rs, int rowNum) throws SQLException {

        PersonaModel customer = new PersonaModel();
        customer.setId(rs.getLong("id"));
        customer.setNombre(rs.getString("nombre"));
        customer.setApellido(rs.getString("apellido"));
        customer.setCorreo(rs.getString("correo"));
        customer.setPassword(rs.getString("clave"));
        customer.setEstado(rs.getLong("estado"));
        return customer;

    }
    
}
