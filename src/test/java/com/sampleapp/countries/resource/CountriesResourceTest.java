package com.sampleapp.countries.resource;

import com.sampleapp.countries.model.Country;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class CountriesResourceTest {

    @Test
    public void testCountriesUnauthorized() {
        given()
                .when().get("/countries")
                .then()
                .statusCode(401);
    }

 /*   @Test
    public void testCountriesAuthorized() {
        Country[] result = given()
                .when().get("/countries")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", is(250))
                .extract()
                .as(Country[].class);

        Stream<Country> stream1 = Arrays.stream(result);
        Optional<Country> country = stream1.filter(x -> x.getName().equals("Greece")).findFirst();
        assertTrue(country.isPresent());
        assertEquals("Athens", country.get().getCapital());
        assertEquals("Euro", country.map(c -> c.getCurrencies()).map(c -> c.get(0)).get().getName());
    }
*/

}