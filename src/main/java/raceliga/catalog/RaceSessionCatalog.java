package raceliga.catalog;

import raceliga.model.RaceSessionSummary;

import java.util.List;

public interface RaceSessionCatalog {

    List<RaceSessionSummary> findAll();
    RaceSessionSummary findById(int id);
}
