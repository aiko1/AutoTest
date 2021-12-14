package test.auto.task.registration.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationPage {

    public WebDriver driver;

    @FindBy(xpath = "//*[@id='email_create']")
    private WebElement emailField;

    @FindBy(xpath = "//*[contains(@class, 'btn btn-default button button-medium exclusive')]")
    private WebElement createAccountBtn;

    public AuthenticationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }

    public void createAccount() {
        createAccountBtn.click();
    }

}
