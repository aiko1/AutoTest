package test.auto.task.registration.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    public WebDriver driver;

    @FindBy(xpath = "//*[contains(@class, 'login')]")
    private WebElement signInBtn;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void signIn() {
        signInBtn.click();
    }

}
