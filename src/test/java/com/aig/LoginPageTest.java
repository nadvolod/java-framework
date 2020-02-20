package com.aig;

import com.aig.page.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

//Make sure that all of your test classes classes end in *Test
public class LoginPageTest extends BaseTest {
    @Test
    public void shouldLoad() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        assertTrue(loginPage.isLoaded());
    }
    @Test
    public void shouldLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        assertTrue(loginPage.isLoaded());

        loginPage.login("standard_user", "secret_sauce");
        //Failure messages on assertions are important in Java
        assertTrue(new InventoryPage(driver).isLoaded(),
                "Inventory page should be visible since we logged in");
    }
}