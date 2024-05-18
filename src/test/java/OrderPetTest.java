import dto.OrderPetDTO;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.hamcrest.Matchers.equalTo;

public class OrderPetTest extends BaseApiTest{

	/**
	 * Тест проверяет метод POST, для успешного размещение заказа на приобретение питомца.
	 * Тест проверяет, что данные переданные в теле запроса корректно обработаны и
	 * возвращены в теле ответа. Также проверяется, что статус код ответа = 200.
	 * */
	@Test(description = "Успешное размещение заказа")
	public void checkCreateOrderOk() {
		OrderPetDTO orderPetDTO = OrderPetDTO.builder()
						.id(faker.number().numberBetween(0, 1000000))
						.petId(faker.number().numberBetween(0, 100000))
						.quantity(faker.number().numberBetween(0, 100))
						.shipDate(LocalDateTime.now(ZoneId.of("GMT")) + "+0000")
						.status("placed")
						.complete(true)
						.build();

		Response response = storeServicesApi.createNewOrderForPet(orderPetDTO);

		response
						.then()
						.spec(specifications.specResponse200())
						.log().all()
						.body("id", equalTo(orderPetDTO.getId()))
						.body("petId", equalTo(orderPetDTO.getPetId()))
						.body("quantity", equalTo(orderPetDTO.getQuantity()))
						.body("shipDate", equalTo(orderPetDTO.getShipDate()))
						.body("status", equalTo(orderPetDTO.getStatus()))
						.body("complete", equalTo(orderPetDTO.isComplete()));
	}

	/**
	 * Тест проверяет метод POST, для получения ошибки при размещении заказа на приобретение питомца.
	 * Чтобы получить ошибку выполняем POST запрос без body.
	 * Тест проверяет, что статус код = 400 и тело ответа соответствует ожидаемой ошибке.
	 * */
	@Test(description = "Ошибка при размещении заказа")
	public void checkCreateOrderError() {
		Response response = storeServicesApi.createNewOrderForPet();

		response
						.then()
						.spec(specifications.specResponse400())
						.log().all()
						.body("code", equalTo(1))
						.body("type", equalTo("error"))
						.body("message", equalTo("No data"));
	}

}
