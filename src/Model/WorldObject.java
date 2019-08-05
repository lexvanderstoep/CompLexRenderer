package Model;

import Data.Hit;
import Data.Vector3D;

import java.awt.*;

public abstract class WorldObject {
    private String name;

    public WorldObject() {
        this("");
    }

    public WorldObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract Hit computeHit (Vector3D origin, Vector3D direction);

    public abstract Color getColor();
}
