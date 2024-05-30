import static org.hamcrest.Matchers.equalTo;

import com.google.inject.Inject;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import services.Specifications;
import services.StoreServicesApi;

public class OrderPetErrorTest extends BaseApiTest{

  @Inject
  StoreServicesApi storeServicesApi;

  @Inject
  Specifications specifications;

  /**
   * Тест проверяет метод POST, для получения ошибки при размещении заказа на приобретение питомца.
   * Чтобы получить ошибку выполняем POST запрос без body.
   * Тест проверяет, что статус код = 400 и тело ответа соответствует ожидаемой ошибке.
   */
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
