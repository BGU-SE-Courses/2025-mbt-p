function login(session) {
    session.writeText(xpaths.loginWindow.usernameBox, adminCredentials[0]);
    session.writeText(xpaths.loginWindow.passwordBox, adminCredentials[1]);
    with(session) {
      click(xpaths.loginWindow.submitLogin)
    }
  }

  function navigateToProducts(session) {
    with(session) {
      click(xpaths.navigationSide.openMenu)
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
    }
  }


function addToCart(session) {
    session.writeText(xpathsu.search.searchBox, chosenProduct);
    with(session) {
    click(xpathsu.search.submitSearch)
    click(xpathsu.searchResults.addToCartButton)
    click(xpathsu.cart.cartButton)

    }
}

function checkout(session) {
    with(session) {
    click(xpathsu.cart.checkoutButton)
    click(xpathsu.checkout.guestCheckoutButton)
    }
}
