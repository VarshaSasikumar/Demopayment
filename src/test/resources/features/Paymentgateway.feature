Feature: Payment Gateway of MarketPay

  As a user of the MarketPay
  I want to select a Payment method
  and Perform a successful Bank Transaction

  Scenario:Successful navigation to payment gateway
    Given I am on the Payment gateway page
    When User click on dropdown and select TELECHECK routing
    And Click on blue Checkout button
    Then User should be navigated to new session page
    Then Click on Bank account payment method
    When User enters data

      | routingno | accountno | dob      | driverlicense | licensedstate |
      | 121000248 | 144155167 | 18121970 | 10981111      | Texas         |

    And Accepts Terms and Conditions
    And  Click on Pay button
    Then Validate the result xml
    Then Navigate to MarketPay Home page


