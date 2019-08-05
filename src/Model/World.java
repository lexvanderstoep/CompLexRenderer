package Model;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class World implements Iterable<Sphere> {
    Set<Sphere> theObjects;

    public World() {
        this(new HashSet<>());
    }

    public World(Set<Sphere> theObjects) {
        this.theObjects = theObjects;
    }

    public boolean addObject(Sphere aSphere) {
        return theObjects.add(aSphere);
    }

    @NotNull
    @Override
    public Iterator<Sphere> iterator() {
        return theObjects.iterator();
    }


}
