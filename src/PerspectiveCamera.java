import Data.Vector3D;
import Model.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PerspectiveCamera extends Camera {
    double horizontalViewAngle, verticalViewAngle; // radians

    public PerspectiveCamera(Vector3D position, double phi,
                             double horizontalViewAngle, double verticalViewAngle) {
        super(position, phi);
        this.horizontalViewAngle = horizontalViewAngle;
        this.verticalViewAngle = verticalViewAngle;
    }

    public BufferedImage renderImage(World world, int width, int height) {
        BufferedImage myImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        // TODO: Insert rendering code here.
        Graphics myGraphics = myImage.getGraphics();
        myGraphics.drawString("Not implemented yet", 10, 10);

        return myImage;
    }
}