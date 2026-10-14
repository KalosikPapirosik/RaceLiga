package raceliga.catalog;

import org.springframework.stereotype.Repository;
import raceliga.model.RaceSessionSummary;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class InMemoryRaceSessionCatalog implements RaceSessionCatalog {

    private final List<RaceSessionSummary> races = List.of(
            new RaceSessionSummary(1, "Moscow GT cup", "Moscow Raceway","Финал сезона GT", new BigDecimal(150000)),
            new RaceSessionSummary(2, "Moscow togue cup", "Closed raceway","Чемпионат между гонщиками-любителями на своих автомобилях", new BigDecimal(20000)),
            new RaceSessionSummary(3, "Moscow Formula 4 cup", "Moscow Raceway","Главный гоночный чемпионат России", new BigDecimal(1000000)),
            new RaceSessionSummary(4, "Moscow Drift series", "Moscow Raceway","Легендарный заключительный этап дрифт серии", new BigDecimal(30000)),
            new RaceSessionSummary(5, "Moscow Legends cup", "Moscow ADM","Гонки на машинках в стиле Маквина", new BigDecimal(50000))
    );

    @Override
    public List<RaceSessionSummary> findAll() {
        return races;
    }

    public RaceSessionSummary findById(int id){
        return races.get(id);
    }
}
