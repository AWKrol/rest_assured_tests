import static org.hamcrest.Matchers.equalTo;

import com.google.inject.Inject;
import dto.addPet.AddPetDTO;
import dto.addPet.Category;
import dto.addPet.Tags;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import services.AddPetServiceApi;
import services.Specifications;
import java.util.Arrays;
import java.util.Collections;

public class AddPetTest extends BaseApiTest{

  @Inject
  AddPetServiceApi addPetServiceApi;

  @Inject
  Specifications specifications;

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

    Response responseAddPet = addPetServiceApi.addPetPost(addPetDTO);

    // Проверка ответа от метода POST сервиса "/pet"
    responseAddPet
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

    /**
     * Проверяем методом GET, что метод POST отработал корректно.
     */
    Response respGetPet = addPetServiceApi.petIdGet(addPetDTO.getId());
    respGetPet
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

}
