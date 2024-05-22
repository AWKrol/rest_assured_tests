import com.github.javafaker.Faker;
import services.AddPetServiceApi;
import services.FindByStatusServiceApi;
import services.Specifications;
import services.StoreServicesApi;

public class BaseApiTest {

  Faker faker = new Faker();
  StoreServicesApi storeServicesApi = new StoreServicesApi();
  AddPetServiceApi addPetServiceApi = new AddPetServiceApi();
  Specifications specifications = new Specifications();
  FindByStatusServiceApi findByStatusServiceApi = new FindByStatusServiceApi();
}
