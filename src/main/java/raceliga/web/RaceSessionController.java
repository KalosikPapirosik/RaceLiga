package raceliga.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import raceliga.dto.ShowDetailedRaceSessionDto;
import raceliga.model.RaceSessionSummary;
import raceliga.dto.AddRaceSessionDTO;
import raceliga.services.RaceSessionService;

@Controller
@RequestMapping("/races")
public class RaceSessionController {

    private final RaceSessionService raceSessionService;

    public RaceSessionController(RaceSessionService raceSessionService) {
        this.raceSessionService = raceSessionService;
    }



    @GetMapping
    public String allRaces(Model model) {
        model.addAttribute("racesInfos", raceSessionService.allRaces());
        return "races-all";
    }

//    @GetMapping(value = "/races/{id}", produces = "text/plain;charset=UTF-8")
//    @ResponseBody
//    public String raceById(@PathVariable("id") int id) {
//        return formatRaces(raceSessionService.raceById(id));
//    }
    @GetMapping("/{raceName}")
    public String raceDetails(
        @PathVariable String raceName,
        Model model
    ) {
    model.addAttribute(
            "raceDetails",
            raceSessionService.raceDetails(raceName)
    );
    return "race-details";
    }

    @ModelAttribute("raceSessionModel")
    public AddRaceSessionDTO RaceSessionForm(){
        return new AddRaceSessionDTO();
    }

    @GetMapping("/add")
    public String addRaceSessionForm(Model model){
        model.addAttribute("editing", false);
        model.addAttribute("formHeading", "Новый заезд");
        model.addAttribute("submitLabel", "Создать заезд");
        return "race-add";
    }

    @PostMapping
    public String addRaceSession(
            @Valid @ModelAttribute("raceSessionModel") AddRaceSessionDTO raceSessionModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        if(bindingResult.hasErrors()){
            model.addAttribute("editing", false);
            model.addAttribute("formHeading", "Новый заезд");
            model.addAttribute("submitLabel", "Создать заезд");
            return "race-add";
        }

        try {
            raceSessionService.addRaceSession(raceSessionModel);
        } catch (IllegalArgumentException exception){
            bindingResult.rejectValue("name", "duplicate", exception.getMessage());
            model.addAttribute("editing", false);
            model.addAttribute("formHeading", "Новый заезд");
            model.addAttribute("submitLabel", "Создать заезд");
            return "race-add";
        }
        redirectAttributes.addFlashAttribute("SuccessMessage", "Заезд " + raceSessionModel.getName() + " добавлен");
        return "redirect:/races";
    }

    @GetMapping("/{raceName}/edit")
    public String editRaceSessionForm(
            @PathVariable String raceName,
            Model model
    ){
        RaceSessionSummary race = raceSessionService.raceDetails(raceName);

        AddRaceSessionDTO form = new AddRaceSessionDTO();
        form.setName(race.name());
        form.setDescription(race.description());
        form.setTrack(race.track());
        form.setPrize(race.prize());

        model.addAttribute("raceSessionModel", form);
        model.addAttribute("editing", true);
        model.addAttribute("originalName", raceName);
        model.addAttribute("formHeading", "Изменение заезда");
        model.addAttribute("submitLabel", "Сохранить изменения");
        return "race-add";
    }


    @PostMapping("/{raceName}")
    public String editRaceSession(
            @PathVariable String raceName,
            @Valid @ModelAttribute("raceSessionModel") AddRaceSessionDTO raceSessionModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        if (bindingResult.hasErrors()){
            model.addAttribute("editing", true);
            model.addAttribute("originalName", raceName);
            model.addAttribute("formHeading", "Изменение заезда");
            model.addAttribute("submitLabel", "Сохранить изменения");
            return "race-add";
        }

        raceSessionService.updateRaceSession(raceName, raceSessionModel);
        redirectAttributes.addFlashAttribute("successMessage", "Заезд успешно изменен");
        redirectAttributes.addAttribute("raceName", raceSessionModel.getName());
        return "redirect:/races/{raceName}";

    }

    private String formatRaces(RaceSessionSummary race) {
        return race.name() + " | " + race.name() + " | " + race.track();
    }
}
