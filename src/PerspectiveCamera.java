import Data.Vector3D;
import Model.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PerspectiveCamera extends Camera {
    private double horizontalViewAngle, verticalViewAngle; // radians

    public PerspectiveCamera(Color backgroundColor, Vector3D position, double phi, double horizontalViewAngle, double verticalViewAngle) {
        super(backgroundColor, position, phi);
        this.horizontalViewAngle = horizontalViewAngle;
        this.verticalViewAngle = verticalViewAngle;
    }

    public BufferedImage renderImage(World world, int width, int height) {
        BufferedImage myImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        Vector3D myViewDirection = getViewDirection();

        // Compute directions to the corner pixels.
        Vector3D leftTopPixel = myViewDirection.rotateZ(0.5 * horizontalViewAngle)
                                              .rotateY(0.5 * verticalViewAngle);
        Vector3D rightTopPixel = myViewDirection.rotateZ(-0.5 * horizontalViewAngle)
                                                .rotateY(0.5 * verticalViewAngle);
        Vector3D leftToRight = rightTopPixel.minus(leftTopPixel);
        Vector3D leftBottomPixel = myViewDirection.rotateZ(0.5 * horizontalViewAngle)
                                                  .rotateY(-0.5 * verticalViewAngle);
        Vector3D topToBottom = leftBottomPixel.minus(leftTopPixel);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Vector3D myRayPosition = position;

                // Compute the ray direction
                Vector3D myRayDirection = leftTopPixel.add(leftToRight.scale((double)x/width))
                                                      .add(topToBottom.scale((double)y/height))
                                                      .normalise();

                Color myRayTracedColor = traceRay(myRayPosition, myRayDirection, world);
                myImage.setRGB(x, y ,myRayTracedColor.getRGB());
            }

            System.out.println((double)x/width*100.0 + "%");
        }

        return myImage;
    }
}