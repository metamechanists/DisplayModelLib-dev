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
import org.joml.Quaterniond;
import org.joml.Quaternionf;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.metamechanists.displaymodellib.builders.BlockDisplayBuilder;
import org.metamechanists.displaymodellib.transformations.TransformationMatrixBuilder;
import org.metamechanists.displaymodellib.transformations.TransformationUtils;


@SuppressWarnings({"unused", "WeakerAccess"})
@Getter
public class ModelCuboid implements ModelComponent {
    private final BlockDisplayBuilder main;
    private final TransformationMatrixBuilder matrixBuilder;

    public ModelCuboid() {
         this.main = new BlockDisplayBuilder();
         this.matrixBuilder = new TransformationMatrixBuilder();
    }
    public ModelCuboid(final @NotNull ModelCuboid other) {
        this.main = new BlockDisplayBuilder(other.main);
        this.matrixBuilder = new TransformationMatrixBuilder(other.matrixBuilder);
    }

    /**
     * Translates the cuboid by the given vector
     * This depends on the rotation of the component
     * For example, if you rotate by 90 degrees before translating, the translation will also be rotated by 90 degrees
     */
    public ModelCuboid translate(@NotNull final Vector3f translation) {
        matrixBuilder.translate(translation);
        return this;
    }
    /**
     * Translates the cuboid by the given vector
     * This depends on the rotation of the component
     * For example, if you rotate by 90 degrees before translating, the translation will also be rotated by 90 degrees
     */
    public ModelCuboid translate(final float x, final float y, final float z) {
        return translate(new Vector3f(x, y, z));
    }

    /**
     * Translates the cuboid by the given vector
     * This depends on the rotation of the component
     * For example, if you rotate by 90 degrees before translating, the translation will also be rotated by 90 degrees
     */
    public ModelCuboid translate(@NotNull final Vector3d translation) {
        matrixBuilder.translate(TransformationUtils.dropPrecision(translation));
        return this;
    }
    /**
     * Translates the cuboid by the given vector
     * This depends on the rotation of the component
     * For example, if you rotate by 90 degrees before translating, the translation will also be rotated by 90 degrees
     */
    public ModelCuboid translate(final double x, final double y, final double z) {
        return translate(new Vector3d(x, y, z));
    }

    /**
     * Looks along the provided vector
     * This is useful eg to align a component with the direction a player is looking
     */
    public ModelCuboid lookAlong(final @NotNull Vector3f facing) {
        matrixBuilder.lookAlong(facing);
        return this;
    }
    /**
     * Looks along the provided vector
     * This is useful eg to align a component with the direction a player is looking
     */
    public ModelCuboid lookAlong(final @NotNull Vector3d facing) {
        matrixBuilder.lookAlong(new Vector3f((float) facing.x, (float) facing.y, (float) facing.z));
        return this;
    }
    /**
     * Looks along the provided face
     * This is useful eg to align a component with the direction a player is looking
     */
    public ModelCuboid lookAlong(final @NotNull BlockFace face) {
        return lookAlong(face.getDirection().toVector3f());
    }

    /**
     * Scales the component on each axis
     */
    public ModelCuboid scale(@NotNull final Vector3f size) {
        matrixBuilder.scale(size);
        return this;
    }
    /**
     * Scales the component on each axis
     */
    public ModelCuboid scale(final float x, final float y, final float z) {
        return scale(new Vector3f(x, y, z));
    }
    /**
     * Scales the component on all axes
     */
    public ModelCuboid scale(final float size) {
        return scale(new Vector3f(size));
    }

    /**
     * Scales the component on each axis
     */
    public ModelCuboid scale(@NotNull final Vector3d size) {
        matrixBuilder.scale(TransformationUtils.dropPrecision(size));
        return this;
    }
    /**
     * Scales the component on each axis
     */
    public ModelCuboid scale(final double x, final double y, final double z) {
        return scale(new Vector3d(x, y, z));
    }
    /**
     * Scales the component on all axes
     */
    public ModelCuboid scale(final double size) {
        return scale(new Vector3d(size));
    }

    /**
     * Rotates the component by euler angles in radians
     */
    public ModelCuboid rotate(@NotNull final Vector3d rotation) {
        matrixBuilder.rotate(rotation);
        return this;
    }
    /**
     * Rotates the component by a quaternion
     */
    public ModelCuboid rotate(@NotNull final Quaterniond rotation) {
        matrixBuilder.rotate(rotation);
        return this;
    }
    /**
     * Rotates the component by euler angles in radians
     */
    public ModelCuboid rotate(final double x, final double y, final double z) {
        return rotate(new Vector3d(x, y, z));
    }
    /**
     * Rotates the component by euler angles in radians around the X axis
     */
    public ModelCuboid rotateX(final double rotation) {
        return rotate(new Vector3d(rotation, 0, 0));
    }
    /**
     * Rotates the component by euler angles in radians around the Y axis
     */
    public ModelCuboid rotateY(final double rotation) {
        return rotate(new Vector3d(0, rotation, 0));
    }
    /**
     * Rotates the component by euler angles in radians around the Z axis
     */
    public ModelCuboid rotateZ(final double rotation) {
        return rotate(new Vector3d(0, 0, rotation));
    }

    /**
     * Rotates the component by euler angles in radians
     */
    public ModelCuboid rotate(@NotNull final Vector3f rotation) {
        matrixBuilder.rotate(new Vector3d(rotation));
        return this;
    }
    /**
     * Rotates the component by a quaternion
     */
    public ModelCuboid rotate(@NotNull final Quaternionf quaternion) {
        matrixBuilder.rotate(new Quaterniond(quaternion));
        return this;
    }
    /**
     * Rotates the component by euler angles in radians
     */
    public ModelCuboid rotate(final float x, final float y, final float z) {
        return rotate(new Vector3f(x, y, z));
    }
    /**
     * Rotates the component by euler angles in radians around the X axis
     */
    public ModelCuboid rotateX(final float rotation) {
        return rotate(new Vector3f(rotation, 0, 0));
    }
    /**
     * Rotates the component by euler angles in radians around the Y axis
     */
    public ModelCuboid rotateY(final float rotation) {
        return rotate(new Vector3f(0, rotation, 0));
    }
    /**
     * Rotates the component by euler angles in radians around the Z axis
     */
    public ModelCuboid rotateZ(final float rotation) {
        return rotate(new Vector3f(0, 0, rotation));
    }

    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelCuboid undoRotate(@NotNull final Vector3d rotation) {
        matrixBuilder.rotateBackwards(rotation);
        return this;
    }
    /**
     * Undoes a previous rotation
     */
    public ModelCuboid undoRotate(@NotNull final Quaterniond rotation) {
        matrixBuilder.rotateBackwards(rotation);
        return this;
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelCuboid undoRotate(final double x, final double y, final double z) {
        return undoRotate(new Vector3d(x, y, z));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelCuboid undoRotateX(final double rotation) {
        return undoRotate(new Vector3d(rotation, 0, 0));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelCuboid undoRotateY(final double rotation) {
        return undoRotate(new Vector3d(0, rotation, 0));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelCuboid undoRotateZ(final double rotation) {
        return undoRotate(new Vector3d(0, 0, rotation));
    }

    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelCuboid undoRotate(final float x, final float y, final float z) {
        return undoRotate(new Vector3d(x, y, z));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelCuboid undoRotate(@NotNull final Vector3f rotation) {
        matrixBuilder.rotateBackwards(new Vector3d(rotation));
        return this;
    }
    /**
     * Undoes a previous rotation
     */
    public ModelCuboid undoRotate(@NotNull final Quaternionf rotation) {
        matrixBuilder.rotateBackwards(new Quaterniond(rotation));
        return this;
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelCuboid undoRotateX(final float rotation) {
        return undoRotate(new Vector3f(rotation, 0, 0));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelCuboid undoRotateY(final float rotation) {
        return undoRotate(new Vector3f(0, rotation, 0));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelCuboid undoRotateZ(final float rotation) {
        return undoRotate(new Vector3f(0, 0, rotation));
    }

    public ModelCuboid material(@NotNull final Material material) {
        main.material(material);
        return this;
    }
    /**
     * Overrides material
     */
    public ModelCuboid block(@NotNull final BlockData block) {
        main.blockData(block);
        return this;
    }
    public ModelCuboid brightness(final int blockBrightness) {
        main.brightness(blockBrightness);
        return this;
    }
    public ModelCuboid glow(@NotNull final Color color) {
        main.glow(color);
        return this;
    }
    public ModelCuboid viewRange(final float viewRange) {
        main.viewRange(viewRange);
        return this;
    }
    public ModelCuboid interpolationDelay(@NotNull final Integer interpolationDelay) {
        main.interpolationDelay(interpolationDelay);
        return this;
    }
    public ModelCuboid interpolationDuration(@NotNull final Integer interpolationDuration) {
        main.interpolationDuration(interpolationDuration);
        return this;
    }

    @Override
    public Matrix4f getMatrix() {
        return matrixBuilder.buildForBlockDisplay();
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
