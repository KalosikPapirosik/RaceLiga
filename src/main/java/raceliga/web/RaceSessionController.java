package raceliga.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import raceliga.model.RaceSessionSummary;
import raceliga.services.RaceSessionService;

import java.util.stream.Collectors;

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

    private String formatRaces(RaceSessionSummary race) {
        return race.name() + " | " + race.name() + " | " + race.track();
    }
}
