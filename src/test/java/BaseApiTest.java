import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import io.restassured.response.Response;
import modules.GuiceModule;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import services.AddPetServiceApi;
import services.StoreServicesApi;

public class BaseApiTest {

  private int petId;
  private int orderId;

  public void setPetId(int petId) {
    this.petId = petId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  protected Faker faker = new Faker();

  private Injector injector;

  @Inject
  AddPetServiceApi addPetServiceApi;

  @Inject
  StoreServicesApi storeServicesApi;

  @BeforeMethod
  public void init() {
    injector = Guice.createInjector(new GuiceModule());
    injector.injectMembers(this);
  }

  @AfterMethod(onlyForGroups = "pet")
  public void afterMethodPet() {
    Response respDel = addPetServiceApi.petIdDelete(petId);
    respDel
      .then()
      .log().all();
  }

  @AfterMethod(onlyForGroups = "order")
  public void afterMethodOrder() {
    Response respDel = storeServicesApi.orderDelete(orderId);
    respDel
      .then()
      .log().all();
  }

}
