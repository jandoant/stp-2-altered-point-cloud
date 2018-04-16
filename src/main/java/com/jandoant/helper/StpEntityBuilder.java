package com.jandoant.helper;

import com.jandoant.stp_entities.EntityTypes;
import com.jandoant.stp_entities.StpCartesianPoint;
import com.jandoant.stp_entities.StpDirection;
import com.jandoant.stp_entities.StpRepresentationItem;

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
            case EntityTypes.CARTESIAN_POINT :
                result = makeCartesianPoint();
                break;
            case EntityTypes.DIRECTION :
                result = makeDirection();
                break;
            default:
                return null;
        }

        return result;
    }

    private StpRepresentationItem makeDirection() {

        double xDirection = Double.parseDouble(this.argumentsList[1]);
        double yDirection = Double.parseDouble(this.argumentsList[2]);
        double zDirection = Double.parseDouble(this.argumentsList[3]);

        System.out.println(this.name);

        return new StpDirection(this.id, this.name, xDirection, yDirection, zDirection);
    }

    private StpCartesianPoint makeCartesianPoint() {

        double x = Double.parseDouble(this.argumentsList[1]);
        double y = Double.parseDouble(this.argumentsList[2]);
        double z = Double.parseDouble(this.argumentsList[3]);

        return new StpCartesianPoint(this.id, this.name, x, y, z);
    }


    private void extractArgumentsListFromDescription() {

        String arguments = this.partsOfDescription[2];

        this.argumentsList = arguments.split("[\\,\\(\\)\\;]+");

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
