package org.metamechanists.displaymodellib.models.components;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.TextDisplay;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.metamechanists.displaymodellib.builders.TextDisplayBuilder;
import org.metamechanists.displaymodellib.transformations.TransformationMatrixBuilder;


@SuppressWarnings("unused")
@Getter
public class ModelAdvancedText implements ModelComponent {
    private final TextDisplayBuilder main = new TextDisplayBuilder();
    private final TransformationMatrixBuilder matrixBuilder = new TransformationMatrixBuilder();

    public ModelAdvancedText translate(@NotNull final Vector3f translation) {
        matrixBuilder.translate(translation);
        return this;
    }
    public ModelAdvancedText translate(final float x, final float y, final float z) {
        return translate(new Vector3f(x, y, z));
    }

    public ModelAdvancedText facing(final @NotNull Vector3f facing) {
        matrixBuilder.lookAlong(facing);
        return this;
    }
    public ModelAdvancedText facing(final @NotNull BlockFace face) {
        return facing(face.getDirection().toVector3f());
    }

    public ModelAdvancedText scale(@NotNull final Vector3f size) {
        matrixBuilder.scale(size);
        return this;
    }
    public ModelAdvancedText scale(final float x, final float y, final float z) {
        return scale(new Vector3f(x, y, z));
    }
    public ModelAdvancedText scale(final float size) {
        return scale(new Vector3f(size));
    }

    public ModelAdvancedText rotate(@NotNull final Vector3d rotation) {
        matrixBuilder.rotate(rotation);
        return this;
    }
    public ModelAdvancedText rotate(final double x, final double y, final double z) {
        return rotate(new Vector3d(x, y, z));
    }
    public ModelAdvancedText rotate(final double rotationY) {
        return rotate(new Vector3d(0, rotationY, 0));
    }

    public ModelAdvancedText rotateBackwards(@NotNull final Vector3d rotation) {
        matrixBuilder.rotateBackwards(rotation);
        return this;
    }
    public ModelAdvancedText rotateBackwards(final double x, final double y, final double z) {
        return rotateBackwards(new Vector3d(x, y, z));
    }
    public ModelAdvancedText rotateBackwards(final double rotationY) {
        return rotateBackwards(new Vector3d(0, rotationY, 0));
    }

    /**
     * @param color The background of the text (default is transparent)
     */
    public ModelAdvancedText background(@NotNull final Color color) {
        main.backgroundColor(color);
        return this;
    }
    public ModelAdvancedText text(@NotNull final String text) {
        main.text(text);
        return this;
    }
    public ModelAdvancedText text(@NotNull final Component text) {
        main.text(text);
        return this;
    }
    public ModelAdvancedText brightness(final int blockBrightness) {
        main.brightness(blockBrightness);
        return this;
    }
    public ModelAdvancedText glow(@NotNull final Color color) {
        main.glow(color);
        return this;
    }

    @Override
    public Matrix4f getMatrix() {
        return matrixBuilder.buildForBlockDisplay();
    }
    @Override
    public TextDisplay build(@NotNull final Location origin) {
        return main.transformation(getMatrix()).build(origin);
    }
    @Override
    public TextDisplay build(@NotNull final Block block) {
        return build(block.getLocation());
    }
}
