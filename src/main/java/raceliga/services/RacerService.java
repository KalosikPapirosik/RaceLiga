package raceliga.services;

import raceliga.dto.AddRaceSessionDTO;
import raceliga.dto.AddRacerDto;
import raceliga.dto.RacerSummary;
import raceliga.model.RaceSessionSummary;
import raceliga.models.entities.Racer;

import java.util.List;
import java.util.UUID;

public interface RacerService {
    List<RacerSummary> allRacers();

    void addRacer(AddRacerDto form);
}
