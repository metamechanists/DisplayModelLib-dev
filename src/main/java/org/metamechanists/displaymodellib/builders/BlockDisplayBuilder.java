package org.metamechanists.displaymodellib.builders;

import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.Display.Brightness;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

@SuppressWarnings({"unused", "UnusedReturnValue"})
@Getter
public class BlockDisplayBuilder implements DisplayBuilder {
    private Material material;
    private BlockData blockData;
    private Matrix4f transformation;
    private Color glowColor;
    private Integer brightness;
    private Float viewRange;
    private Integer interpolationDelay;
    private Integer interpolationDuration;

    public BlockDisplayBuilder() {}

    public BlockDisplayBuilder(@NotNull final BlockDisplayBuilder other) {
        this.material = other.material;
        this.blockData = other.blockData;
        this.transformation = other.transformation;
        this.glowColor = other.glowColor;
        this.brightness = other.brightness;
        this.viewRange = other.viewRange;
        this.interpolationDelay = other.interpolationDelay;
        this.interpolationDuration = other.interpolationDuration;
    }

    @Override
    public BlockDisplay build(@NotNull final Location location) {
        final Location finalLocation = location.clone();
        finalLocation.setYaw(0);
        finalLocation.setPitch(0);
        return location.getWorld().spawn(finalLocation, BlockDisplay.class, display -> {
            update(display);
            display.setDisplayWidth(0);
            display.setDisplayHeight(0);
        });
    }
    @Override
    public void update(@NotNull final Display display) {
        if (!(display instanceof final BlockDisplay blockDisplay)) {
            throw new IllegalArgumentException("Must provide a BlockDisplay");
        }
        if (material != null) {
            blockDisplay.setBlock(material.createBlockData());
        }
        if (blockData != null) {
            blockDisplay.setBlock(blockData);
        }
        if (transformation != null) {
            blockDisplay.setTransformationMatrix(transformation);
        }
        if (glowColor != null) {
            blockDisplay.setGlowing(true);
            blockDisplay.setGlowColorOverride(glowColor);
        }
        if (brightness != null) {
            blockDisplay.setBrightness(new Brightness(brightness, 0));
        }
        if (viewRange != null) {
            blockDisplay.setViewRange(viewRange);
        }
        if (interpolationDelay != null) {
            blockDisplay.setInterpolationDelay(interpolationDelay);
        }
        if (interpolationDuration != null) {
            blockDisplay.setInterpolationDelay(interpolationDuration);
        }
    }

    public BlockDisplayBuilder material(final Material material) {
        this.material = material;
        return this;
    }
    public BlockDisplayBuilder blockData(final BlockData blockData) {
        // Overrides material
        this.blockData = blockData;
        return this;
    }
    public BlockDisplayBuilder transformation(final Matrix4f transformation) {
        this.transformation = transformation;
        return this;
    }
    public BlockDisplayBuilder brightness(final int brightness) {
        this.brightness = brightness;
        return this;
    }
    public BlockDisplayBuilder glow(final Color glowColor) {
        this.glowColor = glowColor;
        return this;
    }
    public BlockDisplayBuilder viewRange(final float viewRange) {
        this.viewRange = viewRange;
        return this;
    }
    public BlockDisplayBuilder interpolationDelay(final int interpolationDelay) {
        this.interpolationDelay = interpolationDelay;
        return this;
    }
    public BlockDisplayBuilder interpolationDuration(final int interpolationDuration) {
        this.interpolationDuration = interpolationDuration;
        return this;
    }
}