package test.auto.task.registration.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {

    public WebDriver driver;

    @FindBy(xpath = "//*[contains(text(), 'Sign out')]/..")
    private WebElement signOutBtn;

    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void signOut() {
        signOutBtn.click();
    }

}
