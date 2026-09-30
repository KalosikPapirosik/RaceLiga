package raceliga.services;

import raceliga.model.RaceSessionSummary;

import java.util.List;

public interface RaceSessionService {
    List<RaceSessionSummary> allRaces();
    RaceSessionSummary raceById(int id);
}
