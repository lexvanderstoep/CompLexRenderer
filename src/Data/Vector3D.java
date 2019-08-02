package Data;

public class Vector3D {
    private double x, y, z;

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
}
