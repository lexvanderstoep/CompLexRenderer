import Data.Hit;
import Data.Vector3D;
import Model.PointLight;
import Model.Sphere;
import Model.World;
import Model.WorldObject;
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
    protected static Color BACKGROUND_COLOR = Color.BLACK;

    Vector3D position;
    double phi; // radians

    public Camera(Vector3D position, double phi) {
        this.position = position;
        this.phi = phi;
    }

    protected Color traceRay(Vector3D origin, Vector3D direction, World world) {
        WorldObject closestHitObject = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        Hit closestHit = null;
        Color myColor = BACKGROUND_COLOR;

        // Compute the ray trace hit.
        for (WorldObject object : world.getObjects()) {
            // If a ray goes through an object, then return the colour of that object
            Hit myRayTraceHit = object.computeHit(origin, direction);
            if (myRayTraceHit != null) {
                if (myRayTraceHit.getDistanceFromOrigin() < closestDistance) {
                    closestHitObject = object;
                    closestHit = myRayTraceHit;
                    myColor = closestHitObject.getColor();
                    closestDistance = closestHit.getDistanceFromOrigin();
                }
            }
        }

        // Compute lightning.
        if (closestHit != null) {
            double alpha = 0.0;
            double beta = 0.0;

            for (PointLight light : world.getLights()) {
                Vector3D L = light.getPosition().minus(closestHit.getHitLocation());
                Vector3D N = closestHit.getHitNormal();
                Vector3D minusL = L.scale(-1.0);
                Vector3D R = minusL.minus(N.scale(2.0 * minusL.dot(N))).normalise();
                Vector3D V = origin.minus(closestHit.getHitLocation()).normalise();
                double RdotV = Math.max(0, R.dot(V));

                double diffusion = Math.max(0, L.dot(N));
                double specular = Math.pow(RdotV, 7.0);

                alpha += diffusion;
                beta += specular;
            }

            alpha = Math.max(Math.min(1.0, alpha) * 0.8, 0.2);
            beta = Math.max(Math.min(1.0, beta), 0);

            int r = Math.min(255, (int)(myColor.getRed() * alpha + 256 * beta));
            int b = Math.min(255, (int)(myColor.getBlue() * alpha + 256 * beta));
            int g = Math.min(255, (int)(myColor.getGreen() * alpha + 256 * beta));
            int a = myColor.getAlpha();
            myColor = new Color(r, g, b, a);
        }

        return myColor;
    }

    public abstract BufferedImage renderImage(World world, int width, int height);

    public static void main(String[] args) throws IOException {
        Camera myCamera = new OrthographicCamera(new Vector3D(5.0, 0, 0), Math.PI, 4, 3);
        Sphere mySphereOne = new Sphere(new Vector3D(0, -0.4, -0.2), 0.5, Color.BLUE, "One");
        Sphere mySphereTwo = new Sphere(new Vector3D(-5, 0.5, 0.2), 0.8, Color.RED, "Two");
        Sphere mySphereThree = new Sphere (new Vector3D(-0.5, -0.9, 0), 0.5, Color.YELLOW, "Three");
        PointLight mySun = new PointLight(new Vector3D(2.0, 3.0, 2.0));
        World myWorld = new World();
        myWorld.addObject(mySphereOne);
        myWorld.addObject(mySphereTwo);
        myWorld.addObject(mySphereThree);
        myWorld.addLight(mySun);
        
        BufferedImage myRenderedImage = myCamera.renderImage(myWorld, 1600, 1200);

        File myOutputFile = new File("World.png");
        ImageIO.write(myRenderedImage, "PNG", myOutputFile);
    }
}
