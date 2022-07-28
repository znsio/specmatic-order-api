Feature:
  Scenario:
    Given url "http://localhost:8080/orders?status=pending"
    When method get
    Then status 200

    * def id = response[0].id

    Given url "http://localhost:8080/orders/" + id
    When header Authenticate = "abc123"
    And method delete
    Then status 204
