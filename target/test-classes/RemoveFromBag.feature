Feature: Remove from Bag

  Scenario: Removing a product from the Bag
    Given multiple products have been added to the Bag
    When a product is removed from the Bag
    Then the product should not appear in the Bag