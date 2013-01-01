package com.pocketcookies.zipcodeconverter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.Properties;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.opengis.feature.simple.SimpleFeature;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Coordinate;
import java.io.BufferedReader;

/**
 * Hello world!
 *
 */
public class App {

    private static String getShapefileName(String id) {
        return "zt" + id + "_d00.shp";
    }

    private static String getShapefilePath(String id) {
        return "/zipcodes/" + getShapefileName(id);
    }

    public static void main(String[] args) throws IOException {
        Properties shapefiles = new Properties();
        shapefiles.load(App.class.getResourceAsStream("/zipcodes/shapefiles.txt"));

        BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = systemIn.readLine()) != null) {
            double x = Double.parseDouble(line.split("[, ]")[0]);
            double y = Double.parseDouble(line.split("[, ]")[1]);
            for (Entry<Object, Object> shapefileEntry : shapefiles.entrySet()) {
                ShapefileDataStore shapefile = new ShapefileDataStore(App.class.getResource(getShapefilePath((String) shapefileEntry.getValue())));
                // Check the bounds of this entire shapefile to see if we can skip it.
                if (shapefile.getFeatureSource().getBounds().contains(x, y)) {
                    SimpleFeatureIterator featureIterator = shapefile.getFeatureSource().getFeatures().features();
                    while (featureIterator.hasNext()) {
                        SimpleFeature feature = featureIterator.next();
                        if (((MultiPolygon) feature.getDefaultGeometry()).contains(new GeometryFactory().createPoint(new Coordinate(x, y)))) {
                            System.out.println("Found in " + feature.getAttribute("NAME"));
                        }
                    }
                }
            }
        }
    }
}
