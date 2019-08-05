package Model;

import Data.Vector3D;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static Data.Vector3D.*;
import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    private static double EPS = 1E-5;

    @Test
    void distanceToRay() {
        Sphere mySphere = new Sphere(ZERO, 1, Color.BLACK);
        Vector3D myRayOriginOne = new Vector3D(2, 0, 0);
        Vector3D myRayDirectionOne = UNIT_Y;
        assert(equals(mySphere.distanceToRay(myRayOriginOne, myRayDirectionOne), 1.0));

        Vector3D myRayOriginTwo = UNIT_X;
        Vector3D myRayDirectionTwo = UNIT_X;
        assert equals(mySphere.distanceToRay(myRayOriginTwo, myRayDirectionTwo), -1.0);
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