package services;

import dto.OrderPetDTO;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StoreServicesApi extends Specifications{

	private final String BASE_PATH = "/store/order";

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
