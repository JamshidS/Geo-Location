package com.geo.location.geolocation.service.serviceImpl;


import com.geo.location.geolocation.data.Location;
import com.geo.location.geolocation.service.IpAddressService;
import com.geo.location.geolocation.service.LocationService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;


@Service
public class IpAddressServiceImpl implements IpAddressService {

    private  final Logger LOGGER = Logger.getLogger(IpAddressServiceImpl.class.getName());

    private final LocationService locationService;
    private final String UNKNOWN = "unknown";
    private final String GET = "GET";
    private final String ACCEPT = "Accept";
    private final String URL = "https://api.ipify.org/";


    public IpAddressServiceImpl(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public Location getRequestIP(HttpServletRequest request) throws IOException, GeoIp2Exception {

        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            URL ipify = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) ipify.openConnection();
            conn.setRequestMethod(GET);
            conn.setRequestProperty(ACCEPT, "application/json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            ipAddress = response.toString();
        }
        LOGGER.info("User IP address: "+ ipAddress);
        return locationService.getLocation(ipAddress);
    }
}
