
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
        if (universe != null && !universe.isBlank()
                && name != null && !name.isBlank()) {

            String searchName = name.trim().toLowerCase();

            return repository.findByUniverseIgnoreCase(universe.trim())
                    .stream()
                    .filter(character -> character.getName()
                            .toLowerCase().contains(searchName))
                    .toList();
        }

        if (universe != null && !universe.isBlank()) {
            return repository.findByUniverseIgnoreCase(universe.trim());
        }

        if (name != null && !name.isBlank()) {
            return repository.findByNameContainingIgnoreCase(name.trim());
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
        character.setImageUrl(updated.getImageUrl());

        return repository.save(character);
    }

    // Delete a character
    public void deleteCharacter(Long id) {
        MarvelCharacter character = getCharacterById(id);
        repository.delete(character);
    }
}
