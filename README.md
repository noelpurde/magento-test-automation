# Selenium Mini Project

## Overview

This project demonstrates the automation of 6 e-commerce website scenarios using Selenium and Java. The framework follows the Page Object Model (POM) structure, using TestNG for test execution and assertions. It covers account creation, login, page filters, wish list management, shopping cart operations, and more.

## Project Requirements

1. **Automation Framework**: Automate 6 test scenarios using Selenium and Java.
2. **JUnit**: Use JUnit as the test framework.
3. **Page Object Model (POM)**: Organize code with the Page Object Model structure.
4. **Assertions**: Use assertions to verify test results.
5. **Wait Methods**: Implement dynamic waits (avoid `Thread.sleep()`).
6. **Screenshot Capture**: Capture screenshots on test failure.
7. **Reporting (Optional)**: Configure test reports.
8. **Additional Features**: Enhance the project with extra features if desired.

## Tests Overview

### Test 1: Create an Account
- Navigate to the website and create an account.
- Verify success message and log out.

### Test 2: Sign In
- Sign in with credentials from Test 1.
- Verify username is displayed and log out.

### Test 3: Check Page Filters
- Apply filters on the products page (color, price range) and verify product details.

### Test 4: Wish List Test
- Add items to the wish list and verify the correct number of items in the user profile.

### Test 5: Shopping Cart Test
- Add items to the shopping cart and verify the order total.

### Test 6: Empty Shopping Cart Test
- Remove items from the cart and verify it's empty at the end.

## Project Structure

```bash
magento-test-automation
├── .idea
├── main
│   └── java
│       └── pages
│           ├── BasePage.java
│           ├── HomePage.java
│           ├── JacketsPage.java
│           ├── ProductsPage.java
│           ├── RegistrationPage.java
│           ├── ShoppingCartPage.java
│           ├── SignInPage.java
│           └── WishListPage.java
│       └── utilities
│           └── resources
│       └── screenshots
│           └── screenshot.png
├── test
│   └── java
│       ├── BaseTest.java
│       ├── FilterTest.java
│       ├── RegistrationTest.java
│       └── SignInTest.java
├── target
├── .gitignore
├── pom.xml
