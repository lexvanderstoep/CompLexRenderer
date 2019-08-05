import Data.Vector3D;
import Model.Sphere;
import Model.World;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static Data.Vector3D.UNIT_X;
import static Data.Vector3D.ZERO;

public abstract class Camera {
    Vector3D position;
    double phi; // radians

    public Camera(Vector3D position, double phi) {
        this.position = position;
        this.phi = phi;
    }

    protected Color traceRay(Vector3D origin, Vector3D direction, World world) {
        for (Sphere sphere : world) {
            // If a ray goes through an object, then return the colour of that object
            if (sphere.distanceToRay(origin, direction) <= 0) {
                return sphere.getColor();
            }
        }

        return Color.BLACK;
    }

    public abstract BufferedImage renderImage(World world, int width, int height);

    public static void main(String[] args) throws IOException {
        Camera myCamera = new OrthographicCamera(new Vector3D(5.0, 0, 0), Math.PI, 4, 3);
        Sphere mySphereOne = new Sphere(new Vector3D(0, 0, 0), 1, Color.BLUE);
        Sphere mySphereTwo = new Sphere(new Vector3D(0, 0.5, 0.2), 0.8, Color.RED);
        Sphere mySphereThree = new Sphere (new Vector3D(0, -0.5, 0), 0.5, Color.YELLOW);
        World myWorld = new World();
        myWorld.addObject(mySphereOne);
        myWorld.addObject(mySphereTwo);
        myWorld.addObject(mySphereThree);
        
        BufferedImage myRenderedImage = myCamera.renderImage(myWorld, 800, 600);

        File myOutputFile = new File("World.png");
        ImageIO.write(myRenderedImage, "PNG", myOutputFile);
    }
}
