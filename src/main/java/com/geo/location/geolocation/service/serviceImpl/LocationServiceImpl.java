package com.geo.location.geolocation.service.serviceImpl;

import com.geo.location.geolocation.data.Location;
import com.geo.location.geolocation.service.LocationService;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class LocationServiceImpl implements LocationService {
    private DatabaseReader databaseReader =null;
    private final String LOCATION = "C:\\Users\\Js8\\Downloads\\geo-location\\src\\main\\resources\\GeoLite2-City_20230314\\GeoLite2-City.mmdb";
    public LocationServiceImpl() throws IOException {
        File database = new File(LOCATION);
        databaseReader = new DatabaseReader.Builder(database).build();
    }
    @Override
    public Location getLocation(String IP) throws IOException, GeoIp2Exception {

        InetAddress dataBaseIP = InetAddress.getByName(IP);
        CityResponse response = databaseReader.city(dataBaseIP);
        Location userLocation = new Location();
        userLocation.setCity(response.getCity().getName());
        userLocation.setPostal(response.getPostal().getCode());
        userLocation.setCountry(response.getCountry().getName());
        userLocation.setState(response.getLocation().getTimeZone());
        return userLocation;
    }
}
