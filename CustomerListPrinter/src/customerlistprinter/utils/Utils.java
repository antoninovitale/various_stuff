package customerlistprinter.utils;


public class Utils {
	public final static double DEFAULT_LAT = 53.3381985;
	public final static double DEFAULT_LNG = -6.2592576;
	public final static int EARTH_RADIUS = 6371;
	public final static int DEFAULT_DISTANCE = 100;

	/**
	 * Method to calculate if a location is distant no more than 100km from
	 * another location using
	 * https://en.wikipedia.org/wiki/Great-circle_distance
	 * 
	 * d = r*angle
	 * 
	 * numerator = sqrt(pow(cos(lat2)sin(deltaLng),2)+pow((cos(lat1)sin(lat2)-sin(lat1)cos(lat2)cos(deltaLng)),2))
	 * 
	 * denominator = sin(lat1)sin(lat2)+cos(lat1)cos(lat2)cos(deltaLng)
	 * 
	 * angle = atan2(numerator/denominator)
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return true if the distance is less than DEFAULT_DISTANCE
	 */
	public static boolean isWithinDefaultDistance(double lat1, double lng1,
			double lat2, double lng2) {
		lat1 = Math.toRadians(lat1);
		lng1 = Math.toRadians(lng1);
		lat2 = Math.toRadians(lat2);
		lng2 = Math.toRadians(lng2);
		double deltaLng = Math.abs(lng1 - lng2);
		double numerator = Math.sqrt(Math.pow(
				Math.cos(lat2) * Math.sin(deltaLng), 2)
				+ Math.pow(Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)
						* Math.cos(lat2) * Math.cos(deltaLng), 2));
		double denominator = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1)
				* Math.cos(lat2) * Math.cos(deltaLng);
		double angle = Math.atan2(numerator, denominator);
		double distance = EARTH_RADIUS * angle;
		// System.out.println("Distance: " + distance);
		return (distance) <= DEFAULT_DISTANCE;
	}

}