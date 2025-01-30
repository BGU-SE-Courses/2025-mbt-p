/* @provengo summon selenium */

  /**
 * user buys the item
 */
  bthread("UserActions", function () {
    let session = new SeleniumSession("userActions");
    session.start(URLstore);
    loginUser(session);
    checkout(session);

  });



/**
 * admin deletes an item from store
 */
bthread("AdminActions", function () {
    let session = new SeleniumSession("AdminActions");
    session.start(URLadmin);
    login(session);
    navigateToProducts(session);
    navigateToProduct(session); 
    deleteProduct(session); 

  });

