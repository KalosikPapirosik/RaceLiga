package raceliga.web;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import raceliga.services.exceptions.RaceSessionNotFoundException;

@ControllerAdvice
public class RaceErrorHandler {

    @ExceptionHandler(RaceSessionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String companyNotFound(RaceSessionNotFoundException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "error/race-not-found";
    }
}

