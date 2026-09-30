package raceliga.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import raceliga.model.RaceSessionSummary;
import raceliga.services.RaceSessionService;

import java.util.stream.Collectors;

@Controller
public class RaceSessionController {

    private final RaceSessionService raceSessionService;

    public RaceSessionController(RaceSessionService raceSessionService) {
        this.raceSessionService = raceSessionService;
    }

    @GetMapping(value = "/", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String home() {
        return "Система управления чемпионатам гонок. \nОткройте /races";
    }

    @GetMapping(value = "/races", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String races() {
        return raceSessionService.allRaces().stream()
                .map(this::formatRaces)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @GetMapping(value = "/races/{id}", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String raceById(@PathVariable("id") int id) {
        return formatRaces(raceSessionService.raceById(id));
    }

    private String formatRaces(RaceSessionSummary race) {
        return race.name() + " | " + race.name() + " | " + race.track();
    }
}
