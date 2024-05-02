package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static utils.SeleniumCommands.getCommands;
import static utils.StringUtils.extractVariantIDFromString;

public class BagPage {

  private static final By BAG_PAGE = By.cssSelector("[data-locator-id='miniBag-component']");
  private static final By BAG_ITEMS = By.cssSelector("[data-locator-id^='miniBag-miniBagItem']");
  public static final String GS_LOCATOR_ATTRIBUTE = "data-locator-id";
  private static final By CLOSE_BUTTON = By.cssSelector("[data-locator-id='miniBag-closeButton-select']");
  private static final By REMOVED_PRODUCT_POPUP_MESSAGE = By.cssSelector("[data-locator-id='snackbox-component']");


  public BagPage() {
    getCommands().waitForAndGetVisibleElementLocated(BAG_PAGE);
  }

  public List<Long> getVariantIdsInBag() {
    return getBagItems().stream()
      .map(this::getVariantId)
      .collect(Collectors.toList());
  }

  private List<WebElement> getBagItems() {
    return getCommands().waitForAndGetAllVisibleElementsLocated(BAG_ITEMS);
  }

  private long getVariantId(WebElement bagItem) {
    return extractVariantIDFromString(getCommands().getAttributeFromElement(bagItem, GS_LOCATOR_ATTRIBUTE));
  }

  public BagPage closeBagPopup() {
    getCommands().waitForElementToBeClickableLocated(CLOSE_BUTTON);
    return this;
  }

  public BagPage removeProduct(Long productId) {
    By removeButton = By.cssSelector("[data-locator-id^='miniBag-remove-" + productId + "']");
    getCommands().waitForAndClickOnElementLocated(removeButton);
    return this;
  }

  public BagPage waitForAndGetRemovalPopup() {
    getCommands().waitForAndGetVisibleElementLocated(REMOVED_PRODUCT_POPUP_MESSAGE);
    return this;
  }
}
