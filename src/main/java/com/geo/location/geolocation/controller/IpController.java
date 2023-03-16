package com.geo.location.geolocation.controller;


import com.geo.location.geolocation.data.Location;
import com.geo.location.geolocation.service.IpAddressService;
import com.geo.location.geolocation.service.LocationService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/ip")
public class IpController {

    private  final Logger LOGGER = Logger.getLogger(IpController.class.getName());

    @Autowired
    private IpAddressService ipAddressService;

    @Autowired
    private LocationService locationService;


    @GetMapping("/user")
    public Location getIp(HttpServletRequest request) throws IOException, GeoIp2Exception {
        Location userLocation = ipAddressService.getRequestIP(request);
        LOGGER.info("Client City: "+ userLocation.getCity());
        LOGGER.info("Client Country: "+ userLocation.getCountry());
        LOGGER.info("Client State: "+ userLocation.getState());
        LOGGER.info("Client Postal: "+ userLocation.getPostal());
        return userLocation;
    }

}
