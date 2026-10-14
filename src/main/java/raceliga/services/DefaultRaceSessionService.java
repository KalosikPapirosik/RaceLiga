package raceliga.services;

import org.springframework.stereotype.Service;
import raceliga.catalog.RaceSessionCatalog;
import raceliga.model.RaceSessionSummary;
import raceliga.services.exceptions.RaceSessionNotFoundException;

import java.util.List;

@Service
public class DefaultRaceSessionService implements RaceSessionService {

    private final RaceSessionCatalog raceSessionCatalog;

    public DefaultRaceSessionService(RaceSessionCatalog raceSessionCatalog) {
        this.raceSessionCatalog = raceSessionCatalog;
    }

    @Override
    public List<RaceSessionSummary> allRaces() {
        return raceSessionCatalog.findAll();
    }

    public RaceSessionSummary raceDetails(String raceName) {
        return raceSessionCatalog.findAll().stream()
                .filter(raceSessionSummary -> raceSessionSummary.name().equals(raceName))
                .findFirst().orElseThrow(() -> new RaceSessionNotFoundException(raceName));
    }
}
