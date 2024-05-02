Feature: Remove from Bag

  Scenario: Removing a product from the Bag
    Given multiple products have been added to the Bag
    When a product is removed from the Bag
    Then the product should not appear in the Bag

  @manual
  Scenario: Removing multiple products from the Bag and asserting the sub total is updated
    Given there are multiple products in the Bag
    When the user removes a couple of products
    Then the products selected for removal are no longer in the Bag
    And the sub total reflects only the products remaining in the Bag