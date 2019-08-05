package Data;

public final class Vector3D {
    private double x, y, z;
    public static Vector3D UNIT_X = new Vector3D(1, 0, 0);
    public static Vector3D UNIT_Y = new Vector3D(0, 1, 0);
    public static Vector3D UNIT_Z = new Vector3D(0, 0, 1);
    public static Vector3D ZERO = new Vector3D(0, 0, 0);

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    public double dot(Vector3D that) {
        return this.x * that.x +
                this.y * that.y +
                this.z * that.z;
    }

    public Vector3D add(Vector3D that) {
        return new Vector3D(this.x + that.x, this.y + that.y, this.z + that.z);
    }

    public Vector3D minus(Vector3D that) {
        return this.add(that.scale(-1));
    }

    public Vector3D cross(Vector3D that) {
        return new Vector3D(this.y * that.z - this.z * that.y,
                this.z * that.x - this.x * that.z,
                this.x * that.y - this.y * that.x);
    }

    public double abs() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3D scale(double factor) {
        return new Vector3D(factor * x, factor * y, factor * z);
    }

    /**
     * Rotates the vector about the X axis.
     *
     * @param angle The angle to rotate by, in radians.
     * @return The rotated vector.
     */
    public Vector3D rotateX(double angle) {
        return new Vector3D(x,
                y * Math.cos(angle) - z * Math.sin(angle),
                y * Math.sin(angle) + z * Math.cos(angle));
    }

    /**
     * Rotates the vector about the Y axis.
     *
     * @param angle The angle to rotate by, in radians.
     * @return The rotated vector.
     */
    public Vector3D rotateY(double angle) {
        return new Vector3D(x * Math.cos(angle) + z * Math.sin(angle),
                y,
                -x * Math.sin(angle) + z * Math.cos(angle));
    }

    /**
     * Rotates the vector about the Z axis.
     *
     * @param angle The angle to rotate by, in radians.
     * @return The rotated vector.
     */
    public Vector3D rotateZ(double angle) {
        return new Vector3D(
                x * Math.cos(angle) - y * Math.sin(angle),
                x * Math.sin(angle) + y * Math.cos(angle),
                z);
    }

    public Vector3D normalise() {
        return this.scale(1.0/this.abs());
    }
}