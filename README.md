# Introduction

This project provides an API to convert coordinates to U.S. zip codes. The data for zip codes boundaries comes from The United States Census Bureau (http://www.census.gov/geo/www/cob/z52000.html).

# Usage

Simply provide a collection of coordinates and you'll get back a mapping of those given coordinates to the zip codes containing them.

    double latitude = 40.748666;
    double longitude = -73.980389;
    Coordinate coordinate = new Coordinate(longitude, latitude);
    ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    coordinates.add(coordinate);
    Map <Coordinate, String> zipCodes = CoordinateToZipCode.getZipCodes(coordinates);
    System.out.println(zipCodes.get(coordinate));

