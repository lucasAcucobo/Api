package com.lucas.test.controller;

import java.util.List;
import com.lucas.test.models.PersonaModel;
import com.lucas.test.services.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @PostMapping()
    public ResponseEntity<?> guardarPersona(@RequestBody PersonaModel persona){
        try {
            String resp= this.personaService.save(persona);
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (Exception e) {
            String resp= e.getMessage()+e.getCause();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
        }
        
    }

    @PutMapping()
    public ResponseEntity<?> editarPersona(@RequestBody PersonaModel persona){
        String resp = this.personaService.update(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable Long id){
        String resp = this.personaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping()
    public ResponseEntity<?> mostrarPersonas(){
        List<PersonaModel> resp = this.personaService.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping( "/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        PersonaModel resp= this.personaService.findById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping("/obtenernombre")
    public ResponseEntity<?> obtenerNombre(Long id){
        String resp = this.personaService.getNameById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}
