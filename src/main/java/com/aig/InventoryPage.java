package com.aig;

import com.aig.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    public boolean isLoaded() {
        boolean isPageLogoVisible = pageWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("app_logo")));
        return !isPageLogoVisible;
    }
}
