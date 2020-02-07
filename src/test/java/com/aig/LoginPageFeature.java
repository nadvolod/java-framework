package com.aig;

import com.aig.page.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

//Make sure that all of your test classes classes end in *Test
class LoginPageTest extends BaseTest {
    @Test
    public void shouldLoad() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        assertTrue(loginPage.isLoaded());
    }
}