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
import java.util.List;

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
  @Test(description = "Успешное добавление питомца", groups = "pet")
  public void checkAddPetOk() {

    int id = faker.number().numberBetween(0, 1000000);
    String name = faker.dog().name();
    int idCategory = faker.number().numberBetween(1, 10);
    String nameCategory = "dogs";
    List<String> photoUrls = Collections.singletonList("pathToPhoto");
    String status = "available";
    int idTags = faker.number().numberBetween(1, 10000);
    String nameTags = "tagName";
    List<Tags> tags = Arrays.asList(new Tags[] {Tags
            .builder().id(idTags)
            .name(nameTags).build()});

    setPetId(id);

    AddPetDTO addPetDTO = AddPetDTO
        .builder()
        .id(id)
        .category(Category.builder()
          .id(idCategory)
          .name(nameCategory)
          .build())
        .name(name)
        .photoUrls(photoUrls)
        .tags(tags)
        .status(status)
        .build();

    Response responseAddPet = addPetServiceApi.addPetPost(addPetDTO);


    /**
     * Проверка ответа от метода POST сервиса "/pet"
     */
    responseAddPet
      .then()
      .log().all()
      .spec(specifications.specResponse200())
      .body("id", equalTo(id))
      .body("category.id", equalTo(idCategory))
      .body("category.name", equalTo(nameCategory))
      .body("name", equalTo(name))
      .body("photoUrls[0]", equalTo(photoUrls.get(0)))
      .body("tags[0].id", equalTo(idTags))
      .body("tags[0].name", equalTo(nameTags))
        .body("status", equalTo(status));

    /**
     * Проверяем методом GET, что метод POST отработал корректно.
     */
    Response respGetPet = addPetServiceApi.petIdGet(id);
    respGetPet
      .then()
      .log().all()
      .spec(specifications.specResponse200())
      .body("id", equalTo(id))
      .body("category.id", equalTo(idCategory))
      .body("category.name", equalTo(nameCategory))
      .body("name", equalTo(name))
      .body("photoUrls[0]", equalTo(photoUrls.get(0)))
      .body("tags[0].id", equalTo(idTags))
      .body("tags[0].name", equalTo(nameTags))
        .body("status", equalTo(status));
  }

}
