package org.example.homework03.accuweather;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.example.homework03.accuweather.error.Error;

import static io.restassured.RestAssured.given;


public class ForecastsTenDayTest extends AccuweatherAbstractTest {

    @Test
    void testTenDayGetResponse(){
        Error weather = given()
                .queryParams("apikey", getApiKey()).pathParam("locationId", 46875)
                .when()
                .get(getBaseUrl() + "/forecasts/v1/daily/10day/{locationId}")
                .then()
                .statusCode(401)
                .time(Matchers.lessThan(2000L))
                .extract()
                .response()
                .body().as(Error.class);

        Assertions.assertEquals("Unauthorized", weather.getCode());
        Assertions.assertEquals("Api Authorization failed", weather.getMessage());
        Assertions.assertEquals("/forecasts/v1/daily/10day/" +
                        "46875?apikey=b8MMYv1AfgLuxd8AY6TwW5VyfVytaLTb",
                weather.getReference());
    }
}
