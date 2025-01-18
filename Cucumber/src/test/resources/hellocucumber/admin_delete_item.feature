Feature: Admin deletes an item from the store
    
    Scenario: Admin deletes an existing item successfully
        Given Admin is logged in
        When Admin navigates to the Product Page
        And Admin deletes the item "iPhone"
        Then "iPhone" should no longer appear in the product list

