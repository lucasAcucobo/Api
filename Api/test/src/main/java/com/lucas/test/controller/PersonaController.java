package com.lucas.test.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lucas.test.models.PersonaModel;
import com.lucas.test.services.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/persona")
public class PersonaController {
    private static final Logger log = LoggerFactory.getLogger(PersonaService.class);

    @Autowired
    PersonaService personaService;

    @GetMapping("/contar")
    public ResponseEntity<?> contarPersonas(){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.personaService.count());    
        } catch (Exception e) {
            String err=e.getCause().getMessage();
            log.info("Error: "+e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(err);
        }
        
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPersona(@RequestBody PersonaModel persona){
        try {
            String resp= this.personaService.save(persona);
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (Exception e) {
            String resp= e.getMessage()+e.getCause();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
        }
        
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editarPersona(@RequestBody PersonaModel persona){
        String resp = this.personaService.update(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarPersona(Long id){
        String resp = this.personaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> mostrarPersonas(){
        List<PersonaModel> resp = this.personaService.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping( "/buscar")
    public ResponseEntity<?> buscarPorId(Long id){
        PersonaModel resp= this.personaService.findById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping("/obtenernombre")
    public ResponseEntity<?> obtenerNombre(Long id){
        String resp = this.personaService.getNameById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}
