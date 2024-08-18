package org.metamechanists.displaymodellib.transformations.components;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaterniond;
import org.joml.Quaternionf;
import org.joml.Vector3d;
import org.joml.Vector3f;


public class Rotation implements TransformationMatrixComponent {
    private final @Nullable Quaterniond quaternion;
    private final @Nullable Vector3d vector;

    public Rotation(@NotNull final Vector3d rotation) {
        this.quaternion = null;
        this.vector = rotation;
    }

    public Rotation(@NotNull final Quaterniond rotation) {
        this.quaternion = rotation;
        this.vector = null;
    }

    @Override
    public void apply(@NotNull final Matrix4f matrix) {
        if (vector != null) {
            matrix.mul(new Matrix4f().rotateXYZ(new Vector3f((float) vector.x, (float) vector.y, (float) vector.z)));
        } else if (quaternion != null) {
            matrix.mul(new Matrix4f().rotate(new Quaternionf(quaternion.x, quaternion.y, quaternion.z, quaternion.w)));
        }
    }
}
