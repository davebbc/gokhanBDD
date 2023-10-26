Feature: Get object scenarios

  @apiSmoke
  Scenario: Retrieve an Object by Valid ID
    When I send a GET request to "/objects/1"
    Then the response status code is 200
    And the response should contain the following values:
      | id            | 1                  |
      | name          | Google Pixel 6 Pro |
      | data.color    | Cloudy White       |
      | data.capacity | 128 GB             |

  @apiSmoke
  Scenario:Retrieve all objects
    Given I send a GET request to "/objects"
    When the response status code is 200
    And the response should contain the following values:
      | [0].id            | 1                                 |
      | [0].name          | Google Pixel 6 Pro                |
      | [0].data.color    | Cloudy White                      |
      | [0].data.capacity | 128 GB                            |
      | [1].id            | 2                                 |
      | [1].name          | Apple iPhone 12 Mini, 256GB, Blue |
      | [2].id            | 3                                 |
      | [2].name          | Apple iPhone 12 Pro Max           |
      | [2].data.color    | Cloudy White                      |
#and so son

  @apiSmoke
  Scenario Outline: Retrieve an Object by Valid ID - multiple objects
    When I send a GET request to "<object_id>"
    Then the response status code is <expected_status_code>
    And the object name is "<object_name>"
    Examples:
      | object_id  | expected_status_code | object_name                       |
      | /objects/1 | 200                  | Google Pixel 6 Pro                |
      | /objects/2 | 200                  | Apple iPhone 12 Mini, 256GB, Blue |
      | /objects/3 | 200                  | Apple iPhone 12 Pro Max           |
      | /objects/4 | 200                  | Apple iPhone 11, 64GB             |
      | /objects/5 | 200                  | Samsung Galaxy Z Fold2            |
      | /objects/6 | 200                  | Apple AirPods                     |
      | /objects/7 | 200                  | Apple MacBook Pro 16              |
      | /objects/8 | 200                  | Apple Watch Series 8              |
      #      and so on

  @apiSmoke
  Scenario: Attempt to Retrieve Non-Existent Object
    When I send a GET request to "/objects/31"
    Then the response status code is 404

  @apiSmoke
  Scenario: Retrieve an Object by Invalid ID
    When I send a GET request to "/33"
    Then the response status code is 404


