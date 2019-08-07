import Data.Vector3D;
import Model.HorizontalPlane;
import Model.PointLight;
import Model.Sphere;
import Model.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestWorld {

    public static void main(String[] args) throws IOException {
        //Camera myCamera = new OrthographicCamera(new Vector3D(5.0, 0, 0), Math.PI, 4, 3);
        Camera myCamera = new PerspectiveCamera(new Vector3D(5.0, 0, 1), Math.PI, 0.25*Math.PI, 0.1875 * Math.PI);
        HorizontalPlane myPlane = new HorizontalPlane(Color.WHITE, 0);
        Sphere mySphereOne = new Sphere(new Vector3D(0, 1.5, 0.5), 0.5, Color.BLUE, "One");
        Sphere mySphereTwo = new Sphere(new Vector3D(-6, 0.5, 0.8), 0.8, Color.RED, "Two");
        Sphere mySphereThree = new Sphere (new Vector3D(-1.5, -0.9, 0.5), 0.5, Color.YELLOW, "Three");
        PointLight mySun = new PointLight(new Vector3D(2.0, -3.0, 2.0));
        World myWorld = new World();
        myWorld.addObject(myPlane);
        myWorld.addObject(mySphereOne);
        myWorld.addObject(mySphereTwo);
        myWorld.addObject(mySphereThree);
        myWorld.addLight(mySun);

        BufferedImage myRenderedImage = myCamera.renderImage(myWorld, 1600, 1200);

        File myOutputFile = new File("World.png");
        ImageIO.write(myRenderedImage, "PNG", myOutputFile);
    }
}
