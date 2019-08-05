package Model;

import Data.Vector3D;

import java.awt.*;

public class Sphere {
    Vector3D position;
    double radius;
    Color color;

    public Sphere(Vector3D position, double radius, Color color) {
        this.position = position;
        this.radius = radius;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public double distanceToRay(Vector3D origin, Vector3D direction) {
        double distanceToCentre =
                position.minus(origin)
                .cross(position.minus(origin.add(direction)))
                .abs()
                /direction.abs();
        return distanceToCentre - radius;
    }
}
