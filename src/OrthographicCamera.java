import Data.Vector3D;
import Model.World;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Data.Vector3D.UNIT_X;
import static Data.Vector3D.UNIT_Z;

public class OrthographicCamera extends Camera {
    private double cameraWidth, cameraHeight;

    public OrthographicCamera(Vector3D position, double phi, double cameraWidth, double cameraHeight) {
        super(position, phi);
        this.cameraWidth = cameraWidth;
        this.cameraHeight = cameraHeight;
    }

    @Override
    public BufferedImage renderImage(World world, int width, int height) {
        BufferedImage myImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        Vector3D myRayDirection = UNIT_X.rotateZ(phi);
        Vector3D myCameraRight = myRayDirection.cross(UNIT_Z);

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++) {
                Vector3D myRayPosition = position;
                myRayPosition = myRayPosition.add(myCameraRight.scale((((double)x/width) - 0.5) * cameraWidth));
                myRayPosition = myRayPosition.minus(UNIT_Z.scale((((double)y/height) - 0.5) * cameraHeight));

                Color myRayTracedColor = traceRay(myRayPosition, myRayDirection, world);
                myImage.setRGB(x, y ,myRayTracedColor.getRGB());
            }

            System.out.println((double)x/width*100.0 + "%");
        }

        return myImage;
    }
}
