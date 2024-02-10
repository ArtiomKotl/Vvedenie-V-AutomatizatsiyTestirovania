package org.example.seminar.accuweather;

import io.restassured.http.Method;
import org.example.seminar.accuweather.location.Location;
import org.example.seminar.accuweather.weather.Weather;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ForecastsFiveDaysOfDailyTest extends AccuweatherAbstractTest {

    @Test
    void testGetResponse(){
        Weather response = given()
                .queryParams("apikey", getApiKey()).pathParam("locationId", 50)
                .when()
                .get(getBaseUrl() + "/forecasts/v1/daily/5day/{locationId}")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().as(Weather.class);
        Assertions.assertEquals(5,response.getDailyForecasts().size());

    }

    @Test
    void testGetLocations(){
        Map<String, String> mapQuery =new HashMap<>();
        mapQuery.put("apikey", getApiKey());
        mapQuery.put("q", "Samara");
        List<Location> locationList = given()
                .queryParams(mapQuery)
                .when().get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then()
                .statusCode(200)
                .extract()
                .body().jsonPath().getList(".", Location.class);
        Assertions.assertAll(() -> Assertions.assertEquals(10, locationList.size()),
                () -> Assertions.assertEquals("Samarai", locationList.get(2).getLocalizedName()));

    }

    @Test
    void testGetLocationsV2(){
        Map<String, String> mapQuery =new HashMap<>();
        mapQuery.put("apikey", getApiKey());
        mapQuery.put("q", "Samara");
        List<Location> locationList = given()
                .queryParams(mapQuery)
                .when().request( Method.GET,getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then()
                .statusCode(200)
                .extract()
                .body().jsonPath().getList(".", Location.class);
        Assertions.assertAll(() -> Assertions.assertEquals(10, locationList.size()),
                () -> Assertions.assertEquals("Samaraik", locationList.get(2).getLocalizedName()));

    }
}
