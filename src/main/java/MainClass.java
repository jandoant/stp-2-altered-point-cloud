import com.jandoant.builder.StpModelBuilder;
import com.jandoant.deformation.*;
import com.jandoant.geometric.CircleUVW;
import com.jandoant.geometric.CylinderRPhiW;
import com.jandoant.geometric.PolygonUVW;
import com.jandoant.stp_entities.StpAdvancedFace;
import com.jandoant.stp_entities.StpFaceBound;
import com.jandoant.stp_entities.StpPlane;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * Klasse MainClass
 * Created by Jan Doant on 25.04.2018
 */
public class MainClass {

    // Pfad zu den Beispieldateien
    public static final String PATH_TO_MAIN_RESOURCES = System.getProperty("user.dir") + "\\src\\main\\resources\\";

    public static void main(String[] args) {

        try {
            // 1. Laden der gewünschten Datei
            String stpFileName = "Quader.stp";
            StpModelBuilder modelBuilder = new StpModelBuilder(PATH_TO_MAIN_RESOURCES + stpFileName);

            // 2. Die STEP-Datei wird decodiert und in Java abgebildet
            ArrayList<StpAdvancedFace> advancedFaces = modelBuilder.parseFile();

            // 3. Jede ADVANCED_FACE wird diskretisiert
            for (StpAdvancedFace advancedFace : advancedFaces) {

                // Entscheiden ob es sich um eine zylindrische Fläche oder eine planare Fläche handelt
                switch (advancedFace.getType()) {
                    case "StpCylindricalSurface":

                        // 1.   Hier kann der Nutzer die Diskretisierungsparameter
                        //      für zylindrische Körperflächen wählen
                        int numOfRadialSegments = 4;
                        int numOfRings = 3;

                        // 2. Zylinder diskretisieren (in phi w r-Koordinaten)
                        CylinderRPhiW cylinder = new CylinderRPhiW(advancedFace);
                        cylinder.mesh(numOfRadialSegments, numOfRings);

                        // 3. Punkte der abgewickelten Mantelfläche zur Punktewolke hinzufügen
                        advancedFace.setLocalPointCloud(cylinder.getMeshPhiWR());

                        break;
                    case "StpPlane":

                        // 1. Auf welcher Ebene liegen alle diese Bounds?
                        StpPlane plane = (StpPlane) advancedFace.getFaceGeometry();

                        // 2. Finde die positiven Bounds
                        ArrayList<StpFaceBound> positiveBounds = advancedFace.getPositiveBounds();

                        // 3. Diskretisiere alle positiven Bounds entsprechend der Parameter des Nutzers und füge die Punkte zur PointCloud hinzu (uvw)
                        for (StpFaceBound positiveBound : positiveBounds) {

                            if (positiveBound.isPolygon()) {

                                // 1. Den Nutzer nach den Diskretisierungs-Parametern fragen
                                double distanceOfPoints = 5.0;

                                // 2. Die positve Polygonfläche in UVW transformieren und diskretisieren
                                PolygonUVW polygonUVW = new PolygonUVW(positiveBound, plane, true);
                                polygonUVW.mesh(distanceOfPoints);

                                // 3. Die erzeugten Mesh-Punkte(uvw) zur PointCloud der Advanced Face hinzufügen
                                advancedFace.addPositiveSurfaceUVW(polygonUVW);

                            } else if (positiveBound.isCircle()) {

                                // 1. Den Nutzer nach den Diskretisierungs-Parametern fragen
                                numOfRadialSegments = 4;
                                numOfRings = 3;

                                // 2. Die positve Kreisfläche in UVW transformieren und diskretisieren
                                CircleUVW circleUVW = new CircleUVW(positiveBound, plane, true);
                                circleUVW.mesh(numOfRadialSegments, numOfRings);

                                // 3. Die erzeugte Punktewolke(uvw) zur PointCloud der Advanced Face hinzufügen
                                advancedFace.addPositiveSurfaceUVW(circleUVW);

                            }

                        }

                        // 4. Finde die negativen Bounds jeder planaren ADVANCED_FACE
                        ArrayList<StpFaceBound> negativeBounds = advancedFace.getNegativeBounds();

                        // 5. Für jede negative Umrandung, ziehe die Punkte der PointCloud, die innerhalb dieser Umrandung liegen ab unf füge die Kantenpunkte der Umrandung hinzu
                        for (StpFaceBound negativeBound : negativeBounds) {

                            if (negativeBound.isPolygon()) {

                                // 1. Den Nutzer nach den MeshingParametern fragen (nur Kante wird gemesht)
                                double distanceOfPoints = 5.0;

                                // 2. Die negative Polygonfläche in UVW transformieren und die Kanten meshen
                                PolygonUVW polygonUVW = new PolygonUVW(negativeBound, plane, false);
                                polygonUVW.mesh(distanceOfPoints);

                                // 3. Alle Punkte innerhalb dieses Polygons von der Point Cloud der Advanced Face abziehen (Kanten bleiben erhalten)
                                advancedFace.removeNegativeSurfaceUVW(polygonUVW);

                            } else if (negativeBound.isCircle()) {

                                // 1. Den Nutzer nach den MeshingParametern fragen (nur die Kante wird gemesht)
                                numOfRadialSegments = 4;

                                // 2. Die positve Kreisfläche in UVW transformieren und meshen
                                CircleUVW circleUVW = new CircleUVW(negativeBound, plane, false);
                                circleUVW.mesh(numOfRadialSegments, 1);

                                // 3. Alle Punkte innerhalb dieses Kreises von der Point Cloud der Advanced Face abziehen (Kanten bleiben erhalten)
                                advancedFace.removeNegativeSurfaceUVW(circleUVW);

                            }

                        }

                }

            }

            // 4. Deformation der ersten drei Advanced Faces mit gewünschten Deformationsfunktionen
            advancedFaces.get(0).applyDeformationFunction(new DeformConstant(0.05));
            advancedFaces.get(0).applyDeformationFunction(new DeformLinear(0.3, 0.0, DeformationFunction.DIRECTION_V));
            advancedFaces.get(0).applyDeformationFunction(new DeformUniDirectionalSine(0.03, 2, 0.0, 0.0, DeformationFunction.DIRECTION_V));

            advancedFaces.get(1).applyDeformationFunction(new DeformBiLinear(3, 0.4, 0.5));
            advancedFaces.get(1).applyDeformationFunction(new DeformQuadratic(-4, 3, 0, DeformationFunction.DIRECTION_U));

            advancedFaces.get(2).applyDeformationFunction(new DeformBiQuadratic(3.3, -1.2, 4, -2.3, 1.0, 0.0));

            // 5. Ausgabe der verformten Geometrie als Text-Datei in xyz-Koordinaten
            SimpleDateFormat date = new SimpleDateFormat("YYYY_MM_dd-HHmmss-");
            String fileNamePrefix = date.format(new Date());

            String s = "";

            for (StpAdvancedFace advancedFace : advancedFaces) {
                s += advancedFace.print();
            }

            byte data[] = s.getBytes();
            Path p = Paths.get("./src/main/results/" + fileNamePrefix + stpFileName + ".txt");

            try (OutputStream out = new BufferedOutputStream(
                    Files.newOutputStream(p, CREATE, APPEND))) {
                out.write(data, 0, data.length);

                System.out.println(
                        "Ihre deformierten Koordinaten wurden erfolgreich in der Datei '" + fileNamePrefix + stpFileName + ".txt'" + " im Ordner " + "'./src/main/results/'" + " gespeichert.");

            } catch (IOException x) {
                System.err.println(x);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
