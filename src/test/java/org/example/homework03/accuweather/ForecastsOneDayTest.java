package org.example.homework03.accuweather;

import org.example.seminar.accuweather.weather.Weather;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ForecastsOneDayTest extends AccuweatherAbstractTest {

    @Test
    void testGetResponseOneDay(){
        Weather response = given()
                .queryParams("apikey", getApiKey()).pathParam("locationId", 1795)
                .when()
                .get(getBaseUrl() + "/forecasts/v1/daily/1day/{locationId}")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .response()
                .body().as(Weather.class);
        Assertions.assertEquals(1,response.getDailyForecasts().size());
    }
}
