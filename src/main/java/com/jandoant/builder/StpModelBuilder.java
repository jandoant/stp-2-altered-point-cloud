package com.jandoant.builder;

import com.jandoant.stp_entities.StpAdvancedFace;
import com.jandoant.stp_entities.StpRepresentationItem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Klasse StpModelBuilder
 * Created by Jan Doant on 26.04.2018
 */
public class StpModelBuilder {

    //Attribute

    ArrayList<StpAdvancedFace> advancedFaces;
    String filePath;
    FileReader fileReader;
    BufferedReader bufferedReader;
    private ArrayList<String> entityStringList;
    private ArrayList<StpRepresentationItem> entityList;

    //Konstruktor
    public StpModelBuilder(String filePath) throws FileNotFoundException {

        this.filePath = filePath;
        this.fileReader = new FileReader(this.filePath);
        this.bufferedReader = new BufferedReader(this.fileReader);

        this.entityStringList = new ArrayList<>();
        this.entityList = new ArrayList<>();
        this.advancedFaces = new ArrayList<>();
    }

    public ArrayList<StpAdvancedFace> parseFile() throws IOException {

        //make a List of Strings that could describe an entity
        makeListOfEntityStrings();

        //make a list of actual Entities ("unimportant" Entities get filtered out)
        makeListOfEntities();

        //combine the Entities via their ids
        convertEntitiesFromIds();

        //write all advancedFaces into a List
        listAdvancesFaces();

        return this.advancedFaces;
    }

    private void listAdvancesFaces() {
        for (int i = 0; i < entityList.size(); i++) {

            StpRepresentationItem item = entityList.get(i);

            if (item.getClass().getSimpleName().equals("StpAdvancedFace")) {

                advancedFaces.add((StpAdvancedFace) item);

            }
        }
    }

    private void convertEntitiesFromIds() {

        for (int i = 0; i < entityList.size(); i++) {
            entityList.get(i).convertFromIds(entityList);
        }
    }

    private void makeListOfEntities() {

        StpEntityBuilder entityBuilder;

        for (String entityString : this.entityStringList) {
            entityBuilder = new StpEntityBuilder(entityString);
            StpRepresentationItem entity = entityBuilder.extractStpEntity();

            if (entity != null) {
                entityList.add(entity);
            }
        }

    }

    private void makeListOfEntityStrings() throws IOException {

        String line;
        String currentEntityString = "";

        if (bufferedReader.ready()) {

            while ((line = this.bufferedReader.readLine()) != null) {

                currentEntityString += line;

                if (!currentEntityString.endsWith(";")) {
                    continue;
                }

                if (currentEntityString.startsWith("#") && currentEntityString.endsWith(";")) {
                    entityStringList.add(currentEntityString);
                }

                currentEntityString = "";
            }

            this.bufferedReader.close();
        }
    }

    //Methoden
    public ArrayList<String> getEntityStringList() {
        return entityStringList;
    }

    public void setEntityStringList(ArrayList<String> entityStringList) {
        this.entityStringList = entityStringList;
    }

    public ArrayList<StpRepresentationItem> getEntityList() {
        return entityList;
    }
}
