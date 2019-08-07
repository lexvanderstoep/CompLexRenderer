package Model;

import Data.Hit;
import Data.Vector3D;
import org.junit.jupiter.api.Test;

import static Data.Vector3D.ZERO;
import static org.junit.jupiter.api.Assertions.assertNull;

class SphereTest {
    private static double EPS = 1E-5;

    @Test
    void computeHit() {
        Sphere myVictim = new Sphere(new Material(
                ZERO,
                null,
                null,
                0, 0),
                ZERO,
                1);
        Vector3D myRayOrigin = new Vector3D(5, 0, 0);
        Vector3D myRayDirection = new Vector3D(-1, 0, 0);

        Hit myHit = myVictim.computeHit(myRayOrigin, myRayDirection);
        assert equals(myHit.getDistanceFromOrigin(), 4.0);
        assert equals(myHit.getHitLocation(), new Vector3D(1, 0, 0));
        assert equals(myHit.getHitNormal(), new Vector3D(1, 0, 0));

        myRayOrigin = new Vector3D(5, 2, 0);

        myHit = myVictim.computeHit(myRayOrigin, myRayDirection);
        assertNull(myHit);
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