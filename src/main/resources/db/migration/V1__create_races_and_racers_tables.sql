
CREATE TABLE races (
    id UUID PRIMARY KEY,
    prize NUMERIC(19, 2) NOT NULL,
    description VARCHAR(2000) NOT NULL,
    name VARCHAR(100) NOT NULL,
    track VARCHAR(100) NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE racers (
    id UUID PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    racing_number INT NOT NULL UNIQUE,
    racing_class VARCHAR(40) NOT NULL,

    race_id UUID,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_racer_race FOREIGN KEY (race_id) REFERENCES races(id)
);