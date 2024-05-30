import static org.hamcrest.Matchers.equalTo;

import com.google.inject.Inject;
import dto.OrderPetDTO;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import services.Specifications;
import services.StoreServicesApi;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class OrderPetTest extends BaseApiTest{

  @Inject
  StoreServicesApi storeServicesApi;

  @Inject
  Specifications specifications;

  /**
  * Тест проверяет метод POST, для успешного размещение заказа на приобретение питомца.
  * Тест проверяет, что данные переданные в теле запроса корректно обработаны и
  * возвращены в теле ответа. Также проверяется, что статус код ответа = 200.
  */
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

}
