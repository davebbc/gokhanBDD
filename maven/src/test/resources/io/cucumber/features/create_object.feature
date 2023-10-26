Feature: Create object scenarios

  @apiSmoke
  Scenario: Create a new object
    Given I create a new object with random details
    When I send a POST request to "/objects" with the object data
    Then the response status code is 200
    And the response should contain the following values:
      | name             | Apple MacBook Pro 13 |
      | data.year        | 2019                 |
      | data.description | Intel Core i9        |
      | data.color       | Purple               |

  @apiSmoke
  Scenario: Create a new object from feature
    Given I add a new object with the following details:
      | id   | 14            |
      | name | iPhone 16 Pro |
    When I send a POST request to "/objects" with the object data
    Then the response status code is 200
    And the response should contain the following values:
      | name | iPhone 16 Pro |

  @apiSmoke
  Scenario: Attempt to create a object with unsupported media
    When I send a POST request to "/objects" with the object data
    Then the response status code is 415
    And the response should contain the following values:
      | error | 415 Unsupported Media Type. The 415 status code indicates that the origin server is refusing to service the request because the payload is in a format not supported by this method on the target resource. One of the examples of getting 415 would be sending a request with a Content-Type header which is not equal to application/json |

  @apiSmoke
  Scenario: Attempt to create a object with empty body
    Given I have a empty body request
    When I send a POST request to "/objects" with the object data
    Then the response status code is 400
    And the response should contain the following values:
      | error | 400 Bad Request. If you are trying to create or update the data, potential issue is that you are sending incorrect body json or it is missing at all. |



