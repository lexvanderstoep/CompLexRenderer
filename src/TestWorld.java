import Data.Vector3D;
import Model.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Data.Vector3D.ZERO;

public class TestWorld {

    public static void main(String[] args) throws IOException {
        //Camera myCamera = new OrthographicCamera(new Vector3D(5.0, 0, 0), Math.PI, 4, 3);
        Camera myCamera = new PerspectiveCamera(
                new Color(60,100,115),
                new Vector3D(5.0, 0, 1),
                Math.PI,
                0.25*Math.PI,
                0.1875 * Math.PI);

        HorizontalPlane myPlane = new HorizontalPlane(
                new Material(
                        new Vector3D(0.2, 0.2, 0.2),
                        new Vector3D(0.3, 0.3, 0.3),
                        ZERO,
                        0.0),
                0);
        Sphere mySphereOne = new Sphere(
                new Material(
                        new Vector3D(0, 0, 0.2),
                        new Vector3D(0, 0, 0.6),
                        new Vector3D(0.7, 0.7, 0.7),
                        7.0),
                new Vector3D(0, 1.5, 0.5),
                0.5);
        Sphere mySphereTwo = new Sphere(
                new Material(
                        new Vector3D(0, 0.2, 0),
                        new Vector3D(0, 0.6, 0),
                        new Vector3D(0.7, 0.7, 0.7),
                        7.0),
                new Vector3D(-6, 0.5, 0.8),
                0.8);
        Sphere mySphereThree = new Sphere (
                new Material(
                        new Vector3D(0.2, 0, 0),
                        new Vector3D(0.6, 0, 0),
                        new Vector3D(0.7, 0.7, 0.7),
                        7.0),
                new Vector3D(-1.5, -0.9, 0.5),
                0.5);

        PointLight mySun = new PointLight(new Vector3D(2.0, -3.0, 2.0), Color.WHITE);

        World myWorld = new World(Color.WHITE);
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
