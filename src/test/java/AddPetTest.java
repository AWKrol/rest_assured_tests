import static org.hamcrest.Matchers.equalTo;

import dto.addPet.AddPetDTO;
import dto.addPet.Category;
import dto.addPet.Tags;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.Collections;

public class AddPetTest extends BaseApiTest{

  /**
  * Тест проверяет метод POST, для успешного добавления питомца.
  * Тест проверяет, что данные переданные в теле запроса корректно обработаны и
  * возвращены в теле ответа. Также проверяется, что статус код ответа = 200.
  * */
  @Test(description = "Успешное добавление питомца")
  public void checkAddPetOk() {

    AddPetDTO addPetDTO = AddPetDTO
        .builder()
        .id(faker.number().numberBetween(0, 1000000))
        .category(Category.builder()
        .id(faker.number().numberBetween(1, 10))
        .name("dogs")
        .build())
        .name(faker.dog().name())
        .photoUrls(Collections.singletonList("pathToPhoto"))
        .tags(Arrays.asList(new Tags[] {Tags
          .builder().id(faker.number().numberBetween(1, 10000))
          .name("tagName").build()}))
        .status("available")
        .build();

    Response response = addPetServiceApi.addPetPost(addPetDTO);

    response
      .then()
      .log().all()
      .spec(specifications.specResponse200())
      .body("id", equalTo(addPetDTO.getId()))
      .body("category.id", equalTo(addPetDTO.getCategory().getId()))
      .body("category.name", equalTo(addPetDTO.getCategory().getName()))
      .body("name", equalTo(addPetDTO.getName()))
      .body("photoUrls[0]", equalTo(addPetDTO.getPhotoUrls().get(0)))
      .body("tags[0].id", equalTo(addPetDTO.getTags().get(0).getId()))
      .body("tags[0].name", equalTo(addPetDTO.getTags().get(0).getName()))
        .body("status", equalTo(addPetDTO.getStatus()));
  }

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
