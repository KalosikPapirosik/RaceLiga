package raceliga.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "races")
public class RaceSession extends BaseEntity{

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal prize;

    @Column(length = 2000, nullable = false)
    private String description;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String track;

    @OneToMany(
            mappedBy = "race",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    private List<Racer> racers = new ArrayList<>();

    public RaceSession(){
    }

    public RaceSession(String track, String name, String description, BigDecimal prize) {
        this.track = track;
        this.name = name;
        this.description = description;
        this.prize = prize;
    }

    public BigDecimal getPrize() {
        return prize;
    }

    public void setPrize(BigDecimal prize) {
        this.prize = prize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public List<Racer> getRacers() {
        return racers;
    }

    public void setRacers(ArrayList<Racer> racers) {
        this.racers = racers;
    }

    public void addRacer(Racer racer) {
        racers.add(racer);
        racer.setRace(this);
    }
}
