package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageobjects.BagPage;
import pageobjects.ProductDisplayPage;
import stepdefs.hooks.Hooks;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductStepDefs {

  private final WebDriver driver;
  private Long productId;
  private Long product2Id;

  public ProductStepDefs(){
    this.driver = Hooks.getDriver();
  }

  @Given("the user is on a product page")
  public void theUserIsOnAProductPage() {
    driver.get("https://uk.gymshark.com/products/gymshark-speed-t-shirt-black-aw23");
    productId = 39654522814667L;
    new ProductDisplayPage().closeCookieBanner();
  }

  @When("adding the product to the Bag")
  public void addingTheProductToTheBag() {
    ProductDisplayPage productDisplayPage = new ProductDisplayPage();
    productDisplayPage.selectSmallSize();
    productDisplayPage.selectAddToBag();
  }

  @Then("the product should appear in the Bag")
  public void theProductShouldAppearInTheBag() {
    BagPage bagPage = new BagPage();
    List<Long> variantIds = bagPage.getVariantIdsInBag();
    assertThat(variantIds).as("Expected product is in Bag").contains(productId);
  }

  @Given("multiple products have been added to the Bag")
  public void multipleProductsAddedToBag() {
    driver.get("https://uk.gymshark.com/products/gymshark-speed-t-shirt-black-aw23");
    productId = 39654522814667L;
    new ProductDisplayPage().closeCookieBanner();
    ProductDisplayPage productDisplayPage = new ProductDisplayPage();
    productDisplayPage.selectSmallSize();
    productDisplayPage.selectAddToBag();
    BagPage bagPage = new BagPage();
    bagPage.closeBagPopup();
    driver.get("https://uk.gymshark.com/products/gymshark-speed-5-shorts-fluo-speed-green-aw23");
    product2Id = 39654520946891L;
    productDisplayPage.selectSmallSize();
    productDisplayPage.selectAddToBag();
    List<Long> variantIds = bagPage.getVariantIdsInBag();
    assertThat(variantIds).as("Expected product is in Bag").contains(productId, product2Id);
  }

  @When("removing a product from the Bag")
  public void removingProductFromTheBag() {
    BagPage bagPage = new BagPage();
    bagPage.removeProduct(product2Id);
    bagPage.waitForAndGetRemovalPopup();
  }

  @Then("the product should not appear in the Bag")
  public void productShouldNotAppearInTheBag() {
    BagPage bagPage = new BagPage();
    List<Long> variantIds = bagPage.getVariantIdsInBag();
    assertThat(variantIds).as("Expected product is in Bag").contains(productId);
    assertThat(variantIds).as("Expected product is not in Bag").doesNotContain(product2Id);
  }
}
