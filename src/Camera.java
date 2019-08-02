import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class Camera {
    double x, y, z;
    double phi; // radians
    double horizontalViewAngle, verticalViewAngle; // radians

    public Camera(double x, double y, double z, double phi, double horizontalViewAngle, double verticalViewAngle) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.phi = phi;
        this.horizontalViewAngle = horizontalViewAngle;
        this.verticalViewAngle = verticalViewAngle;
    }

    public BufferedImage renderImage(World world, int width, int height) {
        BufferedImage myImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        // TODO: Insert rendering code here.
        myImage.setRGB(10, 10, Color.BLUE.getRGB());
        myImage.setRGB(10, 11, Color.RED.getRGB());
        myImage.setRGB(10, 12, Color.GREEN.getRGB());

        return myImage;
    }

    public static void main(String[] args) throws IOException {
        Camera myCamera = new Camera(0, 0, 0, 0, 0.5*Math.PI, 0.5*Math.PI);
        Sphere mySphere = new Sphere(5, 5, 0, 3, Color.BLUE);
        Set<Sphere> myObjects = Collections.singleton(mySphere);
        World myWorld = new World(myObjects);
        
        BufferedImage myRenderedImage = myCamera.renderImage(myWorld, 600, 600);

        File myOutputFile = new File("World.png");
        ImageIO.write(myRenderedImage, "PNG", myOutputFile);
    }
}
