package com.jandoant.geometric;

import com.jandoant.stp_entities.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CylinderRPhiWTest {

    private StpAdvancedFace createCircularAdvancedFace() {

        ArrayList<Integer> edgesIdsAF = new ArrayList<>();
        edgesIdsAF.add(18);
        edgesIdsAF.add(17);
        StpAdvancedFace advancedFace = new StpAdvancedFace(36, "", edgesIdsAF, 35, true);

        StpCartesianPoint locationSurface = new StpCartesianPoint(77, "Origin", 0, 0, 0);
        StpDirection axisSurface = new StpDirection(66, "center_axis", 0, 0, 1);
        StpDirection refDirectionSurface = new StpDirection(67, "ref_axis", -1, 0, 0);

        StpAxis2Placement3D positionSurface = new StpAxis2Placement3D(59, "", 77, 66, 67);
        positionSurface.setLocation(locationSurface);
        positionSurface.setAxis(axisSurface);
        positionSurface.setRefDirection(refDirectionSurface);

        StpCylindricalSurface cylindricalSurface = new StpCylindricalSurface(35, "", 59, 10);
        cylindricalSurface.setPosition(positionSurface);

        //OUTER BOUNDS
        StpCartesianPoint locationCircleOuter = new StpCartesianPoint(79, "Origin", 0, 0, 30);
        StpDirection axisCircleOuter = new StpDirection(68, "center_axis", 0, 0, 1);
        StpDirection refDirectionCircleOuter = new StpDirection(69, "ref_axis", -1, 0, 0);

        StpAxis2Placement3D positionCircleOuter = new StpAxis2Placement3D(60, "", 79, 68, 69);
        positionCircleOuter.setLocation(locationCircleOuter);
        positionCircleOuter.setAxis(axisCircleOuter);
        positionCircleOuter.setRefDirection(refDirectionCircleOuter);

        StpCircle circleOuter = new StpCircle(25, "", 60, 10);
        circleOuter.setPosition(positionCircleOuter);

        StpCartesianPoint cartesianPointOuter = new StpCartesianPoint(78, "", 10, 0, 30);
        StpVertexPoint edgeVertexOuter = new StpVertexPoint(27, "", 78);
        edgeVertexOuter.setVertexGeometry(cartesianPointOuter);

        StpEdgeCurve edgeCurveOuter = new StpEdgeCurve(29, "", 27, 27, 25, true);
        edgeCurveOuter.setEdgeStartVertex(edgeVertexOuter);
        edgeCurveOuter.setEdgeEndVertex(edgeVertexOuter);
        edgeCurveOuter.setEdgeGeometry(circleOuter);

        StpOrientedEdge orientedEdgeOuter = new StpOrientedEdge(31, "", 27, 27, 29, false);
        orientedEdgeOuter.setEdgeStartVertex(edgeVertexOuter);
        orientedEdgeOuter.setEdgeEndVertex(edgeVertexOuter);
        orientedEdgeOuter.setEdgeElement(edgeCurveOuter);

        ArrayList<Integer> edgesIdsOuterLoop = new ArrayList<>();
        edgesIdsOuterLoop.add(31);
        StpEdgeLoop edgeLoopOuter = new StpEdgeLoop(21, "", edgesIdsOuterLoop);
        ArrayList<StpOrientedEdge> edgesListOuter = new ArrayList<>();
        edgesListOuter.add(orientedEdgeOuter);
        edgeLoopOuter.setEdgesList(edgesListOuter);

        StpFaceOuterBound faceOuterBound = new StpFaceOuterBound(18, "", 21, true);
        faceOuterBound.setBound(edgeLoopOuter);

        // FACE BOUND
        StpCartesianPoint locationCircle = new StpCartesianPoint(81, "Origin", 0, 0, 0);
        StpDirection axisCircle = new StpDirection(70, "center_axis", 0, 0, -1);
        StpDirection refDirectionCircle = new StpDirection(71, "ref_axis", -1, 0, 0);

        StpAxis2Placement3D positionCircle = new StpAxis2Placement3D(61, "", 81, 70, 71);
        positionCircle.setLocation(locationCircle);
        positionCircle.setAxis(axisCircle);
        positionCircle.setRefDirection(refDirectionCircle);

        StpCircle circle = new StpCircle(26, "", 61, 10);
        circle.setPosition(positionCircle);

        StpCartesianPoint cartesianPoint = new StpCartesianPoint(80, "", 10, 0, 0);

        StpVertexPoint edgeVertex = new StpVertexPoint(28, "", 80);
        edgeVertex.setVertexGeometry(cartesianPoint);

        StpEdgeCurve edgeCurve = new StpEdgeCurve(30, "", 28, 28, 26, true);
        edgeCurve.setEdgeStartVertex(edgeVertex);
        edgeCurve.setEdgeEndVertex(edgeVertex);
        edgeCurve.setEdgeGeometry(circle);

        StpOrientedEdge orientedEdge = new StpOrientedEdge(32, "", 28, 28, 30, false);
        orientedEdge.setEdgeStartVertex(edgeVertex);
        orientedEdge.setEdgeEndVertex(edgeVertex);
        orientedEdge.setEdgeElement(edgeCurve);

        ArrayList<Integer> edgesIdsLoop = new ArrayList<>();
        edgesIdsLoop.add(32);
        StpEdgeLoop edgeLoop = new StpEdgeLoop(22, "", edgesIdsLoop);
        ArrayList<StpOrientedEdge> edgesList = new ArrayList<>();
        edgesList.add(orientedEdge);
        edgeLoop.setEdgesList(edgesList);

        StpFaceBound faceBound = new StpFaceBound(17, "", 22, true);
        faceBound.setBound(edgeLoop);

        ArrayList<StpFaceBound> bounds = new ArrayList<>();
        bounds.add(faceBound);
        bounds.add(faceOuterBound);

        advancedFace.setFaceGeometry(cylindricalSurface);
        advancedFace.setBounds(bounds);

        return advancedFace;
    }

    @Test
    void testMeshCylinder() {

        StpAdvancedFace advancedFace = createCircularAdvancedFace();

        CylinderRPhiW cylinderRPhiW = new CylinderRPhiW(advancedFace);

        cylinderRPhiW.mesh(4, 4);

        ArrayList<StpCartesianPoint> expectedMesh = new ArrayList<>();
        expectedMesh.add(new StpCartesianPoint(-1, "", 0.0, 0.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 90.0, 0.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 180.0, 0.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 270.0, 0.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 0.0, 10.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 90.0, 10.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 180.0, 10.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 270.0, 10.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 0.0, 20.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 90.0, 20.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 180.0, 20.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 270.0, 20.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 0.0, 30.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 90.0, 30.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 180.0, 30.0, 10.0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 270.0, 30.0, 10.0));

        assertEquals(expectedMesh, cylinderRPhiW.getMeshPhiWR());

    }
}