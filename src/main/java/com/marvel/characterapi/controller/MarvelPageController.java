
package com.marvel.characterapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marvel.characterapi.entity.MarvelCharacter;
import com.marvel.characterapi.service.MarvelCharacterService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class MarvelPageController {

    private final MarvelCharacterService service;

    public MarvelPageController(MarvelCharacterService service) {
        this.service = service;
    }

    // Gallery and Search
    @GetMapping("/")
    public String showGallery(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String universe,
            Model model) {

        model.addAttribute("characters",
                service.getAllCharacters(universe, name));

        model.addAttribute("nameQuery", name);
        model.addAttribute("universeQuery", universe);

        return "index";
    }

    // About Page
    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }

    // Character Details
    @GetMapping("/characters/{id}")
    public String showDetails(
            @PathVariable Long id,
            Model model,
            HttpServletResponse response) {

        try {
            model.addAttribute("character",
                    service.getCharacterById(id));

            return "details";

        } catch (ResponseStatusException ex) {
            return showNotFound(ex, model, response);
        }
    }

    // Display Add Form
    @GetMapping("/characters/new")
    public String showAddForm(Model model) {

        model.addAttribute("character",
                new MarvelCharacter());

        prepareForm(model, false, null);

        return "character-form";
    }

    // Save New Character
    @PostMapping("/characters")
    public String addCharacter(
            @Valid @ModelAttribute("character")
            MarvelCharacter character,
            BindingResult result,
            Model model,
            RedirectAttributes redirect) {

        if (result.hasErrors()) {
            prepareForm(model, false, null);
            addValidationErrors(model, result);

            return "character-form";
        }

        MarvelCharacter saved =
                service.addCharacter(character);

        redirect.addFlashAttribute(
                "successMessage",
                saved.getName() + " was added.");

        return "redirect:/";
    }

    // Display Edit Form
    @GetMapping("/characters/{id}/edit")
    public String showEditForm(
            @PathVariable Long id,
            Model model,
            HttpServletResponse response) {

        try {
            model.addAttribute("character",
                    service.getCharacterById(id));

            prepareForm(model, true, id);

            return "character-form";

        } catch (ResponseStatusException ex) {
            return showNotFound(ex, model, response);
        }
    }

    // Update Existing Character
    @PostMapping("/characters/{id}")
    public String updateCharacter(
            @PathVariable Long id,
            @Valid @ModelAttribute("character")
            MarvelCharacter character,
            BindingResult result,
            Model model,
            RedirectAttributes redirect,
            HttpServletResponse response) {

        try {
            service.getCharacterById(id);

            if (result.hasErrors()) {
                prepareForm(model, true, id);
                addValidationErrors(model, result);

                return "character-form";
            }

            MarvelCharacter saved =
                    service.updateCharacter(id, character);

            redirect.addFlashAttribute(
                    "successMessage",
                    saved.getName() + " was updated.");

            return "redirect:/characters/" + id;

        } catch (ResponseStatusException ex) {
            return showNotFound(ex, model, response);
        }
    }

    // Delete Character
    @PostMapping("/characters/{id}/delete")
    public String deleteCharacter(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirect,
            HttpServletResponse response) {

        try {
            MarvelCharacter existing =
                    service.getCharacterById(id);

            String name = existing.getName();

            service.deleteCharacter(id);

            redirect.addFlashAttribute(
                    "successMessage",
                    name + " was removed.");

            return "redirect:/";

        } catch (ResponseStatusException ex) {
            return showNotFound(ex, model, response);
        }
    }

    // Prepare Add/Edit Form
    private void prepareForm(
            Model model,
            boolean editing,
            Long id) {

        model.addAttribute("editing", editing);

        model.addAttribute(
                "formTitle",
                editing ? "Edit Character" : "Add a Character");

        model.addAttribute(
                "formAction",
                editing ? "/characters/" + id : "/characters");
    }

    // Display Validation Errors
    private void addValidationErrors(
            Model model,
            BindingResult result) {

        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error ->
                errors.putIfAbsent(
                        error.getField(),
                        error.getDefaultMessage()));

        model.addAttribute("validationErrors", errors);
    }

    // Friendly 404 Page
    private String showNotFound(
            ResponseStatusException ex,
            Model model,
            HttpServletResponse response) {

        if (ex.getStatusCode().value()
                != HttpStatus.NOT_FOUND.value()) {
            throw ex;
        }

        response.setStatus(
                HttpServletResponse.SC_NOT_FOUND);

        model.addAttribute(
                "errorTitle",
                "Character Not Found");

        model.addAttribute(
                "errorMessage",
                "Sorry, that character does not exist.");

        return "error";
    }
}
