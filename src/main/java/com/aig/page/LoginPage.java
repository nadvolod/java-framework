package com.aig.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void visit() {
        driver.get("http://www.saucedemo.com");
    }

    public boolean isLoaded() {
        WebElement sauceBot = driver.findElement(By.className("bot_column"));
        return pageWait.until(ExpectedConditions.visibilityOf(sauceBot)).isDisplayed();
    }

    public void login(String username, String password) {
        String userField = "[data-test='username']";
        String passField = "[data-test='password']";
        String loginBtn = "[value='LOGIN']";

        driver.findElement(By.cssSelector(userField)).sendKeys(username);
        driver.findElement(By.cssSelector(passField)).sendKeys(password);
        driver.findElement(By.cssSelector(loginBtn)).click();
    }
}
