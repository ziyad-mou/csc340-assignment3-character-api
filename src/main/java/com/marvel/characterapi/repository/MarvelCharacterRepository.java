
package com.marvel.characterapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marvel.characterapi.entity.MarvelCharacter;

public interface MarvelCharacterRepository
        extends JpaRepository<MarvelCharacter, Long> {

    List<MarvelCharacter> findByUniverseIgnoreCase(String universe);

    List<MarvelCharacter> findByNameContainingIgnoreCase(String name);
}
