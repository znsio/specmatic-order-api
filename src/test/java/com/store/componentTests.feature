Feature: Componen Tests
  Scenario: Search for products of type gadget
    Given url "http://localhost:8080/products?type=gadget"
    When method get
    Then status 200

    And match each $ contains {"type": "gadget"}
