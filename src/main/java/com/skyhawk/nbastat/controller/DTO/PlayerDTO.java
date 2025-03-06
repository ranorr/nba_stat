package com.skyhawk.nbastat.controller.DTO;

import jakarta.annotation.Nonnull;

public record PlayerDTO(@Nonnull String name, @Nonnull Long teamId) {
}
