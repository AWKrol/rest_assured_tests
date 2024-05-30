package services;

import static io.restassured.RestAssured.given;

import com.google.inject.Inject;
import dto.OrderPetDTO;
import io.restassured.response.Response;

public class StoreServicesApi extends Specifications{

  private final String BASE_PATH = "/store/order";

  @Inject
  public StoreServicesApi() {
  }

  public Response createNewOrderForPet(OrderPetDTO orderPetDTO) {

    return
      given()
        .spec(specRequest())
        .body(orderPetDTO)
        .when()
        .post(BASE_PATH);
  }

  public Response createNewOrderForPet() {

    return
      given(specRequest())
        .when()
        .post(BASE_PATH);
  }

}
