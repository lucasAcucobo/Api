package com.lucas.test.repositories;

import java.util.List;

import com.lucas.test.models.PersonaModel;

public interface PersonaRepository {

    String save(PersonaModel persona);

    String update(PersonaModel persona);

    String deleteById(Long id);

    List<PersonaModel> findAll();

    PersonaModel findById(Long id);

    String getNameById(Long id);
    
}
