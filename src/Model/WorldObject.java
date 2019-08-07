package Model;

import Data.Hit;
import Data.Vector3D;

public abstract class WorldObject {
    private String name;
    private Material material;

    public WorldObject(Material material) {
        this.material = material;
    }

    public WorldObject(String name, Material material) {
        this.name = name;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public abstract Hit computeHit (Vector3D origin, Vector3D direction);
}
