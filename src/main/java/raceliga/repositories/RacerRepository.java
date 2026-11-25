package raceliga.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import raceliga.models.entities.Racer;

import java.util.List;
import java.util.UUID;

public interface RacerRepository extends JpaRepository<Racer, UUID> {
    @EntityGraph(attributePaths = "race")
    List<Racer> findAllByOrderByLastNameAscFirstNameAsc();

    boolean existsByRacingNumber(Integer racingNumber);
}
