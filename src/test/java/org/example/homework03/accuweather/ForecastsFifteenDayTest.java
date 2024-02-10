package org.example.homework03.accuweather;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.example.homework03.accuweather.error.Error;

import static io.restassured.RestAssured.given;

public class ForecastsFifteenDayTest extends AccuweatherAbstractTest {

    @Test
    void  testFifteenDayGetResponse() {

        Response response = given()
                .queryParam("apikey", getApiKey()).pathParam("locationkey", 46875)
                .when()
                .request(Method.GET, getBaseUrl() + "/forecasts/v1/daily/15day/{locationkey}");

        Assertions.assertAll(() -> Assertions.assertEquals(401, response.statusCode()),
                () -> Assertions.assertEquals("Unauthorized", response.body().as(Error.class).getCode()),
                () -> Assertions.assertEquals("Api Authorization failed",
                        response.body().as(Error.class).getMessage()),
                () -> Assertions.assertEquals("/forecasts/v1/daily/15day/" +
                                "46875?apikey=b8MMYv1AfgLuxd8AY6TwW5VyfVytaLTb",
                        response.body().as(Error.class).getReference()));
    }
}
