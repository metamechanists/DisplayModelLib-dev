package org.metamechanists.displaymodellib.models.components;

import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Display;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.metamechanists.displaymodellib.Utils;
import org.metamechanists.displaymodellib.builders.ItemDisplayBuilder;
import org.metamechanists.displaymodellib.transformations.TransformationMatrixBuilder;
import org.metamechanists.displaymodellib.transformations.TransformationUtils;


@SuppressWarnings({"unused", "WeakerAccess"})
@Getter
public class ModelItem implements ModelComponent {
    private final ItemDisplayBuilder main;
    private final TransformationMatrixBuilder matrixBuilder;

    public ModelItem() {
        this.main = new ItemDisplayBuilder();
        this.matrixBuilder = new TransformationMatrixBuilder();
    }
    public ModelItem(final @NotNull ModelItem other) {
        this.main = new ItemDisplayBuilder(other.main);
        this.matrixBuilder = new TransformationMatrixBuilder(other.matrixBuilder);
    }

    /**
     * Translates the cuboid by the given vector
     * This depends on the rotation of the component
     * For example, if you rotate by 90 degrees before translating, the translation will also be rotated by 90 degrees
     */
    public ModelItem translate(@NotNull final Vector3f translation) {
        matrixBuilder.translate(translation);
        return this;
    }
    /**
     * Translates the cuboid by the given vector
     * This depends on the rotation of the component
     * For example, if you rotate by 90 degrees before translating, the translation will also be rotated by 90 degrees
     */
    public ModelItem translate(final float x, final float y, final float z) {
        return translate(new Vector3f(x, y, z));
    }

    /**
     * Translates the cuboid by the given vector
     * This depends on the rotation of the component
     * For example, if you rotate by 90 degrees before translating, the translation will also be rotated by 90 degrees
     */
    public ModelItem translate(@NotNull final Vector3d translation) {
        matrixBuilder.translate(TransformationUtils.dropPrecision(translation));
        return this;
    }
    /**
     * Translates the cuboid by the given vector
     * This depends on the rotation of the component
     * For example, if you rotate by 90 degrees before translating, the translation will also be rotated by 90 degrees
     */
    public ModelItem translate(final double x, final double y, final double z) {
        return translate(new Vector3d(x, y, z));
    }

    /**
     * Looks along the provided vector
     * This is useful eg to align a component with the direction a player is looking
     */
    public ModelItem lookAlong(final @NotNull Vector3f facing) {
        matrixBuilder.lookAlong(facing);
        return this;
    }
    /**
     * Looks along the provided vector
     * This is useful eg to align a component with the direction a player is looking
     */
    public ModelItem lookAlong(final @NotNull Vector3d facing) {
        matrixBuilder.lookAlong(new Vector3f((float) facing.x, (float) facing.y, (float) facing.z));
        return this;
    }
    /**
     * Looks along the provided face
     * This is useful eg to align a component with the direction a player is looking
     */
    public ModelItem lookAlong(final @NotNull BlockFace face) {
        return lookAlong(face.getDirection().toVector3f());
    }

    /**
     * Scales the component on each axis
     */
    public ModelItem scale(@NotNull final Vector3f size) {
        matrixBuilder.scale(size);
        return this;
    }
    /**
     * Scales the component on each axis
     */
    public ModelItem scale(final float x, final float y, final float z) {
        return scale(new Vector3f(x, y, z));
    }
    /**
     * Scales the component on all axes
     */
    public ModelItem scale(final float size) {
        return scale(new Vector3f(size));
    }

    /**
     * Scales the component on each axis
     */
    public ModelItem scale(@NotNull final Vector3d size) {
        matrixBuilder.scale(TransformationUtils.dropPrecision(size));
        return this;
    }
    /**
     * Scales the component on each axis
     */
    public ModelItem scale(final double x, final double y, final double z) {
        return scale(new Vector3d(x, y, z));
    }
    /**
     * Scales the component on all axes
     */
    public ModelItem scale(final double size) {
        return scale(new Vector3d(size));
    }

    /**
     * Rotates the component by euler angles in radians
     */
    public ModelItem rotate(@NotNull final Vector3d rotation) {
        matrixBuilder.rotate(rotation);
        return this;
    }
    /**
     * Rotates the component by euler angles in radians
     */
    public ModelItem rotate(final double x, final double y, final double z) {
        return rotate(new Vector3d(x, y, z));
    }
    /**
     * Rotates the component by euler angles in radians around the X axis
     */
    public ModelItem rotateX(final double rotation) {
        return rotate(new Vector3d(rotation, 0, 0));
    }
    /**
     * Rotates the component by euler angles in radians around the Y axis
     */
    public ModelItem rotateY(final double rotation) {
        return rotate(new Vector3d(0, rotation, 0));
    }
    /**
     * Rotates the component by euler angles in radians around the Z axis
     */
    public ModelItem rotateZ(final double rotation) {
        return rotate(new Vector3d(0, 0, rotation));
    }

    /**
     * Rotates the component by euler angles in radians
     */
    public ModelItem rotate(@NotNull final Vector3f rotation) {
        matrixBuilder.rotate(new Vector3d(rotation));
        return this;
    }
    /**
     * Rotates the component by euler angles in radians
     */
    public ModelItem rotate(final float x, final float y, final float z) {
        return rotate(new Vector3f(x, y, z));
    }
    /**
     * Rotates the component by euler angles in radians around the X axis
     */
    public ModelItem rotateX(final float rotation) {
        return rotate(new Vector3f(rotation, 0, 0));
    }
    /**
     * Rotates the component by euler angles in radians around the Y axis
     */
    public ModelItem rotateY(final float rotation) {
        return rotate(new Vector3f(0, rotation, 0));
    }
    /**
     * Rotates the component by euler angles in radians around the Z axis
     */
    public ModelItem rotateZ(final float rotation) {
        return rotate(new Vector3f(0, 0, rotation));
    }

    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelItem undoRotate(@NotNull final Vector3d rotation) {
        matrixBuilder.rotateBackwards(rotation);
        return this;
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelItem undoRotate(final double x, final double y, final double z) {
        return undoRotate(new Vector3d(x, y, z));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelItem undoRotateX(final double rotation) {
        return undoRotate(new Vector3d(rotation, 0, 0));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelItem undoRotateY(final double rotation) {
        return undoRotate(new Vector3d(0, rotation, 0));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelItem undoRotateZ(final double rotation) {
        return undoRotate(new Vector3d(0, 0, rotation));
    }

    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelItem undoRotate(final float x, final float y, final float z) {
        return undoRotate(new Vector3d(x, y, z));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelItem undoRotate(@NotNull final Vector3f rotation) {
        matrixBuilder.rotateBackwards(new Vector3d(rotation));
        return this;
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelItem undoRotateX(final float rotation) {
        return undoRotate(new Vector3f(rotation, 0, 0));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelItem undoRotateY(final float rotation) {
        return undoRotate(new Vector3f(0, rotation, 0));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelItem undoRotateZ(final float rotation) {
        return undoRotate(new Vector3f(0, 0, rotation));
    }

    /**
     * Overrides material()
     */
    public ModelItem item(@NotNull final ItemStack itemStack) {
        main.itemStack(itemStack);
        return this;
    }
    public ModelItem material(@NotNull final Material material) {
        main.material(material);
        return this;
    }
    public ModelItem brightness(final int blockBrightness) {
        main.brightness(blockBrightness);
        return this;
    }
    public ModelItem glow(@NotNull final Color color) {
        main.glow(color);
        return this;
    }
    public ModelItem billboard(@NotNull final Billboard billboard) {
        main.billboard(billboard);
        return this;
    }
    public ModelItem viewRange(final int viewRange) {
        main.viewRange(viewRange);
        return this;
    }

    @Override
    public Matrix4f getMatrix() {
        // 1.20 added 180 degrees to item display rotation, let's account for this
        if (Utils.getMajorServerVersion() >= 20) {
            return new Matrix4f()
                    .rotateXYZ(new Vector3f((float) Math.PI, (float) Math.PI, (float) Math.PI))
                    .mul(matrixBuilder.buildForBlockDisplay());
        }

        return matrixBuilder.buildForBlockDisplay();
    }
    @Override
    public void updateMatrix(final @NotNull Display display) {
        if (!(display instanceof final ItemDisplay itemDisplay)) {
            throw new IllegalArgumentException("Must provide an ItemDisplay");
        }
        itemDisplay.setTransformationMatrix(getMatrix());
    }
    @Override
    public void update(final @NotNull Display display) {
        if (!(display instanceof final ItemDisplay itemDisplay)) {
            throw new IllegalArgumentException("Must provide an ItemDisplay");
        }
        main.transformation(getMatrix()).update(itemDisplay);
    }
    @Override
    public ItemDisplay build(@NotNull final Location origin) {
        return main.transformation(getMatrix()).build(origin);
    }
    @Override
    public ItemDisplay build(@NotNull final Block block) {
        return build(block.getLocation());
    }
}
