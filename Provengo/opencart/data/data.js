/*
 *  This is a good place to put common test data, project-wide constants, etc.
 */

const URLstore = 'http://localhost/opencart/';
const URLadmin = 'http://localhost/opencart/admin2/';

const userCredentials = ["keren@test.com", "Quality2025"];
const adminCredentials = ["admin", "1234"];

const chosenProduct = "iPhone";


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
        productLocation: '//*[@id="form-product"]/div[1]/table/tbody/tr[6]/td[1]/input'
      },

    deleteProduct: {
      deleteButton: '//*[@id="content"]/div[1]/div/div/button[3]'
    }
}

const xpathsu = {
    search: {
      searchBox: '//input[@name="search"]',
      submitSearch: '//form[1]/button[1]'
    },

    searchResults: {
      addToCartButton: '//form[1]/div[1]/button[1]'
    },

    cart: {
      cartButton: '//button[@id="header-cart"]/div/button',
      checkoutButton: '//*[@id="header-cart"]/div/ul/li/div/p/a[2]/strong'
    },

    checkout: {
      guestCheckoutButton: '//*[@id="content"]/div[3]/div[2]/a' //should not work
    }
  }
