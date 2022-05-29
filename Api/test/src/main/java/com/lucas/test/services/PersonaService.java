package com.lucas.test.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import com.lucas.test.mapper.PersonaMapper;
import com.lucas.test.models.PersonaModel;
import com.lucas.test.repositories.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonaService implements PersonaRepository {
    private static final Logger log = LoggerFactory.getLogger(PersonaService.class);

    PersonaMapper personamapper = new PersonaMapper();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String count() {
        return jdbcTemplate
                .queryForObject("select count(*) from persona", Integer.class)+"";
    }

    @Override
    public String save(@RequestBody PersonaModel persona) {
        try {            
            return jdbcTemplate.update("INSERT INTO PERSONA (ID,NOMBRE,APELLIDO,CORREO,CLAVE,ESTADO) VALUES (?,?,?,?,?,?)",persona.getId(),persona.getNombre(),persona.getApellido(),persona.getCorreo(),persona.getPassword(),persona.getEstado())+"";
        } catch (Exception e) {
            log.info(e.getMessage()+"\n", e.getCause()+"\n", e.getClass());
            return "Error: "+e.getCause();
        }
    }

    @Override
    public String update(PersonaModel persona) {
        return jdbcTemplate.update(
            "update persona set nombre = ?,apellido=?,correo= ?,clave=?,estado=? where id = ?",
            persona.getNombre(),persona.getApellido(),persona.getCorreo(),persona.getPassword(),persona.getEstado(),persona.getId())+"";
    }

    @Override
    public String deleteById(Long id) {
        return jdbcTemplate.update("delete persona where id ="+id)+"";
    }

    @Override
    public List<PersonaModel> findAll() {
        PersonaMapper row = new PersonaMapper();
        return jdbcTemplate.query("select * from persona",row);
    }

    @Override
    public PersonaModel findById(Long id) {
        PersonaMapper row = new PersonaMapper();
        return jdbcTemplate.queryForObject("select * from persona where id="+id,row);
    }

    @Override
    public String getNameById(Long id) {
        return jdbcTemplate.queryForObject("select nombre from persona where id="+id,String.class);
    }
    
}
