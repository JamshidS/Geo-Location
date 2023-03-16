package com.geo.location.geolocation.service;

import com.geo.location.geolocation.data.Location;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface IpAddressService {
    Location getRequestIP(HttpServletRequest request) throws IOException, GeoIp2Exception;
}
