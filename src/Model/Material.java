package Model;

import Data.Vector3D;

public class Material {
    private Vector3D ambientCoefficient;
    private Vector3D diffuseCoefficient;
    private Vector3D specularCoefficient;
    private double phongCoefficient;

    public Material(Vector3D ambientCoefficient, Vector3D diffuseCoefficient, Vector3D specularCoefficient, double phongCoefficient) {
        this.ambientCoefficient = ambientCoefficient;
        this.diffuseCoefficient = diffuseCoefficient;
        this.specularCoefficient = specularCoefficient;
        this.phongCoefficient = phongCoefficient;
    }

    public Vector3D getAmbientCoefficient() {
        return ambientCoefficient;
    }

    public Vector3D getDiffuseCoefficient() {
        return diffuseCoefficient;
    }

    public Vector3D getSpecularCoefficient() {
        return specularCoefficient;
    }

    public double getPhongCoefficient() {
        return phongCoefficient;
    }
}
