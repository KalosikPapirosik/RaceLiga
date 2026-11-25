package raceliga.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raceliga.dto.AddRacerDto;
import raceliga.dto.RacerSummary;
import raceliga.models.entities.RaceSession;
import raceliga.models.entities.Racer;
import raceliga.repositories.RaceSessionRepository;
import raceliga.repositories.RacerRepository;
import raceliga.services.exceptions.RaceSessionNotFoundException;
import raceliga.services.exceptions.RacerAlreadyExistsException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DefaultRacerServiceImpl implements RacerService{
    private final RacerRepository racerRepository;
    private final RaceSessionRepository raceSessionRepository;

    public DefaultRacerServiceImpl(RacerRepository racerRepository, RaceSessionRepository raceSessionRepository) {
        this.racerRepository = racerRepository;
        this.raceSessionRepository = raceSessionRepository;
    }

    @Override
    public List<RacerSummary> allRacers() {
        return racerRepository.findAllByOrderByLastNameAscFirstNameAsc().stream().map(this::toSummary).toList();
    }

    @Override
    @Transactional
    public void addRacer(AddRacerDto form) {
        String raceSessionName = form.getRaceName();
        if (racerRepository.existsByRacingNumber(form.getRacingNumber())){
            throw new RacerAlreadyExistsException(form.getRacingNumber());
        } else if (!raceSessionRepository.existsByNameIgnoreCase(form.getRaceName().trim())) {
            throw new RaceSessionNotFoundException(form.getRaceName().trim());
        }
        RaceSession raceSession =raceSessionRepository.findByName(raceSessionName).orElseThrow(() -> new RaceSessionNotFoundException(raceSessionName));
        Racer racer = new Racer();
        racer.setBirthDate(form.getBirthDate());
        racer.setFirstName(form.getFirstName());
        racer.setLastName(form.getLastName());
        racer.setNickname(form.getNickname());
        racer.setRacingClass(form.getRacingClass());
        racer.setRacingNumber(form.getRacingNumber());
        racer.setRace(raceSession);
        racerRepository.saveAndFlush(racer);
    }

    private RacerSummary toSummary(Racer racer) {
        return new RacerSummary(
                racer.getId(),
                racer.getFirstName(),
                racer.getLastName(),
                racer.getNickname(),
                racer.getRacingNumber(),
                racer.getRacingClass(),
                racer.getRace() != null ? racer.getRace().getName() : "Без заезда"
        );
    }
}
