package Model;

import Data.Hit;
import Data.Vector3D;

public class Plane extends WorldObject {
    private Vector3D normal;
    private Vector3D location;

    public Plane(Material material, Vector3D normal, Vector3D location) {
        super(material);
        this.normal = normal;
        this.location = location;
    }

    public Plane(String name, Material material, Vector3D normal, Vector3D location) {
        super(name, material);
        this.normal = normal;
        this.location = location;
    }

    public Vector3D getNormal() {
        return normal;
    }

    public Vector3D getLocation() {
        return location;
    }

    @Override
    public Hit computeHit(Vector3D origin, Vector3D direction) {
        return null; // TODO
    }
}
