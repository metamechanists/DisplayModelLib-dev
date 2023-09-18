package org.metamechanists.displaymodellib.builders;

import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.Display.Brightness;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;


@SuppressWarnings({"unused", "UnusedReturnValue"})
public class TextDisplayBuilder implements DisplayBuilder {
    private String textString;
    private Component textComponent;
    private Matrix4f transformation;
    private Integer brightness;
    private Color glowColor;
    private Float viewRange;
    private Billboard billboard;
    private TextAlignment alignment;
    private Color backgroundColor;

    @Override
    public TextDisplay build(@NotNull final Location location) {
        return location.getWorld().spawn(location, TextDisplay.class, display -> {
            if (textString != null) {
                display.setText(textString);
            }
            if (textComponent != null) {
                display.text(textComponent);
            }
            if (transformation != null) {
                display.setTransformationMatrix(transformation);
            }
            if (brightness != null) {
                display.setBrightness(new Brightness(brightness, 0));
            }
            if (glowColor != null) {
                display.setGlowing(true);
                display.setGlowColorOverride(glowColor);
            }
            if (viewRange != null) {
                display.setViewRange(viewRange);
            }
            if (billboard != null) {
                display.setBillboard(billboard);
            }
            if (alignment != null) {
                display.setAlignment(alignment);
            }
            if (backgroundColor != null) {
                display.setBackgroundColor(backgroundColor);
            }
            display.setDisplayWidth(0);
            display.setDisplayHeight(0);
        });
    }

    public TextDisplayBuilder text(final String textString) {
        this.textString = textString;
        return this;
    }
    public TextDisplayBuilder text(final Component textComponent) {
        this.textComponent = textComponent;
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
}