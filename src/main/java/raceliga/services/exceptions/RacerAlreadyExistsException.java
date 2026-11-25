package raceliga.services.exceptions;

public class RacerAlreadyExistsException extends RuntimeException {
    public RacerAlreadyExistsException(Integer message) {
        super("Гонщик с номером " + message + " уже существует");
    }
}
