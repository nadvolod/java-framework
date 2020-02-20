package com.aig;

import com.aig.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    public boolean isLoaded() {
        WebElement logo = pageWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("app_logo")));
        return logo.isDisplayed();  //TODO redundant
    }
}
