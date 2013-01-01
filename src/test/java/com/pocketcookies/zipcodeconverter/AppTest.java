package com.pocketcookies.zipcodeconverter;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.vividsolutions.jts.geom.Coordinate;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    public void testZipCode() throws Exception {
        assertEquals("20815", Iterables.getOnlyElement(
                App.getZipCodes(
                ImmutableList.of(new Coordinate(-77.06720, 39.00404)))
                .entrySet()).getValue());
    }
}
