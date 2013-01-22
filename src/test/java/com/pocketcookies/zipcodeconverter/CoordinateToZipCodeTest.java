package com.pocketcookies.zipcodeconverter;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.vividsolutions.jts.geom.Coordinate;
import java.io.IOException;
import junit.framework.TestCase;

/**
 * Tests for {@link CoordinateToZipCode}.
 * 
 * @author john.a.edmonds@gmail.com (John "Jack" Edmonds)
 */
public class CoordinateToZipCodeTest extends TestCase {

    public void testZipCode() throws Exception {
        assertZipCode("20815", new Coordinate(-77.06720, 39.00404));
        assertZipCode("20032", new Coordinate(-76.9880718769312, 38.8324247044461));
        assertZipCode("20812", new Coordinate(-77.1435801669312, 38.9699880244461));
        assertEquals(
                ImmutableSet.of("20815", "20032", "20812"),
                ImmutableSet.copyOf(
                CoordinateToZipCode.getZipCodes(ImmutableSet.of(
                new Coordinate(-77.1435801669312, 38.9699880244461),
                new Coordinate(-76.9880718769312, 38.8324247044461),
                new Coordinate(-77.06720, 39.00404))).values()));
    }
    
    public void testMissingZipCode() throws Exception {
        assertEquals(ImmutableMap.of(), CoordinateToZipCode.getZipCodes(ImmutableSet.of(new Coordinate(37.71859, -39.023437))));
    }
    
    private static void assertZipCode(String zipCode, Coordinate coordinate) throws IOException {
        assertEquals(zipCode, Iterables.getOnlyElement(CoordinateToZipCode.getZipCodes(ImmutableSet.of(coordinate)).entrySet()).getValue());
    }
}
