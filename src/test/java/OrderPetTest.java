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
  @Test(description = "Успешное размещение заказа", groups = "order")
  public void checkCreateOrderOk() {

    int id = faker.number().numberBetween(1, 10);
    int petId = faker.number().numberBetween(0, 100000);
    int quantity = faker.number().numberBetween(0, 100);
    String shipDate = String.format("%s+0000",LocalDateTime.now(ZoneId.of("GMT")));
    String status = "placed";
    boolean complete = true;

    setOrderId(id);

    OrderPetDTO orderPetDTO = OrderPetDTO.builder()
        .id(id)
        .petId(petId)
        .quantity(quantity)
        .shipDate(shipDate)
        .status(status)
        .complete(complete)
          .build();

    Response response = storeServicesApi.createNewOrderForPet(orderPetDTO);
    /**
     * Проверка ответа от метода POST сервиса "/store/order"
     */
    response
      .then()
      .spec(specifications.specResponse200())
      .log().all()
      .body("id", equalTo(id))
      .body("petId", equalTo(petId))
      .body("quantity", equalTo(quantity))
      .body("shipDate", equalTo(shipDate))
      .body("status", equalTo(status))
        .body("complete", equalTo(complete));

    /**
     * Проверяем методом GET, что метод POST отработал корректно.
     */
    Response respGetOrder = storeServicesApi.orderGet(id);

    respGetOrder
      .then()
      .spec(specifications.specResponse200())
      .log().all()
      .body("id", equalTo(id))
      .body("petId", equalTo(petId))
      .body("quantity", equalTo(quantity))
      .body("shipDate", equalTo(shipDate))
      .body("status", equalTo(status))
        .body("complete", equalTo(complete));
  }

}
