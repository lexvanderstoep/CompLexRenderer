package Model;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class World {
    private Set<WorldObject> theObjects;
    private Set<PointLight> theLights;
    private Color ambientLightning;

    public World(Color ambientLightning) {
        this(new HashSet<>(), new HashSet<>(), ambientLightning);
    }

    public World(Set<WorldObject> aObjects, Set<PointLight> aLights, Color ambientLightning) {
        this.theObjects = aObjects;
        this.theLights = aLights;
        this.ambientLightning = ambientLightning;
    }

    public boolean addObject(WorldObject anObject) {
        return theObjects.add(anObject);
    }

    public boolean addLight(PointLight aPointLight) {
        return theLights.add(aPointLight);
    }

    public Set<WorldObject> getObjects() {
        return theObjects;
    }

    public Set<PointLight> getLights() {
        return theLights;
    }

    public Color getAmbientLightning() {
        return ambientLightning;
    }
}
