package org.metamechanists.displaymodellib.transformations.components;

import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;


/**
 * Represents the reverse of a rotation. For example, if you first rotate by (0, 1, 2), you could then get back to the starting orientation by doing a backward rotation of (0, 1, 2)
 */
public class RotationBackwards implements TransformationMatrixComponent {
    private final Vector3d rotation;

    public RotationBackwards(@NotNull final Vector3d rotation) {
        this.rotation = rotation;
    }

    @Override
    public void apply(@NotNull final Matrix4f matrix) {
        matrix.rotateZYX(new Vector3f((float) -rotation.z, (float) -rotation.y, (float) -rotation.x));
    }
}
