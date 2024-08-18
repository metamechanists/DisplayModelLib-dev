package org.metamechanists.displaymodellib.transformations.components;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaterniond;
import org.joml.Quaternionf;
import org.joml.Vector3d;
import org.joml.Vector3f;


/**
 * Represents the reverse of a rotation. For example, if you first rotate by (0, 1, 2), you could then get back to the starting orientation by doing a backward rotation of (0, 1, 2)
 */
public class RotationBackwards implements TransformationMatrixComponent {
    private final @Nullable Quaterniond quaternion;
    private final @Nullable Vector3d vector;

    public RotationBackwards(@NotNull final Vector3d rotation) {
        this.quaternion = null;
        this.vector = rotation;
    }

    public RotationBackwards(@NotNull final Quaterniond rotation) {
        this.quaternion = rotation;
        this.vector = null;
    }

    @Override
    public void apply(@NotNull final Matrix4f matrix) {
        if (vector != null) {
            matrix.mul(new Matrix4f().rotateZYX(new Vector3f((float) -vector.x, (float) -vector.y, (float) -vector.z)));
        } else if (quaternion != null) {
            matrix.mul(new Matrix4f().rotate(new Quaternionf(quaternion.x, quaternion.y, quaternion.z, quaternion.w).invert()));
        }
    }
}
