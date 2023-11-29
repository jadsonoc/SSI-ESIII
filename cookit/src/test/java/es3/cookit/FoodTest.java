package es3.cookit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import es3.cookit.controller.FoodController;
import es3.cookit.services.FoodService;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import io.restassured.http.ContentType;


@QuarkusTest
@TestHTTPEndpoint(FoodController.class)
public class FoodTest {

    @InjectSpy
    FoodService foodService;

    @Test
    public void testFoodsEndpoint() {
        given()
          .when().get()
          .then()
                .statusCode(200);
    }

    @Test
    void testFoodsEndpointWithSpy() {

        given()
          .when().get()
                .then().statusCode(200);

        verify(foodService).listFood();
    }

    @Test
    void testSearchFoodByNameEndpoint() {

        given().contentType(ContentType.JSON).param("query", "Tomate")
          .when().get("search/tom")
          .then().statusCode(200)
          .body("size()", is(1))
            .body("name", hasItem("Tomate"))
                .body("lactoseFree", hasItem(true))
                .body("glutenFree", hasItem(true))
                .body("oilseedFree", hasItem(true))
          .body("foodUnit", hasItem("un"));
    }

    @Test
    void testWhenGetFoodByNameThenFoodShouldBeFound() {

        given().contentType(ContentType.JSON).param("query", "Tomate")
          .when().get("search/tom")
                .then().statusCode(200);

        verify(foodService).listFoodByQuery("tom");
    }
    
}
