-- Create the database
-- CREATE DATABASE nba_stat;

-- Connect to the new database
\c nba_stat

-- Create the schema
CREATE SCHEMA IF NOT EXISTS nba_stat;

-- Set the search path to the custom schema
SET search_path TO nba_stat;

CREATE TABLE IF NOT EXISTS teams (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS players (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    team_id INT,
    CONSTRAINT fk_team FOREIGN KEY (team_id) REFERENCES teams(id)
);

CREATE TABLE IF NOT EXISTS game_statistics (
    id SERIAL PRIMARY KEY,
    player_id INT NOT NULL,
    game_date DATE NOT NULL,
    points INT NOT NULL,
    rebounds INT NOT NULL,
    assists INT NOT NULL,
    steals INT NOT NULL,
    blocks INT NOT NULL,
    fouls INT NOT NULL,
    turnovers INT NOT NULL,
    minutes_played FLOAT NOT NULL,
    CONSTRAINT fk_player FOREIGN KEY (player_id) REFERENCES players(id)
);
