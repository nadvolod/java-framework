package com.aig.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriverWait pageWait;
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        pageWait = new WebDriverWait(this.driver, 10);
    }
}
