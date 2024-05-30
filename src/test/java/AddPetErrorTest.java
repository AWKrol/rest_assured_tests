import static org.hamcrest.Matchers.equalTo;

import com.google.inject.Inject;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import services.AddPetServiceApi;
import services.Specifications;

public class AddPetErrorTest extends BaseApiTest{

  @Inject
  AddPetServiceApi addPetServiceApi;

  @Inject
  Specifications specifications;

  /**
   * Тест проверяет метод POST, для получения ошибки при добавлении питомца.
   * Чтобы получить ошибку выполняем POST запрос без body.
   * Тест проверяет, что статус код = 405 и тело ответа соответствует ожидаемой ошибке.
   */
  @Test(description = "Ошибка при добавлении питомца")
  public void checkAddPetError() {
    Response response = addPetServiceApi.addPetPost();

    response
      .then()
      .log().all()
      .spec(specifications.specResponse405())
      .body("code", equalTo(405))
      .body("type", equalTo("unknown"))
        .body("message", equalTo("no data"));
  }

}
