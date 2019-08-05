package Data;

public class Hit {
    private Vector3D hitLocation;
    private Vector3D hitNormal;
    private double distanceFromOrigin;

    public Hit(Vector3D hitLocation, Vector3D hitNormal, double distanceFromOrigin) {
        this.hitLocation = hitLocation;
        this.hitNormal = hitNormal;
        this.distanceFromOrigin = distanceFromOrigin;
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
