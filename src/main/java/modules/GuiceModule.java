package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import services.AddPetServiceApi;
import services.FindByStatusServiceApi;
import services.Specifications;
import services.StoreServicesApi;

public class GuiceModule extends AbstractModule {

  @Provides
  public AddPetServiceApi getAddPetServiceApi() {
    return new AddPetServiceApi();
  }

  @Provides
  public StoreServicesApi getStoreServicesApi() {
    return new StoreServicesApi();
  }

  @Provides
  public Specifications getSpecifications() {
    return new Specifications();
  }

  @Provides
  public FindByStatusServiceApi getFindByStatusServiceApi() {
    return new FindByStatusServiceApi();
  }

}
