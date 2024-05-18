package services;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FindByStatusServiceApi extends Specifications{

	private final String BASE_PATH = "/pet/findByStatus";

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
