
package com.marvel.characterapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.characterapi.entity.MarvelCharacter;
import com.marvel.characterapi.service.MarvelCharacterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/characters")
public class MarvelCharacterController {

    private final MarvelCharacterService service;

    public MarvelCharacterController(MarvelCharacterService service) {
        this.service = service;
    }

    // Get all characters, filter, or search
    @GetMapping
    public List<MarvelCharacter> getAllCharacters(
            @RequestParam(required = false) String universe,
            @RequestParam(required = false) String name) {

        return service.getAllCharacters(universe, name);
    }

    // Get a character by ID
    @GetMapping("/{id}")
    public MarvelCharacter getCharacterById(@PathVariable Long id) {
        return service.getCharacterById(id);
    }

    // Add a new character
    @PostMapping
    public ResponseEntity<MarvelCharacter> addCharacter(
            @Valid @RequestBody MarvelCharacter character) {

        MarvelCharacter created = service.addCharacter(character);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Update a character
    @PutMapping("/{id}")
    public MarvelCharacter updateCharacter(
            @PathVariable Long id,
            @Valid @RequestBody MarvelCharacter character) {

        return service.updateCharacter(id, character);
    }

    // Delete a character
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {

        service.deleteCharacter(id);

        return ResponseEntity.noContent().build();
    }
}
