package raceliga.models.enums;

public enum RacingClass {
    GT("GT"),
    DRIFT("DRIFT");

    private final String title;

    RacingClass(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
