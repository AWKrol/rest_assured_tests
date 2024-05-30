import com.google.inject.Inject;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.FindByStatusServiceApi;
import services.Specifications;

public class FindPetByStatusErrorTest extends BaseApiTest{

  @Inject
  FindByStatusServiceApi findByStatusServiceApi;

  @Inject
  Specifications specifications;

  /**
   * Тест проверяет метод GET, для получения списка животных по статусу, статус не передан.
   * Тест проверяет, что данные полученные в ответе содержат пустой список ('[]').
   * Также проверяется, что статус код ответа = 200.
   */
  @Test(description = "Успешное получение списка животных по статусу, статус не передан")
  public void findPetByStatusError() {
    Response response = findByStatusServiceApi.findByStatus();

    response
      .then()
      .log().all()
        .spec(specifications.specResponse200());

    Assert.assertEquals(response.asString(), "[]");
  }

}
