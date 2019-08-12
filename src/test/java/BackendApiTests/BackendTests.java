package BackendApiTests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BackendTests {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
        RequestSpecification rs = new RequestSpecBuilder()
                .addHeader("Content-type","bla").build();
        RestAssured.requestSpecification = rs;
    }

    @Test @Order(1) @Tag("ai")
    public void testSomething(){
        given().log().all().get("/posts/1").then().assertThat().statusCode(200);
    }

    @Test @Order(2) @Tag("chachacha")
    public void anotherTest(){
        given()
                .get("/posts/1")
                .then().log().body()
                .assertThat()
                .body("id", equalTo(1))
                .body("userId", equalTo(1));
        }

    @Test @Order(3)
    public void testUserHas10Posts(){
        given()
                .get("https://jsonplaceholder.typicode.com/posts?userId=1")
                .then().log().body()
                .assertThat()
                .body("size()", is(10));
    }

    @Test @Order(4) @Disabled
    public void postSomething(){
        given()
                .header("Content-Type","application/json")
                .body(Data.blabla)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then().log().all()
                .assertThat()
                .statusCode(201);
    }

}
