package services;

import static io.restassured.RestAssured.given;

import com.google.inject.Inject;
import dto.addPet.AddPetDTO;
import io.restassured.response.Response;

public class AddPetServiceApi extends Specifications{

  private final String BASE_PATH = "/pet";

  @Inject
  public AddPetServiceApi() {
  }

  public Response addPetPost(AddPetDTO addPetDTO) {
    return
      given(specRequest())
        .body(addPetDTO)
        .when()
        .post(BASE_PATH);
  }

  public Response addPetPost() {
    return
      given(specRequest())
        .when()
        .post(BASE_PATH);
  }

  public Response petIdGet(int petId) {
    return
      given(specRequest())
        .when()
        .get(String.format("%s/%s", BASE_PATH, petId));
  }

  public Response petIdDelete(int petId) {
    return
            given(specRequest())
                    .when()
                    .delete(String.format("%s/%s", BASE_PATH, petId));
  }

}
