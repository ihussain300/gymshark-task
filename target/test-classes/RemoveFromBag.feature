Feature: Remove from Bag

  Scenario: Removing a product from the Bag
    Given multiple products have been added to the Bag
    When a product is removed from the Bag
    Then the product should not appear in the Bag

  @manual
  Scenario: Sub total is updated once a product is removed from the Bag
    Given multiple products have been added to the Bag
    And the sub total displayed is correct
    When a product is removed from the Bag
    Then the sub total is also updated to reflect only the products remaining in the Bag