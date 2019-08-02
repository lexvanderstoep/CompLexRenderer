import java.util.Set;

public class World {
    Set<Sphere> theObjects;

    public World(Set<Sphere> theObjects) {
        this.theObjects = theObjects;
    }

    public boolean addObject(Sphere aSphere) {
        return theObjects.add(aSphere);
    }
}
