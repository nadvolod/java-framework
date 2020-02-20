package aig;

import com.aig.page.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class StepDefinitions{
    public RemoteWebDriver driver;

    @Given("a user opens the browser")
    public void a_user_opens_the_browser() {
        String sauceUsername = System.getenv("SAUCE_USERNAME");
        String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");

        ChromeOptions chromeOpts = new ChromeOptions();
        chromeOpts.setCapability(CapabilityType.PLATFORM_NAME, "windows 10");
        chromeOpts.setCapability(CapabilityType.BROWSER_VERSION, "latest");

        MutableCapabilities sauceOpts = new MutableCapabilities();
        sauceOpts.setCapability("username", sauceUsername);
        sauceOpts.setCapability("accessKey", sauceAccessKey);
        //TODO future addition
        //sauceOpts.setCapability("name", method.getName());
        sauceOpts.setCapability("build", "best-practices");
        sauceOpts.setCapability("tags", "['best-practices', 'best-practices']");

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY,  chromeOpts);
        capabilities.setCapability("sauce:options", sauceOpts);

        String sauceUrl = "https://ondemand.saucelabs.com/wd/hub";
        URL url = null;
        try {
            url = new URL(sauceUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = new RemoteWebDriver(url, capabilities);
    }

    @When("a user navigates to the Login Page")
    public void a_user_navigates_to_the_Login_Page() {
        new LoginPage(driver).visit();
    }

    @Then("the user sees the page rendered")
    public void the_user_sees_the_page_rendered() {
        assertTrue(new LoginPage(driver).isLoaded());
    }

}