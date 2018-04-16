package com.jandoant.helper;

import com.jandoant.stp_entities.*;

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
    String type;
    String [] argumentsList;
    String name;


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

        switch(this.type){
            case EntityTypesContract.CARTESIAN_POINT :
                result = makeCartesianPoint();
                break;
            case EntityTypesContract.DIRECTION :
                result = makeDirection();
                break;
            case EntityTypesContract.AXIS2_PLACEMENT_3D :
                result = makeAxis2Placement3D();
                break;
            case EntityTypesContract.VECTOR :
                result = makeVector();
                break;
            case EntityTypesContract.LINE :
                result = makeLine();
                break;
            default:
                return null;
        }

        return result;
    }

    private StpRepresentationItem makeLine() {

        int startingPointId = Integer.parseInt(this.argumentsList[1]);
        int directionVectorId = Integer.parseInt(this.argumentsList[2]);

        return new StpLine(this.id, this.name, startingPointId, directionVectorId);
    }

    private StpRepresentationItem makeVector() {

        int directionId = Integer.parseInt(this.argumentsList[1]);
        double magnitude = Double.parseDouble(this.argumentsList[2]);

        return new StpVector(this.id, this.name, directionId, magnitude);

    }

    private StpRepresentationItem makeAxis2Placement3D() {

        int locationId = Integer.parseInt(this.argumentsList[1]);
        int axisId = Integer.parseInt(this.argumentsList[2]);
        int refDirectionId = Integer.parseInt(this.argumentsList[3]);

        return new StpAxis2Placement3D(this.id, this.name, locationId, axisId, refDirectionId);
    }

    private StpRepresentationItem makeDirection() {

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
        for (int i = 0; i < this.argumentsList.length ; i++) {

            if(this.argumentsList[i] == ".T."){
                this.argumentsList[i] = "true";
            }

            if(this.argumentsList[i] == ".F."){
                this.argumentsList[i] = "false";
            }

            //if argument ends with '.', add a 0
            if(this.argumentsList[i].charAt(this.argumentsList[i].length()-1)=='.'){
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
        this.partsOfDescription = this.description.split("[\\=\\(\\)\\;]+",3);
    }

    public boolean describesEntity(){

        boolean describesEntity = true;

        if(this.description.charAt(0)!='#'){
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
