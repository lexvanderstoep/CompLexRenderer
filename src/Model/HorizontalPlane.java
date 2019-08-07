package Model;

import Data.Hit;
import Data.Vector3D;

import java.awt.*;

import static Data.Vector3D.UNIT_Z;

public class HorizontalPlane extends WorldObject {
    private double height;

    public HorizontalPlane(Color color, double height) {
        super(color);
        this.height = height;
    }

    public HorizontalPlane(String name, Color color, double height) {
        super(name, color);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public Hit computeHit(Vector3D origin, Vector3D direction) {
        // The ray is parallel to the plane
        if (direction.z() == 0) {
            return null;
        }

        double t = (height - origin.z()) / direction.z();

        // The ray hits the plane behind the view
        if (t < 0) {
            return null;
        }

        Vector3D hitLocation = origin.add(direction.scale(t));
        Vector3D hitNormal = UNIT_Z;

        return new Hit(this, hitLocation, hitNormal, t);
    }
}
