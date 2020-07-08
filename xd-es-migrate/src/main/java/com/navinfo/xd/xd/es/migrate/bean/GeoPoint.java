package com.navinfo.xd.xd.es.migrate.bean;

import java.io.Serializable;

public class GeoPoint implements Serializable {
	private static final long serialVersionUID = -8231464086120236111L;
	private double lat;
	private double lon;

	@SuppressWarnings("unused")
	private GeoPoint() {
		// required by mapper to instantiate object
	}

	public GeoPoint(double latitude, double longitude) {
		this.lat = latitude;
		this.lon = longitude;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
}