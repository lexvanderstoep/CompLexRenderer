package Model;

import Data.Hit;
import Data.Vector3D;

import java.awt.*;

public class Sphere extends WorldObject {
    Vector3D position;
    double radius;
    Color color;

    public Sphere(Vector3D position, double radius, Color color) {
        this.position = position;
        this.radius = radius;
        this.color = color;
    }

    public Sphere(Vector3D position, double radius, Color color, String name) {
        super(name);
        this.position = position;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Hit computeHit (Vector3D origin, Vector3D direction) {
        double OdotD = origin.minus(position).dot(direction);
        double OdotO = origin.minus(position).dot(origin.minus(position));
        double base = OdotD * OdotD - OdotO + radius * radius;

        if (base >= 0) {
            double root = Math.sqrt(base);
            double t1 = -OdotD + root;
            double t2 = -OdotD - root;
            if (t1 >= 0 || t2 >= 0) {
                double t = (t1 < t2 && t1 >= 0) ? t1 : t2;
                Vector3D pt = origin.add(direction.scale(t));
                Vector3D normal = pt.minus(position).normalise();
                return new Hit(pt, normal, t);
            }
        }
        return null;
    }
}
