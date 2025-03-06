package com.skyhawk.nbastat.controller.DTO;

import jakarta.annotation.Nonnull;

import java.time.LocalDate;

public record StatisticDTO(@Nonnull Long playerId,
                           @Nonnull LocalDate gameDate,
                           @Nonnull Integer points,
                           @Nonnull Integer rebounds,
                           @Nonnull Integer assists,
                           @Nonnull Integer steals,
                           @Nonnull Integer blocks,
                           @Nonnull Integer fouls,
                           @Nonnull Integer turnovers,
                           @Nonnull Float minutesPlayed) {

}
