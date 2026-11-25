package raceliga.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import raceliga.dto.AddRaceSessionDTO;
import raceliga.dto.AddRacerDto;
import raceliga.models.enums.RacingClass;
import raceliga.services.RacerService;
import raceliga.services.RaceSessionService;
import raceliga.services.exceptions.RaceSessionNotFoundException;
import raceliga.services.exceptions.RacerAlreadyExistsException;

@Controller
@RequestMapping("/racers")
public class RacerController {

    private final RacerService racerService;
    private final RaceSessionService raceSessionService;

    public RacerController(RacerService racerService, RaceSessionService raceSessionService) {
        this.racerService = racerService;
        this.raceSessionService = raceSessionService;
    }

    @GetMapping
    public String allRacers(Model model){
        model.addAttribute("racers", racerService.allRacers());
        return "racers-all";
    }

    @GetMapping("/add")
    public String addRacer(Model model){
        model.addAttribute("racerForm", new AddRacerDto());
        model.addAttribute("allRaces", raceSessionService.allRaces());
        model.addAttribute("racingClasses", RacingClass.values());
        return "racer-add";
    }

    @PostMapping("/add")
    public String addRacer(
            @Valid @ModelAttribute("racerForm") AddRacerDto form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
            ){
        if (bindingResult.hasErrors()) {
            model.addAttribute("allRaces", raceSessionService.allRaces());
            model.addAttribute("racingClasses", RacingClass.values());
            return "racer-add";
        }
        try {
            racerService.addRacer(form);
        } catch (RacerAlreadyExistsException e) {
            bindingResult.rejectValue("racingNumber", "duplicate", e.getMessage());

            model.addAttribute("allRaces", raceSessionService.allRaces());
            model.addAttribute("racingClasses", RacingClass.values());
            return "racer-add";
        } catch (RaceSessionNotFoundException e){
            bindingResult.rejectValue("raceName", "notfound", e.getMessage());

            model.addAttribute("allRaces", raceSessionService.allRaces());
            model.addAttribute("racingClasses", RacingClass.values());
            return "racer-add";
        }

        redirectAttributes.addFlashAttribute("successMessage", "Гонщик " + form.getLastName() + " успешно добавлен!");
        return "redirect:/racers";
    }


}
