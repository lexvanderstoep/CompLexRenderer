package Model;

import Data.Hit;
import Data.Vector3D;

import java.awt.*;

public abstract class WorldObject {
    private String name;
    private Color color;

    public WorldObject(Color color) {
        this("", color);
    }

    public WorldObject(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public abstract Hit computeHit (Vector3D origin, Vector3D direction);
}
