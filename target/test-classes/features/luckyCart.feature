Feature: Luck Cart

  @401
  Scenario:  Cart API responds with a 401 with wrong authentication parametres
    Given User go to site "https://api.luckycart.com/cart/ticket" and validate 401 status codes

  @WithoutGame
  Scenario: Cart API responds with a 200, without game with a totalAti (total all tax inclusive) lower than 50
    Given  User go to site "https://api.luckycart.com/cart/ticket" and validate 200 status codes for without game

  @WithGame
  Scenario: Cart API responds with a 200, with game information  with a (total all tax inclusive) greater than 50
    Given  User go to site "https://api.luckycart.com/cart/ticket" and validate 200 status codes for with game


  @Front-End
  Scenario Outline: Web game page
    Given entered "<cartId>","<shopperId>","<shopperEmail>" Go to de basedesktopUrl
    And Home page  click on "Play now"
    And Game page  click on "Spin the wheel!"


    And Validate that the game result text
    Examples:
      | cartId | shopperId | shopperEmail         |
      | 32    | ayten       | muratcik43@luckycart.com |









