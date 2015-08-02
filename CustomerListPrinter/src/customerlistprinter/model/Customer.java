package customerlistprinter.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Customer implements Serializable, Comparable<Customer> {
	private static final long serialVersionUID = -5808404131338797320L;
	private static final String NAME_PLACEHOLDER= "N/A";
	private String latitude;
	private String longitude;
	@SerializedName("user_id")
	private int userId;
	private String name;

	public String getLatitude() {
		return latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public double getLatitudeAsDouble() {
		try {
			return Double.valueOf(latitude);
		}
		catch(Exception e) {
			return 0;
		}
	}

	public double getLongitudeAsDouble() {
		try {
			return Double.valueOf(longitude);
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	public int getUserId() {
		return userId;
	}

	public String getName() {
		if(name == null || name.trim().equalsIgnoreCase("")) {
			return NAME_PLACEHOLDER;
		}
		return name;
	}

	

	@Override
	public int compareTo(Customer o) {
		if (this.getUserId() < o.getUserId()) {
			return -1;
		} else if (this.getUserId() == o.getUserId()) {
			return 0;
		} else {
			return 1;
		}
	}

}