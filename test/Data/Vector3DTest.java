package Data;

import org.junit.jupiter.api.Test;
import Data.Vector3D;

import static Data.Vector3D.*;
import static org.junit.jupiter.api.Assertions.*;

class Vector3DTest {
    private static double EPS = 1E-5;

    @Test
    void x() {
        assert equals(UNIT_X.x(), 1.0);
        assert equals(UNIT_Y.x(), 0.0);
        assert equals(UNIT_Z.x(), 0.0);
    }

    @Test
    void y() {
        assert equals(UNIT_X.y(), 0.0);
        assert equals(UNIT_Y.y(), 1.0);
        assert equals(UNIT_Z.y(), 0.0);
    }

    @Test
    void z() {
        assert equals(UNIT_X.z(), 0.0);
        assert equals(UNIT_Y.z(), 0.0);
        assert equals(UNIT_Z.z(), 1.0);
    }

    @Test
    void dot() {
        assert equals(UNIT_X.dot(UNIT_Y), 0.0);
        assert equals(UNIT_X.dot(UNIT_X), 1.0);
    }

    @Test
    void add() {
        assert equals(UNIT_X.add(UNIT_Y), new Vector3D(1, 1, 0));
    }

    @Test
    void minus() {
        assert equals(UNIT_X.minus(UNIT_Y), new Vector3D(1, -1, 0));
    }

    @Test
    void cross() {
        assert equals(UNIT_X.cross(UNIT_Y), UNIT_Z);
        assert equals(UNIT_Y.cross(UNIT_X), UNIT_Z.scale(-1.0));
    }

    @Test
    void abs() {
        assert equals(UNIT_X.abs(), 1.0);
        assert equals(ZERO.abs(), 0.0);
    }

    @Test
    void scale() {
        assert equals(UNIT_X.scale(0.0), ZERO);
        assert equals(ZERO.scale(1.0), ZERO);
        assert equals(UNIT_X.scale(2.0), new Vector3D(2.0, 0.0, 0.0));
    }

    @Test
    void rotateX() {
        assert equals(UNIT_X.rotateX(Math.PI), UNIT_X);
        assert equals(UNIT_Y.rotateX(Math.PI), UNIT_Y.scale(-1.0));
        assert equals(UNIT_Y.rotateX(0.5 * Math.PI), UNIT_Z);
    }

    @Test
    void rotateY() {
        assert equals(UNIT_Y.rotateY(0.5 * Math.PI), UNIT_Y);
        assert equals(UNIT_X.rotateY(Math.PI), UNIT_X.scale(-1.0));
        assert equals(UNIT_X.rotateY(-0.5 * Math.PI), UNIT_Z);
    }

    @Test
    void rotateZ() {
        assert equals(UNIT_Z.rotateZ(Math.PI), UNIT_Z);
        assert equals(UNIT_X.rotateZ(Math.PI), UNIT_X.scale(-1.0));
        assert equals(UNIT_X.rotateZ(0.5 * Math.PI), UNIT_Y);
    }

    @Test
    void normalise() {
        assert equals(UNIT_X.normalise(), UNIT_X);
        assert equals(UNIT_X.scale(2.0).normalise(), UNIT_X);
    }

    private boolean equals (Vector3D a, Vector3D b) {
        return equals(a.x(), b.x()) &&
               equals(a.y(), b.y()) &&
               equals(a.z(), b.z());
    }

    private boolean equals(double a, double b) {
        return Math.abs(a - b) < EPS;
    }
}