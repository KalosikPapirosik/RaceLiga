package raceliga.catalog;

import org.springframework.stereotype.Repository;
import raceliga.model.RaceSessionSummary;

import java.util.List;

@Repository
public class InMemoryRaceSessionCatalog implements RaceSessionCatalog {

    private final List<RaceSessionSummary> races = List.of(
            new RaceSessionSummary(1, "Moscow GT cup", "Moscow Raceway"),
            new RaceSessionSummary(2, "Moscow togue cup", "Closed raceway"),
            new RaceSessionSummary(3, "Moscow Formula 4 cup", "Moscow Raceway"),
            new RaceSessionSummary(4, "Moscow Drift series", "Moscow Raceway"),
            new RaceSessionSummary(5, "Moscow Legends cup", "Moscow ADM")
    );

    @Override
    public List<RaceSessionSummary> findAll() {
        return races;
    }

    public RaceSessionSummary findById(int id){
        return races.get(id);
    }
}
