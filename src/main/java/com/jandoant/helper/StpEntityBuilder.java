package com.jandoant.helper;

import com.jandoant.stp_entities.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Helps to build a StpEntity by splitting a String
 * Created by Jan Doant on 12.04.2018
 */
public class StpEntityBuilder {

    //Attribute
    String description;
    String[] partsOfDescription;

    int id;
    String name;
    String type;
    String[] argumentsList;

    //Konstruktor
    public StpEntityBuilder(String description) {
        this.description = description;
    }

    //Methoden
    public StpRepresentationItem extractStpEntity() {

        parseDescription();

        extractIdFromDescription();
        extractTypeFromDescription();
        extractArgumentsListFromDescription();
        extractNameFromDescription();

        return makeEntity();
    }

    private void extractNameFromDescription() {
        this.name = this.argumentsList[0].replace("'", "");
    }

    private StpRepresentationItem makeEntity() {

        StpRepresentationItem result;

        switch (this.type) {
            case EntityTypesContract.CARTESIAN_POINT:
                result = makeCartesianPoint();
                break;
            case EntityTypesContract.DIRECTION:
                result = makeDirection();
                break;
            case EntityTypesContract.AXIS2_PLACEMENT_3D:
                result = makeAxis2Placement3D();
                break;
            case EntityTypesContract.VECTOR:
                result = makeVector();
                break;
            case EntityTypesContract.LINE:
                result = makeLine();
                break;
            case EntityTypesContract.VERTEX_POINT:
                result = makeVertexPoint();
                break;
            case EntityTypesContract.EDGE_CURVE:
                result = makeEdgeCurve();
                break;
            case EntityTypesContract.ORIENTED_EDGE:
                result = makeOrientedEdge();
                break;
            case EntityTypesContract.EDGE_LOOP:
                result = makeEdgeLoop();
                break;
            case EntityTypesContract.PLANE:
                result = makePlane();
                break;
            case EntityTypesContract.CYLINDRICAL_SURFACE:
                result = makeCylindricalSurface();
                break;
            case EntityTypesContract.FACE_OUTER_BOUND:
                result = makeFaceOuterBound();
                break;
            default:
                return null;
        }

        return result;
    }

    private StpFaceOuterBound makeFaceOuterBound() {

        int boundId = Integer.parseInt(this.argumentsList[1]);
        Boolean orientation = Boolean.valueOf(this.argumentsList[2]);

        return new StpFaceOuterBound(this.id, this.name, boundId, orientation);
    }

    private StpCylindricalSurface makeCylindricalSurface() {

        int positionId = Integer.parseInt(this.argumentsList[1]);
        double radius = Double.parseDouble(this.argumentsList[2]);

        return new StpCylindricalSurface(this.id, this.name, positionId, radius);
    }

    private StpPlane makePlane() {
        int positionId = Integer.parseInt(this.argumentsList[1]);
        return new StpPlane(this.id, this.name, positionId);
    }

    private StpEdgeLoop makeEdgeLoop() {

        ArrayList<Integer> edgesIds = new ArrayList<>();

        for (int i = 1; i < argumentsList.length; i++) {
            edgesIds.add(Integer.valueOf(argumentsList[i]));
        }

        return new StpEdgeLoop(this.id, this.name, edgesIds);
    }

    private StpOrientedEdge makeOrientedEdge() {

        int edgeStartVertexId;
        int edgeEndVertexId;

        int edgeElementId = Integer.parseInt(this.argumentsList[3]);

        if (this.argumentsList[1].equals("*")) {
            edgeStartVertexId = edgeElementId;
        } else {
            edgeStartVertexId = Integer.parseInt(this.argumentsList[1]);
        }

        if (this.argumentsList[2].equals("*")) {
            edgeEndVertexId = edgeElementId;
        } else {
            edgeEndVertexId = Integer.parseInt(this.argumentsList[2]);
        }

        boolean orientation = Boolean.parseBoolean(this.argumentsList[4]);

        return new StpOrientedEdge(this.id, this.name, edgeStartVertexId, edgeEndVertexId, edgeElementId, orientation);
    }

    private StpEdgeCurve makeEdgeCurve() {

        int edgeStartVertexId = Integer.parseInt(argumentsList[1]);
        int edgeEndVertexId = Integer.parseInt(argumentsList[2]);
        int edgeGeometryId = Integer.parseInt(argumentsList[3]);
        boolean sameSense = Boolean.parseBoolean(argumentsList[4]);

        return new StpEdgeCurve(this.id, this.name, edgeStartVertexId, edgeEndVertexId, edgeGeometryId, sameSense);

    }

    private StpVertexPoint makeVertexPoint() {

        int vertexGeometryId = Integer.parseInt(argumentsList[1]);

        return new StpVertexPoint(this.id, this.name, vertexGeometryId);
    }

    private StpLine makeLine() {

        int startingPointId = Integer.parseInt(this.argumentsList[1]);
        int directionVectorId = Integer.parseInt(this.argumentsList[2]);

        return new StpLine(this.id, this.name, startingPointId, directionVectorId);
    }

    private StpVector makeVector() {

        int directionId = Integer.parseInt(this.argumentsList[1]);
        double magnitude = Double.parseDouble(this.argumentsList[2]);

        return new StpVector(this.id, this.name, directionId, magnitude);

    }

    private StpAxis2Placement3D makeAxis2Placement3D() {

        int locationId = Integer.parseInt(this.argumentsList[1]);
        int axisId = Integer.parseInt(this.argumentsList[2]);
        int refDirectionId = Integer.parseInt(this.argumentsList[3]);

        return new StpAxis2Placement3D(this.id, this.name, locationId, axisId, refDirectionId);
    }

    private StpDirection makeDirection() {

        double xDirection = Double.parseDouble(this.argumentsList[1]);
        double yDirection = Double.parseDouble(this.argumentsList[2]);
        double zDirection = Double.parseDouble(this.argumentsList[3]);

        return new StpDirection(this.id, this.name, xDirection, yDirection, zDirection);
    }

    private StpCartesianPoint makeCartesianPoint() {

        double x = Double.parseDouble(this.argumentsList[1]);
        double y = Double.parseDouble(this.argumentsList[2]);
        double z = Double.parseDouble(this.argumentsList[3]);

        return new StpCartesianPoint(this.id, this.name, x, y, z);
    }

    private void extractArgumentsListFromDescription() {

        String argumentsString = this.partsOfDescription[2];

        this.argumentsList = argumentsString.split("[\\,\\(\\)\\;]+");

        // change certain things about the arguments
        for (int i = 0; i < this.argumentsList.length; i++) {

            if (this.argumentsList[i].equals(".T.")) {
                this.argumentsList[i] = "true";
            }

            if (this.argumentsList[i].equals(".F.")) {
                this.argumentsList[i] = "false";
            }

            //if argument ends with '.', add a 0
            if (this.argumentsList[i].charAt(this.argumentsList[i].length() - 1) == '.') {
                this.argumentsList[i] += '0';
            }

            //remove hashtags from arguments
            this.argumentsList[i] = this.argumentsList[i].replace("#", "");
        }
    }

    private void extractTypeFromDescription() {
        this.type = this.partsOfDescription[1];
    }

    private void extractIdFromDescription() {
        String hashedID = this.partsOfDescription[0];
        this.id = Integer.parseInt(hashedID.split("#")[1]);
    }

    private void parseDescription() {
        this.partsOfDescription = this.description.split("[\\=\\(\\)\\;]+", 3);
    }

    public boolean describesEntity() {

        boolean describesEntity = true;

        if (this.description.charAt(0) != '#') {
            return false;
        }

        return describesEntity;
    }

    @Override
    public String toString() {
        return "StpEntityBuilder{" +
                "description='" + description + '\'' +
                ", partsOfDescription=" + Arrays.toString(partsOfDescription) +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", argumentsList=" + Arrays.toString(argumentsList) +
                ", name='" + name + '\'' +
                '}';
    }
}
