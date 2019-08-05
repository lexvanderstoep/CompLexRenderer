package Data;

import Model.WorldObject;

public class Hit {
    private WorldObject hitObject;
    private Vector3D hitLocation;
    private Vector3D hitNormal;
    private double distanceFromOrigin;

    public Hit(WorldObject hitObject, Vector3D hitLocation, Vector3D hitNormal, double distanceFromOrigin) {
        this.hitObject = hitObject;
        this.hitLocation = hitLocation;
        this.hitNormal = hitNormal;
        this.distanceFromOrigin = distanceFromOrigin;
    }

    public WorldObject getHitObject() {
        return hitObject;
    }

    public Vector3D getHitLocation() {
        return hitLocation;
    }

    public Vector3D getHitNormal() {
        return hitNormal;
    }

    public double getDistanceFromOrigin() {
        return distanceFromOrigin;
    }
}
