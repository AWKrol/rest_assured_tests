import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.stream.Collectors;

public class FindPetByStatusTest extends BaseApiTest{

  /**
  * Тест проверяет метод GET, для получения списка животных по статусу, который мы передаем как параметр.
  * Тест проверяет, что данные полученные в ответе содержат только информацию о питомцах с переданным статусом.
  * Также проверяется, что статус код ответа = 200.
  */
  @Test(description = "Успешное получение списка животных по статусу")
  public void findPetByStatusOk() {
    String status = "sold";
    Response response = findByStatusServiceApi.findByStatus(status);

    response
      .then()
      .log().all()
        .spec(specifications.specResponse200());

    List<String> list =
        response.jsonPath().getList("status")
        .stream()
        .map(x -> String.valueOf(x))
        .filter(statusActual -> !statusActual.equals(status))
        .collect(Collectors.toList());

    Assert.assertEquals(list.size(), 0);
  }

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
