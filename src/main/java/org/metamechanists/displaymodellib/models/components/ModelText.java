package org.metamechanists.displaymodellib.models.components;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Display;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaterniond;
import org.joml.Quaternionf;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.metamechanists.displaymodellib.builders.TextDisplayBuilder;
import org.metamechanists.displaymodellib.transformations.TransformationMatrixBuilder;
import org.metamechanists.displaymodellib.transformations.TransformationUtils;


@SuppressWarnings({"unused", "WeakerAccess"})
@Getter
public class ModelText implements ModelComponent {
    private final TextDisplayBuilder main;
    private final TransformationMatrixBuilder matrixBuilder;

    public ModelText() {
        this.main = new TextDisplayBuilder();
        this.matrixBuilder = new TransformationMatrixBuilder();
    }
    public ModelText(final @NotNull ModelText other) {
        this.main = new TextDisplayBuilder(other.main);
        this.matrixBuilder = new TransformationMatrixBuilder(other.matrixBuilder);
    }

    /**
     * Translates the cuboid by the given vector
     * This depends on the rotation of the component
     * For example, if you rotate by 90 degrees before translating, the translation will also be rotated by 90 degrees
     */
    public ModelText translate(@NotNull final Vector3f translation) {
        matrixBuilder.translate(translation);
        return this;
    }
    /**
     * Translates the cuboid by the given vector
     * This depends on the rotation of the component
     * For example, if you rotate by 90 degrees before translating, the translation will also be rotated by 90 degrees
     */
    public ModelText translate(final float x, final float y, final float z) {
        return translate(new Vector3f(x, y, z));
    }

    /**
     * Translates the cuboid by the given vector
     * This depends on the rotation of the component
     * For example, if you rotate by 90 degrees before translating, the translation will also be rotated by 90 degrees
     */
    public ModelText translate(@NotNull final Vector3d translation) {
        matrixBuilder.translate(TransformationUtils.dropPrecision(translation));
        return this;
    }
    /**
     * Translates the cuboid by the given vector
     * This depends on the rotation of the component
     * For example, if you rotate by 90 degrees before translating, the translation will also be rotated by 90 degrees
     */
    public ModelText translate(final double x, final double y, final double z) {
        return translate(new Vector3d(x, y, z));
    }

    /**
     * Looks along the provided vector
     * This is not the same as .facing(...), because this affects the transformation, while .facing(...) affects where the text is rendered after the transformations are applied
     * This is useful eg to align a component with the direction a player is looking
     */
    public ModelText lookAlong(final @NotNull Vector3f facing) {
        matrixBuilder.lookAlong(facing);
        return this;
    }
    /**
     * Looks along the provided vector
     * This is not the same as .facing(...), because this affects the transformation, while .facing(...) affects where the text is rendered after the transformations are applied
     * This is useful eg to align a component with the direction a player is looking
     */
    public ModelText lookAlong(final @NotNull Vector3d facing) {
        matrixBuilder.lookAlong(new Vector3f((float) facing.x, (float) facing.y, (float) facing.z));
        return this;
    }
    /**
     * Looks along the provided face
     * This is not the same as .facing(...), because this affects the transformation, while .facing(...) affects where the text is rendered after the transformations are applied
     * This is useful eg to align a component with the direction a player is looking
     */
    public ModelText lookAlong(final @NotNull BlockFace face) {
        return lookAlong(face.getDirection().toVector3f());
    }

    /**
     * Scales the component on each axis
     */
    public ModelText scale(@NotNull final Vector3f size) {
        matrixBuilder.scale(size);
        return this;
    }
    /**
     * Scales the component on each axis
     */
    public ModelText scale(final float x, final float y, final float z) {
        return scale(new Vector3f(x, y, z));
    }
    /**
     * Scales the component on all axes
     */
    public ModelText scale(final float size) {
        return scale(new Vector3f(size));
    }

    /**
     * Scales the component on each axis
     */
    public ModelText scale(@NotNull final Vector3d size) {
        matrixBuilder.scale(TransformationUtils.dropPrecision(size));
        return this;
    }
    /**
     * Scales the component on each axis
     */
    public ModelText scale(final double x, final double y, final double z) {
        return scale(new Vector3d(x, y, z));
    }
    /**
     * Scales the component on all axes
     */
    public ModelText scale(final double size) {
        return scale(new Vector3d(size));
    }

    /**
     * Rotates the component by euler angles in radians
     */
    public ModelText rotate(@NotNull final Vector3d rotation) {
        matrixBuilder.rotate(rotation);
        return this;
    }
    /**
     * Rotates the component by a quaternion
     */
    public ModelText rotate(@NotNull final Quaterniond rotation) {
        matrixBuilder.rotate(rotation);
        return this;
    }
    /**
     * Rotates the component by euler angles in radians
     */
    public ModelText rotate(final double x, final double y, final double z) {
        return rotate(new Vector3d(x, y, z));
    }
    /**
     * Rotates the component by euler angles in radians around the X axis
     */
    public ModelText rotateX(final double rotation) {
        return rotate(new Vector3d(rotation, 0, 0));
    }
    /**
     * Rotates the component by euler angles in radians around the Y axis
     */
    public ModelText rotateY(final double rotation) {
        return rotate(new Vector3d(0, rotation, 0));
    }
    /**
     * Rotates the component by euler angles in radians around the Z axis
     */
    public ModelText rotateZ(final double rotation) {
        return rotate(new Vector3d(0, 0, rotation));
    }

    /**
     * Rotates the component by euler angles in radians
     */
    public ModelText rotate(@NotNull final Vector3f rotation) {
        matrixBuilder.rotate(new Vector3d(rotation));
        return this;
    }
    /**
     * Rotates the component by a quaternion
     */
    public ModelText rotate(@NotNull final Quaternionf quaternion) {
        matrixBuilder.rotate(new Quaterniond(quaternion));
        return this;
    }
    /**
     * Rotates the component by euler angles in radians
     */
    public ModelText rotate(final float x, final float y, final float z) {
        return rotate(new Vector3f(x, y, z));
    }
    /**
     * Rotates the component by euler angles in radians around the X axis
     */
    public ModelText rotateX(final float rotation) {
        return rotate(new Vector3f(rotation, 0, 0));
    }
    /**
     * Rotates the component by euler angles in radians around the Y axis
     */
    public ModelText rotateY(final float rotation) {
        return rotate(new Vector3f(0, rotation, 0));
    }
    /**
     * Rotates the component by euler angles in radians around the Z axis
     */
    public ModelText rotateZ(final float rotation) {
        return rotate(new Vector3f(0, 0, rotation));
    }

    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelText undoRotate(@NotNull final Vector3d rotation) {
        matrixBuilder.rotateBackwards(rotation);
        return this;
    }
    /**
     * Undoes a previous rotation
     */
    public ModelText undoRotate(@NotNull final Quaterniond rotation) {
        matrixBuilder.rotateBackwards(rotation);
        return this;
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelText undoRotate(final double x, final double y, final double z) {
        return undoRotate(new Vector3d(x, y, z));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelText undoRotateX(final double rotation) {
        return undoRotate(new Vector3d(rotation, 0, 0));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelText undoRotateY(final double rotation) {
        return undoRotate(new Vector3d(0, rotation, 0));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelText undoRotateZ(final double rotation) {
        return undoRotate(new Vector3d(0, 0, rotation));
    }

    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelText undoRotate(final float x, final float y, final float z) {
        return undoRotate(new Vector3d(x, y, z));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelText undoRotate(@NotNull final Vector3f rotation) {
        matrixBuilder.rotateBackwards(new Vector3d(rotation));
        return this;
    }
    /**
     * Undoes a previous rotation
     */
    public ModelText undoRotate(@NotNull final Quaternionf rotation) {
        matrixBuilder.rotateBackwards(new Quaterniond(rotation));
        return this;
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelText undoRotateX(final float rotation) {
        return undoRotate(new Vector3f(rotation, 0, 0));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelText undoRotateY(final float rotation) {
        return undoRotate(new Vector3f(0, rotation, 0));
    }
    /**
     * Undoes a previous rotation
     * You'd use this if, for example, you rotated and then translated, and now want the component back in its starting orientation
     */
    public ModelText undoRotateZ(final float rotation) {
        return undoRotate(new Vector3f(0, 0, rotation));
    }

    /**
     * Default is transparent
     */
    public ModelText background(@NotNull final Color color) {
        main.backgroundColor(color);
        return this;
    }
    public ModelText text(@NotNull final String text) {
        main.text(text);
        return this;
    }
    public ModelText text(@NotNull final Component text) {
        main.text(text);
        return this;
    }
    /**
     * Default is centered
     */
    public ModelText alignment(@NotNull final TextAlignment alignment) {
        main.alignment(alignment);
        return this;
    }
    public ModelText brightness(final int blockBrightness) {
        main.brightness(blockBrightness);
        return this;
    }
    public ModelText glow(@NotNull final Color color) {
        main.glow(color);
        return this;
    }
    public ModelText billboard(@NotNull final Billboard billboard) {
        main.billboard(billboard);
        return this;
    }

    @Override
    public Matrix4f getMatrix() {
        return matrixBuilder.buildForBlockDisplay();
    }
    @Override
    public void updateMatrix(final @NotNull Display display) {
        if (!(display instanceof final TextDisplay textDisplay)) {
            throw new IllegalArgumentException("Must provide a TextDisplay");
        }
        textDisplay.setTransformationMatrix(getMatrix());
    }
    @Override
    public void update(final @NotNull Display display) {
        if (!(display instanceof final TextDisplay textDisplay)) {
            throw new IllegalArgumentException("Must provide a TextDisplay");
        }
        main.transformation(getMatrix()).update(textDisplay);
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
