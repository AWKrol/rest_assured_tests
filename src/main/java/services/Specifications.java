package services;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

public class Specifications {

  protected final String BASE_URL = "https://petstore.swagger.io/v2";

  protected RequestSpecification specRequest() {
    return given()
      .baseUri(BASE_URL)
      .contentType(ContentType.JSON)
      .log().all();
  }

  public ResponseSpecification specResponse200() {
    return given().then()
      .contentType(ContentType.JSON)
      .statusCode(HttpStatus.SC_OK);
  }

  public ResponseSpecification specResponse400() {
    return given().then()
      .contentType(ContentType.JSON)
      .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  public ResponseSpecification specResponse405() {
    return given().then()
      .contentType(ContentType.JSON)
      .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
  }

}
