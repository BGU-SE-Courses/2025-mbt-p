/* @provengo summon selenium */

/**
 * admin deletes an item from store
 */
bthread("AdminActions", function () {
    let session = new SeleniumSession("AdminActions");
    session.start(URLadmin);
    login(session);
    navigateToProducts(session); //products list
    navigateToProduct(session); //pick iphone
    deleteProduct(session); //delete iphone
    Event("AdminActionsCompleted");

  });

  /**
 * user buys the item
 */
bthread("UserActions", function () {
    let session = new SeleniumSession("userActions");
    session.start(URLstore);
    addToCart(session);
    sync({
      waitForL: Event("AdminActionsCompleted"),  // Wait for admin stuff to finish
      block: Event("AdminActionsCompleted")      // Block until admin stuff is completed
    });

    checkout(session);
  });


  //we assume the user already have the item in his cart
