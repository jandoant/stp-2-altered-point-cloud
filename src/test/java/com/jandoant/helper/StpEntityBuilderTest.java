package com.jandoant.helper;

import com.jandoant.stp_entities.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StpEntityBuilderTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testWithValueSource(int argument) {
        assertTrue(argument > 0 && argument < 4);
    }

    @ParameterizedTest
    @DisplayName("should return true if String describes an entity")
    @ValueSource(strings = {
            "#31=ORIENTED_EDGE('',*,*,#27,.T.);",
            "#76=CARTESIAN_POINT('CARTPoint1',(0.,0.,30.));",
            "#66=DIRECTION('',(0.,0.,-1.));",
            "#58=AXIS2_PLACEMENT_3D('',#68,#61,#62);",
            "#36=ADVANCED_FACE('',(#13),#35,.T.);",
            "#35=PLANE('',#58);",
            "#34=ORIENTED_EDGE('',*,*,#30,.T.);",
            "#30=EDGE_CURVE('',#26,#23,#18,.T.);",
            "#26=VERTEX_POINT('',#74);",
            "#22=VECTOR('',#66,10.);",
            "#18=LINE('',#76,#22);",
            "#14=EDGE_LOOP('',(#31,#32,#33,#34));",
            "#13=FACE_OUTER_BOUND('',#14,.T.);"
    })
    void testDescribesEntityTrue(String argument) {
        //act
        StpEntityBuilder builder = new StpEntityBuilder(argument);
        //assert
        assertTrue(builder.describesEntity());
    }

    @ParameterizedTest
    @DisplayName("should return false if String does not describe an entity")
    @ValueSource(strings = {
            "FILE_SCHEMA (('CONFIG_CONTROL_DESIGN'));",
            "ISO-10303-21;",
            "HEADER;",
            "/* Generated by software containing ST-Developer",
            " * from STEP Tools, Inc. (www.steptools.com) ",
            " */",
            "FILE_SCHEMA (('CONFIG_CONTROL_DESIGN'));",
            "ENDSEC;",
            "DATA;"
    })
    void testDescribesEntityFalse(String testDescription) {
        //act
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);
        //assert
        assertFalse(builder.describesEntity());
    }

    @Test
    @DisplayName("Should instantiate a StpCartesianPoint if correct Descripton is given")
    void testCorrectCartesianPoint() {
        //SetUp
        String testDescription = "#76=CARTESIAN_POINT('CARTPoint1',(0.,0.,30.));";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);
        //expectation
        StpCartesianPoint expected = new StpCartesianPoint(
                76,
                "CARTPoint1",
                0.0,
                0.0,
                30.0);
        //Act
        StpCartesianPoint actual = (StpCartesianPoint) builder.extractStpEntity();
        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate another StpCartesianPoint if correct Descripton is given")
    void testAnotherCorrectCartesianPoint() {

        //SetUp
        String testDescription = "#67=CARTESIAN_POINT('',(0.3,0.,15.));";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        StpCartesianPoint expected = new StpCartesianPoint(
                67,
                "",
                0.3,
                0.0,
                15.0);

        //Act
        StpCartesianPoint actual = (StpCartesianPoint) builder.extractStpEntity();

        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate a StpDirection if correct Descripton is given")
    void testCorrectDirection() {

        //SetUp
        String testDescription = "#66=DIRECTION('',(0.,0.,-1.));";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        StpDirection expected = new StpDirection(
                66,
                "",
                0.0,
                0.0,
                -1.0);

        //Act
        StpDirection actual = (StpDirection) builder.extractStpEntity();

        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("should instantiate Axis2Placement3D if correct description is given")
    void correctAxis2Placement3D() {

        //SetUp
        String testDescription = "#58=AXIS2_PLACEMENT_3D('',#68,#61,#62);";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        StpAxis2Placement3D expected = new StpAxis2Placement3D(
                58,
                "",
                68,
                61,
                62);

        //Act
        StpAxis2Placement3D actual = (StpAxis2Placement3D) builder.extractStpEntity();

        //assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate a StpVector if correct Descripton is given")
    void testCorrectVector() {

        //SetUp
        String testDescription = "#22=VECTOR('',#66,10.);";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        StpVector expected = new StpVector(
                22,
                "",
                66,
                10.0);

        //Act
        StpVector actual = (StpVector) builder.extractStpEntity();

        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate a StpLine if correct Descripton is given")
    void testCorrectLine() {

        //SetUp
        String testDescription = "#18=LINE('',#76,#22);";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        StpLine expected = new StpLine(
                18,
                "",
                76,
                22);

        //Act
        StpLine actual = (StpLine) builder.extractStpEntity();

        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate a StpVertexPoint if correct Descripton is given")
    void testCorrectVertexPoint() {

        //SetUp
        String testDescription = "#26=VERTEX_POINT('',#74);";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        StpVertexPoint expected = new StpVertexPoint(
                26,
                "",
                74);

        //Act
        StpVertexPoint actual = (StpVertexPoint) builder.extractStpEntity();

        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate a StpEdgeCurve if correct Descripton is given")
    void testCorrectEdgeCurve() {

        //SetUp
        String testDescription = "#27=EDGE_CURVE('',#23,#24,#15,.T.);";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        StpEdgeCurve expected = new StpEdgeCurve(
                27,
                "",
                23,
                24,
                15,
                true);

        //Act
        StpEdgeCurve actual = (StpEdgeCurve) builder.extractStpEntity();

        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate another StpEdgeCurve if correct Descripton is given")
    void testCorrectEdgeCurve2() {

        //SetUp
        String testDescription = "#27=EDGE_CURVE('',#23,#24,#15,.F.);";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        StpEdgeCurve expected = new StpEdgeCurve(
                27,
                "",
                23,
                24,
                15,
                false);

        //Act
        StpEdgeCurve actual = (StpEdgeCurve) builder.extractStpEntity();

        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate a StpOrientedEdge if correct Descripton is given")
    void testCorrectOrientedEdge() {

        //SetUp
        String testDescription = "#31=ORIENTED_EDGE('',#23,#24,#27,.T.);";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        StpOrientedEdge expected = new StpOrientedEdge(31, "", 23, 24, 27, true);
        //Act
        StpOrientedEdge actual = (StpOrientedEdge) builder.extractStpEntity();
        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate another StpOrientedEdge if correct Descripton is given")
    void testCorrectOrientedEdge2() {

        //SetUp
        String testDescription = "#31=ORIENTED_EDGE('',#23,#24,#27,.F.);";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        StpOrientedEdge expected = new StpOrientedEdge(31, "", 23, 24, 27, false);
        //Act
        StpOrientedEdge actual = (StpOrientedEdge) builder.extractStpEntity();
        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate another StpOrientedEdge if correct Descripton is given")
    void testCorrectOrientedEdge3() {

        //SetUp
        String testDescription = "#31=ORIENTED_EDGE('',*,#24,#27,.F.);";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        StpOrientedEdge expected = new StpOrientedEdge(31, "", 27, 24, 27, false);
        //Act
        StpOrientedEdge actual = (StpOrientedEdge) builder.extractStpEntity();
        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate another StpOrientedEdge if correct Descripton is given")
    void testCorrectOrientedEdge4() {

        //SetUp
        String testDescription = "#31=ORIENTED_EDGE('',#23,*,#27,.F.);";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        StpOrientedEdge expected = new StpOrientedEdge(31, "", 23, 27, 27, false);
        //Act
        StpOrientedEdge actual = (StpOrientedEdge) builder.extractStpEntity();
        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate another StpOrientedEdge if correct Descripton is given")
    void testCorrectOrientedEdge5() {

        //SetUp
        String testDescription = "#31=ORIENTED_EDGE('',*,*,#27,.F.);";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        StpOrientedEdge expected = new StpOrientedEdge(31, "", 27, 27, 27, false);
        //Act
        StpOrientedEdge actual = (StpOrientedEdge) builder.extractStpEntity();
        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate StpEdgeLoop if correct Descripton is given")
    void testCorrectEdgeLoop() {

        //SetUp
        String testDescription = "#14=EDGE_LOOP('',(#31,#32,#33,#34));";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.add(31);
        edgesIds.add(32);
        edgesIds.add(33);
        edgesIds.add(34);
        StpEdgeLoop expected = new StpEdgeLoop(14, "", edgesIds);
        //Act
        StpEdgeLoop actual = (StpEdgeLoop) builder.extractStpEntity();
        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate another StpEdgeLoop if correct Descripton is given")
    void testCorrectEdgeLoop2() {

        //SetUp
        String testDescription = "#14=EDGE_LOOP('',(#31,#32,#33,#34,#35,#36));";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.add(31);
        edgesIds.add(32);
        edgesIds.add(33);
        edgesIds.add(34);
        edgesIds.add(35);
        edgesIds.add(36);
        StpEdgeLoop expected = new StpEdgeLoop(14, "", edgesIds);
        //Act
        StpEdgeLoop actual = (StpEdgeLoop) builder.extractStpEntity();
        //Assert
        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("Should instantiate a StpPlane if correct Descripton is given")
    void testCorrectPlane() {

        //SetUp
        String testDescription = "#35=PLANE('',#58);";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        StpPlane expected = new StpPlane(35, "", 58);
        //Act
        StpPlane actual = (StpPlane) builder.extractStpEntity();
        //Assert
        assertTrue(actual.equals(expected));
    }



    @Test
    @DisplayName("Should instantiate a StpCylindricalSurface if correct Descripton is given")
    void testCorrectCylindricalSurface() {

        //SetUp
        String testDescription = "#35=CYLINDRICAL_SURFACE('',#59,5.);";
        StpEntityBuilder builder = new StpEntityBuilder(testDescription);

        //expectation
        StpCylindricalSurface expected = new StpCylindricalSurface(35, "", 59,5.0);

        //Act
        StpCylindricalSurface actual = (StpCylindricalSurface) builder.extractStpEntity();

        //Assert
        assertTrue(actual.equals(expected));
    }


}