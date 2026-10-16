
package com.marvel.characterapi.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.marvel.characterapi.entity.MarvelCharacter;
import com.marvel.characterapi.repository.MarvelCharacterRepository;

@Service
public class MarvelCharacterService {

    private final MarvelCharacterRepository repository;

    public MarvelCharacterService(MarvelCharacterRepository repository) {
        this.repository = repository;
    }

    // Get all characters or filter/search
    public List<MarvelCharacter> getAllCharacters(String universe, String name) {
        if (universe != null) {
            return repository.findByUniverseIgnoreCase(universe);
        }

        if (name != null) {
            return repository.findByNameContainingIgnoreCase(name);
        }

        return repository.findAll();
    }

    // Get a character by ID
    public MarvelCharacter getCharacterById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Character not found"));
    }

    // Add a new character
    public MarvelCharacter addCharacter(MarvelCharacter character) {
        character.setCharacterId(null);
        return repository.save(character);
    }

    // Update an existing character
    public MarvelCharacter updateCharacter(Long id, MarvelCharacter updated) {
        MarvelCharacter character = getCharacterById(id);

        character.setName(updated.getName());
        character.setDescription(updated.getDescription());
        character.setUniverse(updated.getUniverse());
        character.setRole(updated.getRole());
        character.setPowerLevel(updated.getPowerLevel());

        return repository.save(character);
    }

    // Delete a character
    public void deleteCharacter(Long id) {
        MarvelCharacter character = getCharacterById(id);
        repository.delete(character);
    }
}
