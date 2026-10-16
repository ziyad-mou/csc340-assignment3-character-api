
package com.marvel.characterapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "characters")
public class MarvelCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characterId;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 2000)
    private String description;

    private String universe;
    private String role;
    private Integer powerLevel;

    public MarvelCharacter() {
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUniverse() {
        return universe;
    }

    public void setUniverse(String universe) {
        this.universe = universe;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(Integer powerLevel) {
        this.powerLevel = powerLevel;
    }
}
