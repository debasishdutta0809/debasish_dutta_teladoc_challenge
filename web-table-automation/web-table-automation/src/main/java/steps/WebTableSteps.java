package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;

public class WebTableSteps {
    WebDriver driver;

    @Given("I am on the web table page")
    public void i_am_on_the_web_table_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.way2automation.com/angularjs-protractor/webtables/");
    }

    @When("I add a user with details {string}, {string}, {string}, {string}")
    public void i_add_a_user_with_details(String firstName, String lastName, String email, String age) {
        // Find the add button and click
        driver.findElement(By.cssSelector(".btn.btn-link.pull-right")).click();
        
        // Fill the form
        driver.findElement(By.name("FirstName")).sendKeys(firstName);
        driver.findElement(By.name("LastName")).sendKeys(lastName);
        driver.findElement(By.name("Email")).sendKeys(email);
        driver.findElement(By.name("Age")).sendKeys(age);
        
        // Submit the form
        driver.findElement(By.cssSelector(".btn.btn-success")).click();
    }

    @Then("I should see the user {string} in the table")
    public void i_should_see_the_user_in_the_table(String firstName) {
        WebElement table = driver.findElement(By.tagName("table"));
        boolean userFound = table.getText().contains(firstName);
        Assert.assertTrue("User was not added to the table", userFound);
        driver.quit();
    }

    @When("I delete the user {string}")
    public void i_delete_the_user(String userName) {
        WebElement table = driver.findElement(By.tagName("table"));
        WebElement row = table.findElement(By.xpath("//td[text()='" + userName + "']/.."));
        row.findElement(By.cssSelector(".btn.btn-danger")).click();
        driver.switchTo().alert().accept();  // Confirm the alert
    }

    @Then("I should not see the user {string} in the table")
    public void i_should_not_see_the_user_in_the_table(String userName) {
        WebElement table = driver.findElement(By.tagName("table"));
        boolean userFound = table.getText().contains(userName);
        Assert.assertFalse("User was not deleted from the table", userFound);
        driver.quit();
    }
}
