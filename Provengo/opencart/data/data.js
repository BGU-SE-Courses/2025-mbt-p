/*
 *  This is a good place to put common test data, project-wide constants, etc.
 */

const URLstore = 'http://localhost/';
const URLadmin = 'http://localhost/admin';

const userCredentials = ["tomer@bgu.com", "12345678"];
const adminCredentials = ["admin", "admin"];

const chosenProduct = "iPhone";
const deletedProduct = "Palm Treo Pro";




const xpaths = {
    loginWindow: {
      usernameBox:  '//input[@name="username"]',
      passwordBox:  '//input[@name="password"]',
      submitLogin:  '//button[@type="submit"]'
    },

    navigationSide: {
      openMenu: '//*[@id=\'button-menu\']/i[1]',
      catalogChoice:  '//*[@id=\'menu-catalog\']/a[1]',
      productsChoice: '//div[2]/nav[1]/ul[1]/li[2]/ul[1]/li[2]/a[1]'
    },

    selectProduct: {
        productLocation: '//*[@id="form-product"]/div[1]/table/tbody/tr[10]/td[1]/input'
    },

    deleteProduct: {
      deleteButton: '//*[@id="content"]/div[1]/div/div/button[3]',
      okButton: "//button[text()='OK']"
    },

    ensureDeleted: {
      searchBox: '//*[@id="input-name"]',
      submitSearch: '//*[@id="button-filter"]'
    },

    closeSecuriyWindow: {
      closeWindow: '//*[@id="modal-security"]/div/div/div[1]/button'
    }
}

const xpathsu = {
  loginWindow: {
    accountButton:  '//*[@id="top"]/div/div/div[2]/ul/li[2]/div/a/span',
    loginButton:  '//*[@id="top"]/div/div/div[2]/ul/li[2]/div/ul/li[2]/a',
    usernameBox:  '//*[@id="input-email"]',
    passwordBox:  '//*[@id="input-password"]',
    submitLogin:  '//*[@id="form-login"]/div[3]/button'
  },

  // search: {
  //   searchBox: '//input[@name="search"]',
  //   submitSearch: '//form[1]/button[1]'
  // },

  // searchResults: {
  //   addToCartButton: '//form[1]/div[1]/button[1]'
  // },

  cart: {
    cartButton: '//*[@id="cart"]/div/button',
    checkoutButton: '//*[@id="cart"]/div/ul/li/div/p/a[2]/strong'
  },

  checkout: {
    guestCheckoutButton: '//*[@id="content"]/div[3]/div[2]/a'

  },

  // info: {
  //     newAddress: '//*[@id="input-shipping-new"]',
  //     firstName: '//*[@id="input-firstname"]',
  //     lastName: '//*[@id="input-lastname"]',
  //     email: '//*[@id="input-email"]',
  //     address: '//*[@id="input-shipping-address-1"]',
  //     city: '//*[@id="input-shipping-city"]',
  //     postcode: '//*[@id="input-shipping-postcode"]',
  //     country: '//*[@id="input-shipping-country"]',
  //     state: '//*[@id="input-shipping-zone"]',
  //     password: '//*[@id="input-password"]',
  //     confirmOrderButton: '//*[@id="button-register"]',
  //     privacyPolicyCheckbox: '//*[@id="register-agree"]'
  // },

  existingInfo: {
      chooseAddress: '//*[@id="input-shipping-address"]',
      Address: '//*[@id="input-shipping-address"]/option[2]'
  },

  shippingMethod: {
      choose: '//*[@id="button-shipping-methods"]',
      confirm: '//*[@id="button-shipping-method"]'
  },

  paymentMethos: {
      choose: '//*[@id="button-payment-methods"]',
      confirm: '//*[@id="button-payment-method"]'
  },

  confirmOrder: {
      confirm: '//*[@id="button-confirm"]'
  }



  }
