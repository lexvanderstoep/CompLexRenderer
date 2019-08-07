import Data.Hit;
import Data.Vector3D;
import Model.Material;
import Model.PointLight;
import Model.World;
import Model.WorldObject;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Data.Vector3D.UNIT_X;

public abstract class Camera {
    private static double EPS = 1E-5;

    protected Color backgroundColor;
    protected Vector3D position;
    protected double phi; // radians
    protected int maxReflectionDepth;

    public Camera(Color backgroundColor, Vector3D position, double phi, int maxReflectionDepth) {
        this.backgroundColor = backgroundColor;
        this.position = position;
        this.phi = phi;
        this.maxReflectionDepth = maxReflectionDepth;
    }

    public Vector3D getViewDirection() {
        return UNIT_X.rotateZ(phi);
    }

    protected Color traceRay(Vector3D origin, Vector3D direction, World world, int reflectionDepth) {
        Hit hit = findClosestHit(origin, direction, world);

        if (hit == null) {
            return backgroundColor;
        }

        Material hitMaterial = hit.getHitObject().getMaterial();
        Vector3D N = hit.getHitNormal();
        Vector3D V = origin.minus(hit.getHitLocation()).normalise();

        // Ambient colour.
        Color ambientColor = scaleColor(
                world.getAmbientLightning(),
                hitMaterial.getAmbientCoefficient());

        // Compute point-light diffusion and specular colour.
        Color diffusionColor = Color.BLACK;
        Color specularColor = Color.BLACK;
        for (PointLight light : world.getLights()) {
            // Check if there are no objects in the way.
            Vector3D L = light.getPosition().minus(hit.getHitLocation()).normalise();
            Hit lightHit = findClosestHit(hit.getHitLocation().add(L.scale(EPS)), L, world);
            if (lightHit != null) {
                continue; // There is an object in the way to the light.
            }

            Vector3D minusL = L.scale(-1.0);
            Vector3D R = minusL.minus(N.scale(2.0 * minusL.dot(N))).normalise();
            double RdotV = Math.max(0, R.dot(V));

            double diffusionCoefficient = Math.max(0, L.dot(N));
            double specularCoefficient = Math.pow(RdotV, hitMaterial.getPhongCoefficient());

            // Diffusion lightning.
            //  I_diff = I_p * K_d * (N . L)
            diffusionColor = addColors(
                    diffusionColor,
                    scaleColor(
                            scaleColor(light.getColor(), hitMaterial.getDiffuseCoefficient()),
                            diffusionCoefficient));

            // Specular lightning.
            // I_spec = I_p * K_s * (R . V)^7
            specularColor = addColors(
                    specularColor,
                    scaleColor(
                            scaleColor(light.getColor(), hitMaterial.getSpecularCoefficient()),
                            specularCoefficient));
        }

        Color myColor = addColors(addColors(ambientColor, specularColor), diffusionColor);

        if (reflectionDepth < maxReflectionDepth) {
            Vector3D minusV = V.scale(-1.0);
            Vector3D rV = minusV.minus(N.scale(2.0 * minusV.dot(N))).normalise();
            Color reflectionColor = traceRay(hit.getHitLocation().add(rV.scale(EPS)), rV,
                    world, reflectionDepth + 1);
            myColor = addColors(myColor, scaleColor(reflectionColor, hitMaterial.getReflectionCoefficient()));
        }

        return myColor;
    }

    private Color addColors(Color one, Color two) {
        int r = Math.min(one.getRed() + two.getRed(), 255);
        int g = Math.min(one.getGreen() + two.getGreen(), 255);
        int b = Math.min(one.getBlue() + two.getBlue(), 255);
        int a = Math.min(one.getAlpha() + two.getAlpha(), 255);
        return new Color(r, g, b, a);
    }

    private Color scaleColor(Color one, Vector3D factor) {
        return new Color(
                (int)(one.getRed() * factor.x()),
                (int)(one.getGreen() * factor.y()),
                (int)(one.getBlue() * factor.z()),
                one.getAlpha());
    }

    private Color scaleColor(Color one, double factor) {
        return new Color(
                (int)(one.getRed() * factor),
                (int)(one.getGreen() * factor),
                (int)(one.getBlue() * factor),
                one.getAlpha());
    }

    private Hit findClosestHit(Vector3D origin, Vector3D direction, World world) {
        Hit closestHit = null;

        // Check for all objects in the world, whether the ray hits them.
        // Return the closest.
        for (WorldObject object : world.getObjects()) {
            Hit myRayTraceHit = object.computeHit(origin, direction);
            if (myRayTraceHit != null) {
                if (closestHit == null || myRayTraceHit.getDistanceFromOrigin() < closestHit.getDistanceFromOrigin()) {
                    closestHit = myRayTraceHit;
                }
            }
        }

        return closestHit;
    }

    public abstract BufferedImage renderImage(World world, int width, int height);
}