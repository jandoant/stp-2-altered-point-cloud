package com.jandoant.builder;

import com.jandoant.stp_entities.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.jandoant.PathContract.PATH_TO_TEST_RESOURCES;
import static org.junit.jupiter.api.Assertions.*;

class StpModelBuilderTest {

    @Test
    @DisplayName("should return a non Null Object if a correct Path to a stp file is given")
    void testConstructor() {

        StpModelBuilder stpModelBuilder = null;

        try {
            stpModelBuilder = new StpModelBuilder(PATH_TO_TEST_RESOURCES + "testQuader.stp");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertNotNull(stpModelBuilder);
    }

    @Test
    @DisplayName("should have an empty entityStringList if the STP-File is empty")
    void testEmptyStpFile1() {

        StpModelBuilder stpModelBuilder = null;

        try {
            stpModelBuilder = new StpModelBuilder(PATH_TO_TEST_RESOURCES + "empty.stp");
            stpModelBuilder.parseFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(stpModelBuilder.getEntityStringList().equals(new ArrayList<>()));

    }

    @Test
    @DisplayName("should have an empty entityList if the STP-File is empty")
    void testEmptyStpFile2() {

        StpModelBuilder stpModelBuilder = null;

        try {
            stpModelBuilder = new StpModelBuilder(PATH_TO_TEST_RESOURCES + "empty.stp");
            stpModelBuilder.parseFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(stpModelBuilder.getEntityList().equals(new ArrayList<>()));
    }

    @Test
    @DisplayName("should have an empty entityStringList if the STP-File is empty")
    void testEmptyStpFileSetEntityStringList() {

        StpModelBuilder stpModelBuilder = null;

        try {
            stpModelBuilder = new StpModelBuilder(PATH_TO_TEST_RESOURCES + "empty.stp");
            stpModelBuilder.parseFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(stpModelBuilder.getEntityStringList().equals(new ArrayList<>()));
    }

    @Test
    @DisplayName("should write only lines that start with a '#' and end with a ';' in EntityStringList")
    void testMakeEntityStringList1() {

        StpModelBuilder stpModelBuilder = null;

        try {
            stpModelBuilder = new StpModelBuilder(PATH_TO_TEST_RESOURCES + "testMakeEntityStringList1.stp");
            stpModelBuilder.parseFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //expectation
        ArrayList<String> expectedEntityStringList = new ArrayList<>();
        expectedEntityStringList.add("#10=MECHANICAL_DESIGN_GEOMETRIC_PRESENTATION_REPRESENTATION('',(#13),#189);");
        expectedEntityStringList.add("#11=SHAPE_REPRESENTATION_RELATIONSHIP('SRR','None',#196,#12);");
        expectedEntityStringList.add("#12=ADVANCED_BREP_SHAPE_REPRESENTATION('',(#14),#188);");
        expectedEntityStringList.add("#13=STYLED_ITEM('',(#205),#14);");
        expectedEntityStringList.add("#14=MANIFOLD_SOLID_BREP('Volumenk\\X\\F6rper1',#107);");

        //assert
        ArrayList<String> actualEntityStringList = stpModelBuilder.getEntityStringList();
        assertEquals(expectedEntityStringList, actualEntityStringList);
    }

    @Test
    @DisplayName("should write only lines that end with a ';' in EntityStringList")
    void testMakeEntityStringList2() {

        StpModelBuilder stpModelBuilder = null;

        try {
            stpModelBuilder = new StpModelBuilder(PATH_TO_TEST_RESOURCES + "testMakeEntityStringList2.stp");
            stpModelBuilder.parseFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> expectedEntityStringList = new ArrayList<>();

        expectedEntityStringList.add("#10=MECHANICAL_DESIGN_GEOMETRIC_PRESENTATION_REPRESENTATION('',(#13),#189);");
        expectedEntityStringList.add("#11=SHAPE_REPRESENTATION_RELATIONSHIP('SRR','None',#196,#12);");
        expectedEntityStringList.add("#110=(MASS_UNIT()NAMED_UNIT(*)SI_UNIT($,.GRAM.));");
        expectedEntityStringList.add("#112=MEASURE_REPRESENTATION_ITEM('density measure',POSITIVE_RATIO_MEASURE(1.),#111);");
        expectedEntityStringList.add("#201=APPLICATION_PROTOCOL_DEFINITION('international standard','automotive_design',2009,#202);");
        expectedEntityStringList.add("#202=APPLICATION_CONTEXT('Core Data for Automotive Mechanical Design Process');");

        //assert
        ArrayList<String> actualEntityStringList = stpModelBuilder.getEntityStringList();
        assertEquals(expectedEntityStringList, actualEntityStringList);
    }

    @Test
    @DisplayName("should assemble an ArrayList<StpRepresentationItem> from given Stp-File")
    void testMakeEntityList1() {

        StpModelBuilder stpModelBuilder = null;

        ArrayList<String> entityStringList = new ArrayList<>();
        entityStringList.add("#17=FACE_OUTER_BOUND('',#23,.T.);");
        entityStringList.add("#26=EDGE_LOOP('',(#91,#92,#93,#94));");
        entityStringList.add("#34=LINE('',#177,#46);");
        entityStringList.add("#44=VECTOR('',#144,10.);");
        entityStringList.add("#54=VERTEX_POINT('',#166);");
        entityStringList.add("#59=EDGE_CURVE('',#51,#52,#27,.T.);");
        entityStringList.add("#73=ORIENTED_EDGE('',*,*,#61,.F.);");

        try {
            stpModelBuilder = new StpModelBuilder(PATH_TO_TEST_RESOURCES + "empty.stp");
            stpModelBuilder.parseFile();
            //because a private method is tested, parseFile() is called twice
            stpModelBuilder.setEntityStringList(entityStringList);
            stpModelBuilder.parseFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //expectation
        ArrayList<StpRepresentationItem> expectedEntityList = new ArrayList<>();

        expectedEntityList.add(new StpFaceOuterBound(17, "", 23, true));
        Integer[] edgesIdsArr = {91, 92, 93, 94};
        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.addAll(Arrays.asList(edgesIdsArr));
        expectedEntityList.add(new StpEdgeLoop(26, "", edgesIds));
        expectedEntityList.add(new StpLine(34, "", 177, 46));
        expectedEntityList.add(new StpVector(44, "", 144, 10.0));
        expectedEntityList.add(new StpVertexPoint(54, "", 166));
        expectedEntityList.add(new StpEdgeCurve(59, "", 51, 52, 27, true));
        expectedEntityList.add(new StpOrientedEdge(73, "", 61, 61, 61, false));

        //assert
        ArrayList<StpRepresentationItem> actualEntityList = stpModelBuilder.getEntityList();
        assertTrue(actualEntityList.equals(expectedEntityList));

    }

    @Test
    @DisplayName("should assemble an ArrayList<StpRepresentationItem> from given Stp-File and ignore all other lines")
    void testMakeEntityList2() {

        StpModelBuilder stpModelBuilder = null;

        ArrayList<String> entityStringList = new ArrayList<>();
        entityStringList.add("#10=MECHANICAL_DESIGN_GEOMETRIC_PRESENTATION_REPRESENTATION('',(#13),#189);");
        entityStringList.add("#110=(MASS_UNIT()NAMED_UNIT(*)SI_UNIT($,.GRAM.));");
        entityStringList.add("#17=FACE_OUTER_BOUND('',#23,.T.);");
        entityStringList.add("#26=EDGE_LOOP('',(#91,#92,#93,#94));");
        entityStringList.add("#34=LINE('',#177,#46);");
        entityStringList.add("#44=VECTOR('',#144,10.);");
        entityStringList.add("#54=VERTEX_POINT('',#166);");
        entityStringList.add("#59=EDGE_CURVE('',#51,#52,#27,.T.);");
        entityStringList.add("#73=ORIENTED_EDGE('',*,*,#61,.F.);");

        try {
            stpModelBuilder = new StpModelBuilder(PATH_TO_TEST_RESOURCES + "empty.stp");
            stpModelBuilder.parseFile();
            //because a private method is tested, parseFile() is called twice
            stpModelBuilder.setEntityStringList(entityStringList);
            stpModelBuilder.parseFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //expectation
        ArrayList<StpRepresentationItem> expectedEntityList = new ArrayList<>();

        expectedEntityList.add(new StpFaceOuterBound(17, "", 23, true));
        Integer[] edgesIdsArr = {91, 92, 93, 94};
        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.addAll(Arrays.asList(edgesIdsArr));
        expectedEntityList.add(new StpEdgeLoop(26, "", edgesIds));
        expectedEntityList.add(new StpLine(34, "", 177, 46));
        expectedEntityList.add(new StpVector(44, "", 144, 10.0));
        expectedEntityList.add(new StpVertexPoint(54, "", 166));
        expectedEntityList.add(new StpEdgeCurve(59, "", 51, 52, 27, true));
        expectedEntityList.add(new StpOrientedEdge(73, "", 61, 61, 61, false));

        //assert
        ArrayList<StpRepresentationItem> actualEntityList = stpModelBuilder.getEntityList();
        assertTrue(actualEntityList.equals(expectedEntityList));

    }

}