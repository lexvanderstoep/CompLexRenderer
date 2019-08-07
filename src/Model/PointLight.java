package Model;

import Data.Vector3D;

import java.awt.*;

public class PointLight {
    private Vector3D position;
    private Color color;

    public PointLight(Vector3D position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Vector3D getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }
}
