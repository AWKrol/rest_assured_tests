package services;

import static io.restassured.RestAssured.given;

import com.google.inject.Inject;
import io.restassured.response.Response;

public class FindByStatusServiceApi extends Specifications{

  private final String BASE_PATH = "/pet/findByStatus";

  @Inject
  public FindByStatusServiceApi() {
  }

  public Response findByStatus(String status) {

    return
      given()
        .spec(specRequest())
        .param("status", status)
        .when()
        .get(BASE_PATH);
  }

  public Response findByStatus() {

    return
      given()
        .spec(specRequest())
        .when()
        .get(BASE_PATH);
  }

}
