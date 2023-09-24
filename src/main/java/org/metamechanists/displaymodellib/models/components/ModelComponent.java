package org.metamechanists.displaymodellib.models.components;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Display;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;


/**
 * Represents a single component of a model, composed of one or multiple Displays.
 */
public interface ModelComponent {
    Matrix4f getMatrix();
    void updateMatrix(@NotNull Display display);
    void update(@NotNull Display display);
    Display build(@NotNull final Location origin);
    Display build(@NotNull final Block block);
}
