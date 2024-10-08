package org.metamechanists.displaymodellib.builders;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.Display.Brightness;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;


@Getter
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class TextDisplayBuilder implements DisplayBuilder {
    private Component text;
    private Matrix4f transformation;
    private Integer brightness;
    private Color glowColor;
    private Float viewRange;
    private Billboard billboard;
    private TextAlignment alignment;
    private Color backgroundColor;
    private Integer interpolationDelay;
    private Integer interpolationDuration;

    public TextDisplayBuilder() {}

    public TextDisplayBuilder(@NotNull final TextDisplayBuilder other) {
        this.text = other.text;
        this.transformation = other.transformation;
        this.brightness = other.brightness;
        this.glowColor = other.glowColor;
        this.viewRange = other.viewRange;
        this.billboard = other.billboard;
        this.alignment = other.alignment;
        this.backgroundColor = other.backgroundColor;
        this.interpolationDelay = other.interpolationDelay;
        this.interpolationDuration = other.interpolationDuration;
    }

    @Override
    public TextDisplay build(@NotNull final Location location) {
        final Location finalLocation = location.clone();
        finalLocation.setYaw(0);
        finalLocation.setPitch(0);

        return finalLocation.getWorld().spawn(finalLocation, TextDisplay.class, display -> {
            update(display);
            display.setDisplayWidth(0);
            display.setDisplayHeight(0);
        });
    }
    @Override
    public void update(@NotNull final Display display) {
        if (!(display instanceof final TextDisplay textDisplay)) {
            throw new IllegalArgumentException("Must provide a TextDisplay");
        }
        if (text != null) {
            textDisplay.text(text);
        }
        if (transformation != null) {
            textDisplay.setTransformationMatrix(transformation);
        }
        if (brightness != null) {
            textDisplay.setBrightness(new Brightness(brightness, 0));
        }
        if (glowColor != null) {
            textDisplay.setGlowing(true);
            textDisplay.setGlowColorOverride(glowColor);
        }
        if (viewRange != null) {
            textDisplay.setViewRange(viewRange);
        }
        if (billboard != null) {
            textDisplay.setBillboard(billboard);
        }
        if (alignment != null) {
            textDisplay.setAlignment(alignment);
        }
        if (backgroundColor != null) {
            textDisplay.setBackgroundColor(backgroundColor);
        }
        if (interpolationDelay != null) {
            textDisplay.setInterpolationDelay(interpolationDelay);
        }
        if (interpolationDuration != null) {
            textDisplay.setInterpolationDelay(interpolationDuration);
        }
    }

    public TextDisplayBuilder text(final String text) {
        this.text = Component.text(text);
        return this;
    }
    public TextDisplayBuilder text(final Component text) {
        this.text = text;
        return this;
    }
    public TextDisplayBuilder transformation(final Matrix4f transformation) {
        this.transformation = transformation;
        return this;
    }
    public TextDisplayBuilder brightness(final int brightness) {
        this.brightness = brightness;
        return this;
    }
    public TextDisplayBuilder glow(final Color glowColor) {
        this.glowColor = glowColor;
        return this;
    }
    public TextDisplayBuilder viewRange(final float viewRange) {
        this.viewRange = viewRange;
        return this;
    }
    public TextDisplayBuilder billboard(final Billboard billboard) {
        this.billboard = billboard;
        return this;
    }
    public TextDisplayBuilder alignment(final TextAlignment alignment) {
        this.alignment = alignment;
        return this;
    }
    public TextDisplayBuilder backgroundColor(final Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }
    public TextDisplayBuilder interpolationDelay(final int interpolationDelay) {
        this.interpolationDelay = interpolationDelay;
        return this;
    }
    public TextDisplayBuilder interpolationDuration(final int interpolationDuration) {
        this.interpolationDuration = interpolationDuration;
        return this;
    }
}