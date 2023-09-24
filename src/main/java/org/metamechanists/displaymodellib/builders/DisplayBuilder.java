package org.metamechanists.displaymodellib.builders;

import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.jetbrains.annotations.NotNull;


public interface DisplayBuilder {
    @SuppressWarnings("unused")
    Display build(@NotNull final Location location);
    void update(@NotNull final Display display);
}
