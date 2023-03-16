package com.geo.location.geolocation.service;

import com.geo.location.geolocation.data.Location;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.IOException;
import java.net.UnknownHostException;

public interface LocationService {
    Location getLocation(String IP) throws IOException, GeoIp2Exception;
}
