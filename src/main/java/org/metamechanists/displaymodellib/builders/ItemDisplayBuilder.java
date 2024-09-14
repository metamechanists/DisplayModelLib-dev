package org.metamechanists.displaymodellib.builders;

import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Display;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.Display.Brightness;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

@Getter
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class ItemDisplayBuilder implements DisplayBuilder {
    private ItemStack itemStack;
    private Matrix4f transformation;
    private Integer brightness;
    private Color glowColor;
    private Billboard billboard;
    private Float viewRange;
    private Integer interpolationDelay;
    private Integer interpolationDuration;

    public ItemDisplayBuilder() {}

    public ItemDisplayBuilder(@NotNull final ItemDisplayBuilder other) {
        this.itemStack = other.itemStack;
        this.transformation = other.transformation;
        this.brightness = other.brightness;
        this.glowColor = other.glowColor;
        this.billboard = other.billboard;
        this.viewRange = other.viewRange;
        this.interpolationDelay = other.interpolationDelay;
        this.interpolationDuration = other.interpolationDuration;
    }

    @Override
    public ItemDisplay build(@NotNull final Location location) {
        final Location finalLocation = location.clone();
        finalLocation.setYaw(0);
        finalLocation.setPitch(0);

        return finalLocation.getWorld().spawn(finalLocation, ItemDisplay.class, display -> {
            update(display);
            display.setDisplayWidth(0);
            display.setDisplayHeight(0);
        });
    }
    @Override
    public void update(@NotNull final Display display) {
        if (!(display instanceof final ItemDisplay itemDisplay)) {
            throw new IllegalArgumentException("Must provide an ItemDisplay");
        }
        if (itemStack != null) {
            itemDisplay.setItemStack(itemStack);
        }
        if (transformation != null) {
            itemDisplay.setTransformationMatrix(transformation);
        }
        if (glowColor != null) {
            itemDisplay.setGlowing(true);
            itemDisplay.setGlowColorOverride(glowColor);
        }
        if (brightness != null) {
            itemDisplay.setBrightness(new Brightness(brightness, 0));
        }
        if (billboard != null) {
            itemDisplay.setBillboard(billboard);
        }
        if (viewRange != null) {
            itemDisplay.setViewRange(viewRange);
        }
        if (interpolationDelay != null) {
            itemDisplay.setInterpolationDelay(interpolationDelay);
        }
        if (interpolationDuration != null) {
            itemDisplay.setInterpolationDelay(interpolationDuration);
        }
    }

    public ItemDisplayBuilder material(final Material material) {
        this.itemStack = new ItemStack(material);
        return this;
    }
    public ItemDisplayBuilder itemStack(final ItemStack itemStack) {
        // Overrides material
        this.itemStack = itemStack;
        return this;
    }
    public ItemDisplayBuilder transformation(final Matrix4f transformation) {
        this.transformation = transformation;
        return this;
    }
    public ItemDisplayBuilder brightness(final int brightness) {
        this.brightness = brightness;
        return this;
    }
    public ItemDisplayBuilder glow(final Color glowColor) {
        this.glowColor = glowColor;
        return this;
    }
    public ItemDisplayBuilder billboard(final Billboard billboard) {
        this.billboard = billboard;
        return this;
    }
    public ItemDisplayBuilder viewRange(final float viewRange) {
        this.viewRange = viewRange;
        return this;
    }
    public ItemDisplayBuilder interpolationDelay(final int interpolationDelay) {
        this.interpolationDelay = interpolationDelay;
        return this;
    }
    public ItemDisplayBuilder interpolationDuration(final int interpolationDuration) {
        this.interpolationDuration = interpolationDuration;
        return this;
    }
}
