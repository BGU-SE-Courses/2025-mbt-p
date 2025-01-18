Feature: A user buys a product

    Scenario: User buys a product successfully
        Given User is on the Home Page
        When User logs in with "<Email>" "<Password>"
        And User adds "<Quantity>" units of "<Product>" to the cart and checks out
        And User selects an address for delivery: "Tomer" "bgu" "blabla" "Rishon" "123123" "Israel" "Jerusalem"
        And User confirms the order
        Then The cart should be empty

        Examples:
            | Email           | Password  | Product | Quantity |
            | tomer@bgu.com    | 12345678  |  iMac  | 1 |

    Scenario: User buys a product with insufficient stock
        Given User is on the Home Page
        When User logs in with "<Email>" "<Password>"
        When User adds "<Quantity>" units of "<Product>" to the cart and checks out
        Then an error message should be displayed saying "Products marked with *** are not available in the desired quantity or not in stock!"

        Examples:
            | Email           | Password  | Product | Quantity |
            | tomer@bgu.com    | 12345678  |  iMac  | 1000 |
