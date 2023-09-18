package org.metamechanists.displaymodellib.models.components;

import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.metamechanists.displaymodellib.builders.BlockDisplayBuilder;
import org.metamechanists.displaymodellib.transformations.TransformationMatrixBuilder;

@SuppressWarnings("unused")
@Getter
public class ModelAdvancedCuboid implements ModelComponent {
    private final BlockDisplayBuilder main = new BlockDisplayBuilder();
    private final TransformationMatrixBuilder matrixBuilder = new TransformationMatrixBuilder();

    public ModelAdvancedCuboid translate(@NotNull final Vector3f translation) {
        matrixBuilder.translate(translation);
        return this;
    }
    public ModelAdvancedCuboid translate(final float x, final float y, final float z) {
        return translate(new Vector3f(x, y, z));
    }

    public ModelAdvancedCuboid facing(final @NotNull Vector3f facing) {
        matrixBuilder.lookAlong(facing);
        return this;
    }
    public ModelAdvancedCuboid facing(final @NotNull BlockFace face) {
        return facing(face.getDirection().toVector3f());
    }

    public ModelAdvancedCuboid scale(@NotNull final Vector3f size) {
        matrixBuilder.scale(size);
        return this;
    }
    public ModelAdvancedCuboid scale(final float x, final float y, final float z) {
        return scale(new Vector3f(x, y, z));
    }
    public ModelAdvancedCuboid scale(final float size) {
        return scale(new Vector3f(size));
    }

    public ModelAdvancedCuboid rotate(@NotNull final Vector3d rotation) {
        matrixBuilder.rotate(rotation);
        return this;
    }
    public ModelAdvancedCuboid rotate(final double x, final double y, final double z) {
        return rotate(new Vector3d(x, y, z));
    }
    public ModelAdvancedCuboid rotate(final double rotationY) {
        return rotate(new Vector3d(0, rotationY, 0));
    }

    public ModelAdvancedCuboid material(@NotNull final Material material) {
        main.material(material);
        return this;
    }
    /**
     * Overrides material
     */
    public ModelAdvancedCuboid block(@NotNull final BlockData block) {
        main.blockData(block);
        return this;
    }
    public ModelAdvancedCuboid brightness(final int blockBrightness) {
        main.brightness(blockBrightness);
        return this;
    }
    public ModelAdvancedCuboid glow(@NotNull final Color color) {
        main.glow(color);
        return this;
    }

    public Matrix4f getMatrix() {
        return matrixBuilder.buildForBlockDisplay();
    }
    @Override
    public BlockDisplay build(@NotNull final Location origin) {
        return main.transformation(getMatrix()).build(origin);
    }
    @Override
    public BlockDisplay build(@NotNull final Block block) {
        return build(block.getLocation());
    }
}
