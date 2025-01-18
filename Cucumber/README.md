# Testing OpenCart using Cucumber
This directory contains the cucumber files for testing the Inventory Management & Purchase Functionality modules of the OpenCart application.

## Running the tests
Run ```mvn test``` to run all the tests.

## Feature files
The behaviors that we tested are in the feature files that inside the [resources/hellocucumber](resources/hellocucumber) directory. See the files for a detailed description of the tests.

admin_delete_item.feature: 
    We made an assumption that there is admin named "admin" with password "admin".

user_buy_item.feature: 
    We made an assumption that there is a registered user with email: "tomer@bgu.com" and password: "12345678". We registered him beforehand.


## Step files
The step files in the [src/test/java/hellocucumber](src/test/java/hellocucumber) directory contain the code that defines how each sentence in the feature files is translated to Selenium actions. See the files for a detailed description of the implementation.