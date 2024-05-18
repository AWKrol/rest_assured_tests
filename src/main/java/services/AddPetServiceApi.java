package services;

import dto.addPet.AddPetDTO;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AddPetServiceApi extends Specifications{

	private final String BASE_PATH = "/pet";

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

}
