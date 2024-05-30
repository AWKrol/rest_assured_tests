import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.GuiceModule;
import org.testng.annotations.BeforeMethod;

public class BaseApiTest {

  protected Faker faker = new Faker();

  private Injector injector;

  @BeforeMethod
  public void init() {
    injector = Guice.createInjector(new GuiceModule());
    injector.injectMembers(this);
  }

}
