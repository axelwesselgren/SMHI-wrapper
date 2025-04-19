package smhi.data;

/**
 * {@code Geo} is a DTO class that represents geographical coordinates.
 * 
 * @author Axel Lönnby Wesselgren
 * @version 1.0
 * @since 1.0
 */
public class Geo {
    private final double latitude, longitude;
    
    /**
     * Bounds for geographical coordinates according to SMHI.
     */
    public static final double 
    SW_LAT, SW_LON, 
    SE_LAT, SE_LON, 
    NE_LAT, NE_LON, 
    NW_LAT, NW_LON;

    /**
     * Predfined geographical coordinates for Major city within the bounds.
     */
    public static final Geo HELSINKI, COPENHAGEN, OSLO, GOTHENBURG, STOCKHOLM;

    static {
        SW_LAT = 52.500440;
        SW_LON = 2.250475;

        SE_LAT = 52.547483;
        SE_LON = 27.348870;

        NE_LAT = 70.740996;
        NE_LON = 37.848053;

        NW_LAT = 70.655722;
        NW_LON = -8.541278;

        HELSINKI = new Geo(60.1695, 24.9354);
        COPENHAGEN = new Geo(55.6761, 12.5683);
        OSLO = new Geo(59.9139, 10.7522);
        GOTHENBURG = new Geo(57.7089, 11.9746);
        STOCKHOLM = new Geo(59.3293, 18.0686);
    }

    /**
     * Constructs a new {@code Geo} object with the specified latitude and longitude.
     * <p> Checks if the geographical coordinates are inside the bounds.
     * 
     * @param latitude The latitude of the geographical coordinates.
     * @param longitude The longitude of the geographical coordinates.
     * @throws IllegalArgumentException If the geographical coordinates are outside of the bounds.
     */
    public Geo(double latitude, double longitude) throws IllegalArgumentException {
        this.latitude = latitude;
        this.longitude = longitude;

        if (!isInsideBounds()) {
            throw new IllegalArgumentException("Geographical coordinates outside of bounds.");
        }
    }

    /**
     * Checks if the geographical coordinates are inside the bounds.
     * <p>If the coordinates are inside of the South-West and North-East bounds, the coordinates are considered to be inside the bounds.
     * 
     * @return {@code true} if the geographical coordinates are inside the bounds, otherwise {@code false}.
     */
    private boolean isInsideBounds() {
        return latitude >= SW_LAT && latitude <= NE_LAT &&
        longitude >= SW_LON && longitude <= NE_LON;
    }

    /**
     * Returns the latitude of the geographical coordinates.
     * 
     * @return The latitude of the geographical coordinates.
     */
    public double getLatitude() {
        return latitude;
    }
    /**
     * Returns the longitude of the geographical coordinates.
     * 
     * @return The longitude of the geographical coordinates.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns a string representation of the geographical coordinates.
     * 
     * @return A string representation of the geographical coordinates.
     */
    @Override
    public String toString() {
        return String.format("Latitude: %s\nLongitude: %s", latitude, longitude);
    }
}
