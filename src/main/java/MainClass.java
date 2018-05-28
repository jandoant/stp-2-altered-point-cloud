import com.jandoant.builder.StpModelBuilder;
import com.jandoant.geometric.PolygonUVW;
import com.jandoant.geometric.SurfaceUVW;
import com.jandoant.stp_entities.StpAdvancedFace;
import com.jandoant.stp_entities.StpFaceBound;
import com.jandoant.stp_entities.StpPlane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Klasse MainClass
 * Created by Jan Doant on 25.04.2018
 */
public class MainClass {

    public static final String PATH_TO_MAIN_RESOURCES = "C:\\Users\\Jan\\Google Drive\\stp-2-altered-point-cloud\\src\\main\\resources\\";

    //Konstruktor

    //Methoden
    public static void main(String[] args) {

        //Attribute
        try {

            // 1. load the file
            StpModelBuilder reader = new StpModelBuilder(PATH_TO_MAIN_RESOURCES + "Zylinder.stp");

            // 2. parse the file an make advancedFaces
            ArrayList<StpAdvancedFace> advancedFaces = reader.parseFile();

            // 3. mesh and deform each advanced face
            for (StpAdvancedFace advancedFace : advancedFaces) {
                switch (advancedFace.getType()) {
                    case "StpCylindricalSurface":

                        /*  meshing and deforming any cylindrical surface
                            the user can choose the meshing parameters
                            the user can choose the deformation Functions and their parameters */

                        // 1. ask the user for meshing parameters
                        int numOfRadialSegments = 4;
                        int numOfRings = 3;

                        //meshCylinder
                        advancedFace.meshCylinder(numOfRadialSegments, numOfRings);

                        break;
                    case "StpPlane":


                        System.out.println(advancedFace);

                        // 1. Finde die positiven Bounds
                        ArrayList<StpFaceBound> positiveBounds = advancedFace.getPositiveBounds();

                        // 2. Meshe alle positiven Bounds und füge die Punkte zur PointCloud hinzu (uvw)
                        StpPlane plane = (StpPlane) advancedFace.getFaceGeometry();

                        for (StpFaceBound positiveBound : positiveBounds) {

                            if (positiveBound.isPolygon()) {

                                // Den Nutzer nach den MeshingParametern fragen (im Moment nicht implementiert)
                                double distanceOfPoints = 5.0;

                                // Die positve Fläche in UVW transformieren und meshen
                                PolygonUVW polygonUVW = new PolygonUVW(positiveBound, plane, true);
                                polygonUVW.mesh(distanceOfPoints);

                                // Die erzeugten Mesh-Punkte zur PointCloud der Advanced Face hinzufügen
                                advancedFace.addPositiveSurfaceUVW(polygonUVW);

                            } else if (positiveBound.isCircle()) {
                            /*CircleUVW circleUVW = new CircleUVW(positiveBound, plane, isPositive);
                            circleUVW.mesh(numOfRadialSegments, numOfRings);

                            advancedFace.addToPointCloud(circleUVW.getMeshUVW());*/
                            }

                        }

                        //3. Die folgenden bounds in der Liste sind die negativen und müssen abgezogen werden

                }

                //manipulate the single points of each advanced face with deformation function

            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
