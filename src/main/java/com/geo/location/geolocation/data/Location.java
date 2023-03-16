package com.geo.location.geolocation.data;


import lombok.Data;

@Data
public class Location {
    private String country;
    private String city;
    private String state;
    private String postal;
}
