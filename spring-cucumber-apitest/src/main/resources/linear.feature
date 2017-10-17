Feature: Implementing server side linear equation restful service
Scenario: client makes call to GET /solve
    When the client calls /solve
    Then the employee receives status code of 200
    And the response should contain:
      """
      Values of X=0.3333333333333333 and Y=0.16666666666666666
      """