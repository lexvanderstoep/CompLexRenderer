package Model;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class World {
    Set<WorldObject> theObjects;
    Set<PointLight> theLights;

    public World() {
        this(new HashSet<>(), new HashSet<>());
    }

    public World(Set<WorldObject> aObjects, Set<PointLight> aLights) {
        this.theObjects = aObjects;
        this.theLights = aLights;
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
}
