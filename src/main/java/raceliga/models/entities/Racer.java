package raceliga.models.entities;

import jakarta.persistence.*;
import raceliga.models.enums.RacingClass;

import java.time.LocalDate;

@Entity
@Table(name = "racers")
public class Racer extends BaseEntity {

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "nickname", length = 50, nullable = false)
    private String nickname;

    @Column(name = "racing_number", nullable = false, unique = true)
    private Integer racingNumber;

    @Column(name = "racing_class", length = 40, nullable = false)
    @Enumerated(EnumType.STRING)
    private RacingClass racingClass;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "race_id",
            nullable = true,
            foreignKey = @ForeignKey(name = "fk_racer_race")
    )
    private RaceSession race;

    public Racer() {
    }

    public Racer(String firstName, String lastName, LocalDate birthDate, String nickname, Integer racingNumber, RacingClass racingClass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nickname = nickname;
        this.racingNumber = racingNumber;
        this.racingClass = racingClass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getRacingNumber() {
        return racingNumber;
    }

    public void setRacingNumber(Integer racingNumber) {
        this.racingNumber = racingNumber;
    }

    public RacingClass getRacingClass() {
        return racingClass;
    }

    public void setRacingClass(RacingClass racingClass) {
        this.racingClass = racingClass;
    }

    public RaceSession getRace() {
        return race;
    }

    public void setRace(RaceSession race) {
        this.race = race;
    }
}
