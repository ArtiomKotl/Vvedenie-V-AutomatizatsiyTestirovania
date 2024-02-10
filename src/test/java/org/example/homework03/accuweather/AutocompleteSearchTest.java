package org.example.homework03.accuweather;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.example.homework03.accuweather.location.Location;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AutocompleteSearchTest extends AccuweatherAbstractTest{

    @Test
    void testGetLocations() {
        Map<String, String> mapQuery = new HashMap<>();

        mapQuery.put("apikey", getApiKey());
        mapQuery.put("q", "Gomel");

        List<Location> list = given()
                .queryParams(mapQuery)
                .when()
                .get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(3000L))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertAll(() -> Assertions.assertEquals(5,
                        list.size()),
                () -> Assertions.assertEquals("Gomel",
                        list.get(0).getLocalizedName()),
                () -> Assertions.assertEquals("City",
                        list.get(0).getType()),
                () -> Assertions.assertEquals("28573",
                        list.get(0).getKey()),
                () -> Assertions.assertEquals("BY",
                        list.get(0).getCountry().getId()),
                () -> Assertions.assertEquals("Belarus",
                        list.get(0).getCountry().getLocalizedName()),
                () -> Assertions.assertEquals("HO",
                        list.get(0).getAdministrativeArea().getId()),
                () -> Assertions.assertEquals("Gomel", list.get(0).
                        getAdministrativeArea().getLocalizedName()));
    }

    @Test
    void testGetResponseLocations() {

        Response response = given().queryParams("apikey", getApiKey(), "q", "Gomel")
                .when().request(Method.GET, getBaseUrl() + "/locations/v1/cities/autocomplete");

        int statusCode = response.getStatusCode();
        List<Header> locationList = response.getHeaders().asList();
        String header = response.getHeader("Content-Encoding");
        String contentType = response.getContentType();

        Assertions.assertEquals(200, statusCode);
        Assertions.assertEquals(25, locationList.size());
        Assertions.assertEquals("gzip", header);
        Assertions.assertEquals(ContentType.JSON.withCharset(StandardCharsets.UTF_8).
                toLowerCase(Locale.ROOT), contentType);

    }
}
