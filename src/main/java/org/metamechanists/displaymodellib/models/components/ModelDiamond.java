package org.metamechanists.displaymodellib.models.components;

import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.metamechanists.displaymodellib.builders.BlockDisplayBuilder;
import org.metamechanists.displaymodellib.transformations.TransformationMatrixBuilder;
import org.metamechanists.displaymodellib.transformations.TransformationUtils;


/**
 * Represents a diamond (no not that kind of diamond... this is a rotated cube)
 * Offers far less flexibility than ModelCuboid
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@Getter
public class ModelDiamond implements ModelComponent {
    public static final Vector3d ROTATION = new Vector3d(-0.955, 0.785, 0);
    private final BlockDisplayBuilder main;
    private Vector3f location;
    private Vector3f facing;
    private Vector3f size;

    public ModelDiamond() {
        this.main = new BlockDisplayBuilder();
        this.location = new Vector3f();
        this.facing = new Vector3f(0, 0, 1);
        this.size = new Vector3f();
    }
    public ModelDiamond(final @NotNull ModelDiamond other) {
        this.main = new BlockDisplayBuilder(other.main);
        this.location = new Vector3f(other.location);
        this.facing = new Vector3f(other.facing);
        this.size = new Vector3f(other.size);
    }

    /**
     * Sets the center of the diamond
     */
    public ModelDiamond location(@NotNull final Vector3f location) {
        this.location = location;
        return this;
    }
    /**
     * Sets the center of the diamond
     */
    public ModelDiamond location(final float x, final float y, final float z) {
        return location(new Vector3f(x, y, z));
    }

    /**
     * Sets the center of the diamond
     */
    public ModelDiamond location(@NotNull final Vector3d location) {
        this.location = TransformationUtils.dropPrecision(location);
        return this;
    }
    /**
     * Sets the center of the diamond
     */
    public ModelDiamond location(final double x, final double y, final double z) {
        return location(new Vector3d(x, y, z));
    }

    /**
     * Sets the starting orientation of the diamond (default is south AKA positive Z)
     * This is useful eg to align a model with the direction a player is looking
     */
    public ModelDiamond facing(final @NotNull Vector3f facing) {
        this.facing = facing;
        return this;
    }
    /**
     * Sets the starting orientation of the diamond (default is south AKA positive Z)
     * This is useful eg to align a model with the direction a player is looking
     */
    public ModelDiamond facing(final @NotNull Vector3d facing) {
        this.facing = TransformationUtils.dropPrecision(facing);
        return this;
    }
    /**
     * Sets the starting orientation of the diamond (default is south AKA positive Z)
     * This is useful eg to align a model with the direction a player is looking
     */
    public ModelDiamond facing(final @NotNull BlockFace face) {
        return facing(face.getDirection().toVector3f());
    }

    /**
     * Sets the size of the cuboid (ie: the distance from one side to the other) on each axis
     */
    public ModelDiamond size(final float size) {
        // The scale() function takes the scale as the side from one face to the next
        // But we actually want the size to be inputted as the distance from one corner to the opposite corner
        // We can accomplish this with basic pythagoras
        this.size = new Vector3f((float) Math.sqrt(2 * Math.pow(size/2, 2)));
        return this;
    }
    /**
     * Sets the size of the cuboid (ie: the distance from one side to the other) on each axis
     */
    public ModelDiamond size(final double size) {
        return size((float) size);
    }

    /**
     * Overrides material
     */
    public ModelDiamond block(@NotNull final BlockData block) {
        main.blockData(block);
        return this;
    }
    public ModelDiamond material(@NotNull final Material material) {
        main.material(material);
        return this;
    }
    public ModelDiamond brightness(final int blockBrightness) {
        main.brightness(blockBrightness);
        return this;
    }
    public ModelDiamond glow(@NotNull final Color color) {
        main.glow(color);
        return this;
    }

    @Override
    public Matrix4f getMatrix() {
        return new TransformationMatrixBuilder()
                .lookAlong(facing)
                .translate(location)
                .rotate(ROTATION)
                .scale(size)
                .buildForBlockDisplay();
    }
    @Override
    public void updateMatrix(final @NotNull Display display) {
        if (!(display instanceof final BlockDisplay blockDisplay)) {
            throw new IllegalArgumentException("Must provide a BlockDisplay");
        }
        blockDisplay.setTransformationMatrix(getMatrix());
    }
    @Override
    public void update(final @NotNull Display display) {
        if (!(display instanceof final BlockDisplay blockDisplay)) {
            throw new IllegalArgumentException("Must provide a BlockDisplay");
        }
        main.transformation(getMatrix()).update(blockDisplay);
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
