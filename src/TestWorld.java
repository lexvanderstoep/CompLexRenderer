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
        World myWorld = new World(new Color(50, 50, 50));

        Camera myCamera = new PerspectiveCamera(
                new Color(0,0,0),
                new Vector3D(5.0, 0, 1),
                Math.PI,
                1,
                0.25*Math.PI,
                0.1875 * Math.PI);

        HorizontalPlane myPlane = new HorizontalPlane(
                new Material(
                        new Vector3D(1, 1, 1),
                        new Vector3D(0.3, 0.3, 0.3),
                        ZERO,
                        0.0, 0.0),
                0);
        Sphere mySphereOne = new Sphere(
                "Blue ball",
                new Material(
                        new Vector3D(0, 0, 1),
                        new Vector3D(0, 0, 0.6),
                        new Vector3D(0.7, 0.7, 0.7),
                        7.0, 0.2),
                new Vector3D(0, 1.5, 0.55),
                0.55);
        Sphere mySphereTwo = new Sphere (
                "Red ball",
                new Material(
                        new Vector3D(1, 0, 0),
                        new Vector3D(0.6, 0, 0),
                        new Vector3D(0.7, 0.7, 0.7),
                        7.0, 0.2),
                new Vector3D(-3.1, -2.2, 0.55),
                0.55);
        Sphere mySphereThree = new Sphere (
                "Reflective ball",
                new Material(
                        new Vector3D(0, 0, 0),
                        new Vector3D(0.1, 0.1, 0.1),
                        new Vector3D(0.1, 0.1, 0.1),
                        7.0, 0.95),
                new Vector3D(-9, 1, 0.7),
                0.7
                );
        for (int i = -40; i < -30; i++) {
            for (int j = -5; j < 5; j++) {
                Sphere smallSphere = new Sphere(
                        "Small sphere " + i + " " + j,
                        new Material(
                                new Vector3D(0.8, 0.8, 0),
                                new Vector3D(0.6, 0.6, 0),
                                new Vector3D(0.7, 0.7, 0.7),
                                7.0, 0.3),
                        new Vector3D(i/5.0, j/5.0, 0.1),
                        0.1);
                myWorld.addObject(smallSphere);
            }
        }

        PointLight mySun = new PointLight(new Vector3D(2.0, -3.0, 2.0), Color.WHITE);

        myWorld.addObject(myPlane);
        myWorld.addObject(mySphereOne);
        myWorld.addObject(mySphereTwo);
        myWorld.addObject(mySphereThree);
        myWorld.addLight(mySun);

        BufferedImage myRenderedImage = myCamera.renderImage(myWorld, 3200, 2400);

        File myOutputFile = new File("World.png");
        ImageIO.write(myRenderedImage, "PNG", myOutputFile);
    }
}
