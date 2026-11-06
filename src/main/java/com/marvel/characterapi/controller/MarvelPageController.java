
package com.marvel.characterapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.marvel.characterapi.service.MarvelCharacterService;

@Controller
public class MarvelPageController {

    private final MarvelCharacterService service;

    public MarvelPageController(MarvelCharacterService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String showGallery(Model model) {

        model.addAttribute("characters",
                service.getAllCharacters(null, null));

        return "index";
    }
}
