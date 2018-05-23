import Jama.Matrix;
import com.jandoant.builder.StpModelBuilder;
import com.jandoant.stp_entities.StpAdvancedFace;

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

            //load the file
            StpModelBuilder reader = new StpModelBuilder(PATH_TO_MAIN_RESOURCES + "Zylinder.stp");
            //parse the file an make advancedFaces
            ArrayList<StpAdvancedFace> advancedFaces = reader.parseFile();
            //mesh each advanced face

            for (StpAdvancedFace advancedFace : advancedFaces) {
                switch (advancedFace.getType()) {
                    case "StpCylindricalSurface":
                        //ask for meshing parameters
                        int numOfRadialSegments = 17;
                        int numOfRings = 10;

                        //meshCylinder
                        advancedFace.meshCylinder(numOfRadialSegments, numOfRings);

                        break;
                    case "StpPlane":
                        //entfernung der Punkte voneinander festlegen
                        double distanceOfPoints = 0.1;

                        advancedFace.meshPolygon(distanceOfPoints);
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
