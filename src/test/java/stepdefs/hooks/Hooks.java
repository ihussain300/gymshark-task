package stepdefs.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

  private static WebDriver driver;

  @Before
  public void setup() {
    if (driver == null) {
      driver = new ChromeDriver();
    }
  }

  @After
  public void teardown() {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }

  public static WebDriver getDriver() {
    return driver;
  }

}
