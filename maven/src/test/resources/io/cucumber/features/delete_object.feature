Feature: Delete object scenarios

  @apiSmoke
  Scenario: Delete an object and then delete
    Given I create a new object with random details
    And I send a POST request to object endpoint
    And the response status code is 200
    And I get the object id
    When I delete the object with the object id
    Then the response status code is 200
    And in the response a message is displayed the object has successfully been deleted

  @apiSmoke
  Scenario: Delete an object
    When I send a DELETE request to "/objects/6"
    Then the response status code is 405

  @apiSmoke
  Scenario: Attempt to delete an object with invalid object
    When I send a DELETE request to "/objects/31"
    Then the response status code is 404

  @apiSmoke
  Scenario: Attempt to delete an object with invalid object
    When I send a DELETE request to "/objects/31"
    Then the response status code is 404


