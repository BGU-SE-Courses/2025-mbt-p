


function login(session) {
    session.writeText(xpaths.loginWindow.usernameBox, adminCredentials[0]);
    session.writeText(xpaths.loginWindow.passwordBox, adminCredentials[1]);
    with(session) {
      click(xpaths.loginWindow.submitLogin)
      Ctrl.doSleep(2000);
      click(xpaths.closeSecuriyWindow.closeWindow)
    }
  }

  function ensureProductDeleted(session) {
    session.writeText(xpaths.ensureDeleted.searchBox, deletedProduct);
    with(session) {
        click(xpaths.ensureDeleted.submitSearch);
    }
  }

  function navigateToProducts(session) {
    with(session) {
      click(xpaths.navigationSide.catalogChoice)
      click(xpaths.navigationSide.productsChoice)
    }
  }



  function navigateToProduct(session) {
    with(session) {
      click(xpaths.selectProduct.productLocation)
    }
  }

  function deleteProduct(session) {
    with(session) {
      click(xpaths.deleteProduct.deleteButton)  
      Ctrl.doPause("Please confirm the deletion of the product manually")

    } 

    ensureProductDeleted(session);
  }




// function addToCart(session) {
//     session.writeText(xpathsu.search.searchBox, chosenProduct);
//     with(session) {
//     click(xpathsu.search.submitSearch)
//     click(xpathsu.searchResults.addToCartButton)
//     click(xpathsu.cart.cartButton)

//     }
// }

function checkout(session) {
  with(session) {
      click(xpathsu.cart.cartButton)
      click(xpathsu.cart.checkoutButton);
      chooseExistingAddress(session);
      selectShippingMethod(session);
      Ctrl.doSleep(5000);
      selectPaymentMethod(session);
      Ctrl.doSleep(5000);
      confirmOrder(session);
  }
}

function chooseExistingAddress(session) {
  session.click(xpathsu.existingInfo.chooseAddress);
  session.click(xpathsu.existingInfo.Address);
}

function selectShippingMethod(session) {
  session.click(xpathsu.shippingMethod.choose);
  Ctrl.doSleep(4000);
  session.click(xpathsu.shippingMethod.confirm);
  Ctrl.doSleep(15000);
}

function selectPaymentMethod(session) {
  Ctrl.doSleep(5000);
  session.click(xpathsu.paymentMethos.choose);
  Ctrl.doSleep(3000);
  session.click(xpathsu.paymentMethos.confirm);
  Ctrl.doSleep(3000);
}

function confirmOrder(session) {
  session.click(xpathsu.confirmOrder.confirm);
}


function loginUser(session) {
  session.click(xpathsu.loginWindow.accountButton);
  session.click(xpathsu.loginWindow.loginButton);
  session.writeText(xpathsu.loginWindow.usernameBox, userCredentials[0]);
  session.writeText(xpathsu.loginWindow.passwordBox, userCredentials[1]);
  session.click(xpathsu.loginWindow.submitLogin);

  Ctrl.doSleep(5000);

}
