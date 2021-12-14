package test.auto.task.registration.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage {

    public WebDriver driver;

    @FindBy(xpath = "//*[contains(@id, 'customer_firstname')]")
    private WebElement firstNameField;

    @FindBy(xpath = "//*[contains(@id, 'customer_lastname')]")
    private WebElement lastNameField;

    @FindBy(xpath = "//*[contains(@id, 'passwd')]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[contains(@id, 'address1')]")
    private WebElement addressField;

    @FindBy(xpath = "//*[contains(@id, 'city')]")
    private WebElement cityField;

    @FindBy(xpath = "//*[contains(@id, 'postcode')]")
    private WebElement postcodeField;

    @FindBy(xpath = "//*[contains(@id, 'phone_mobile')]")
    private WebElement phoneField;

    @FindBy(xpath = "//*[contains(text(), 'Register')]/..")
    private WebElement registerBtn;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public RegistrationPage firstName(String firstName) {
        firstNameField.sendKeys(firstName);
        return this;
    }

    public RegistrationPage lastName(String lastName) {
        lastNameField.sendKeys(lastName);
        return this;
    }

    public RegistrationPage password(String pass) {
        passwordField.sendKeys(pass);
        return this;
    }

    public RegistrationPage address(String address) {
        addressField.sendKeys(address);
        return this;
    }

    public RegistrationPage city(String city) {
        cityField.sendKeys(city);
        return this;
    }

    public RegistrationPage state(String state) {
        Select select = new Select(driver.findElement(By.id("id_state")));
        select.selectByVisibleText(state);
        return this;
    }

    public RegistrationPage postcode(String postcode) {
        postcodeField.sendKeys(postcode);
        return this;
    }

    public RegistrationPage phone(String phone) {
        phoneField.sendKeys(phone);
        return this;
    }

    public void register() {
        registerBtn.click();
    }

}
