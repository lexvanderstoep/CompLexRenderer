package Model;

import Data.Vector3D;

public class PointLight {
    private Vector3D position;

    public PointLight(Vector3D position) {
        this.position = position;
    }

    public Vector3D getPosition() {
        return position;
    }
}
