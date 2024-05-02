Feature: Remove from Bag

  Scenario: Removing a product from the Bag
    Given multiple products have been added to the Bag
    When removing a product from the Bag
    Then the product should not appear in the Bag

  @manual
  Scenario: Removing multiple products from the Bag and asserting the sub total is updated
    Given there are multiple products in the Bag
    When removing multiple products from the Bag
    Then the products selected for removal are no longer in the Bag
    And the sub total reflects only the products remaining in the Bag