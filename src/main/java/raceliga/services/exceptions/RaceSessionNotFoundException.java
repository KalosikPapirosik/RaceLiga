package raceliga.services.exceptions;

public class RaceSessionNotFoundException extends RuntimeException {

    public RaceSessionNotFoundException(String raceName) {
        super("Гонка с названием '" + raceName + "' не найдена");
    }
}
