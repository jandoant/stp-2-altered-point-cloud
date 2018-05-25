import com.jandoant.builder.StpModelBuilder;
import com.jandoant.stp_entities.StpAdvancedFace;
import com.jandoant.stp_entities.StpCartesianPoint;

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
            StpModelBuilder reader = new StpModelBuilder(PATH_TO_MAIN_RESOURCES + "Quader.stp");

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

                        /*  meshing and deforming any straight line polygon or circle lying on a Plane
                            the user can choose the meshing parameter
                            the user can choose the deformationfunctions and their parameters */

                        double radialSegments = 4;
                        double numOfRings = 3;

                        // 1. ask the user for the meshing parameters
                        double distanceOfPoints = 1.0;

                        // 2.1 find the outer edge points of the Polygon or the seam points of the circle in uvw-space
                        StpCartesianPoint[] outerEdgePointsUVW = advancedFace.getOuterEdgePointsUVW();

                        //3. mesh the outer 2D-Polygon and Fill the PointCloud of the Face
                        advancedFace.mesh2DPolygonUVW(outerEdgePointsUVW, distanceOfPoints);

                        break;
                    default:
                        //default code
                        break;
                }
            }

            //manipulate the single points of each advanced face

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
