
package com.marvel.characterapi.service;

import java.util.List;
import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.marvel.characterapi.entity.MarvelCharacter;
import com.marvel.characterapi.repository.MarvelCharacterRepository;

@Service
public class MarvelCharacterService {

    private final MarvelCharacterRepository repository;

    public MarvelCharacterService(
            MarvelCharacterRepository repository) {

        this.repository = repository;
    }

    // Get All Characters or Search
    public List<MarvelCharacter> getAllCharacters(
            String universe,
            String name) {

        boolean useUniverse =
                universe != null && !universe.isBlank();

        boolean useName =
                name != null && !name.isBlank();

        if (useUniverse && useName) {

            String searchName =
                    name.trim().toLowerCase(Locale.ROOT);

            return repository
                    .findByUniverseIgnoreCase(universe.trim())
                    .stream()
                    .filter(c -> c.getName() != null &&
                            c.getName()
                                    .toLowerCase(Locale.ROOT)
                                    .contains(searchName))
                    .toList();
        }

        if (useUniverse) {
            return repository
                    .findByUniverseIgnoreCase(universe.trim());
        }

        if (useName) {
            return repository
                    .findByNameContainingIgnoreCase(name.trim());
        }

        return repository.findAll();
    }

    // Get Character By ID
    public MarvelCharacter getCharacterById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Character not found"));
    }

    // Add Character
    public MarvelCharacter addCharacter(
            MarvelCharacter character) {

        character.setCharacterId(null);

        return repository.save(character);
    }

    // Update Character
    public MarvelCharacter updateCharacter(
            Long id,
            MarvelCharacter updated) {

        MarvelCharacter existing = getCharacterById(id);

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setUniverse(updated.getUniverse());
        existing.setRole(updated.getRole());
        existing.setPowerLevel(updated.getPowerLevel());
        existing.setImageUrl(updated.getImageUrl());

        return repository.save(existing);
    }

    // Delete Character
    public void deleteCharacter(Long id) {

        MarvelCharacter existing = getCharacterById(id);

        repository.delete(existing);
    }
}
