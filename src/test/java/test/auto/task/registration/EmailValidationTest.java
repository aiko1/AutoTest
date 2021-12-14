package test.auto.task.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.auto.task.registration.pages.AuthenticationPage;
import test.auto.task.registration.pages.MainPage;
import test.auto.task.registration.pages.ProfilePage;
import test.auto.task.registration.pages.RegistrationPage;
import test.auto.task.registration.util.ConfProperties;

public class EmailValidationTest {

    public static MainPage mainPage;
    public static AuthenticationPage authenticationPage;
    public static RegistrationPage registrationPage;
    public static ProfilePage profilePage;
    public static WebDriver driver;

    /**
     * initialization once before all tests
     */
    @BeforeAll
    public static void init() {
        //get driver location
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        driver = new ChromeDriver();

        mainPage = new MainPage(driver);
        authenticationPage = new AuthenticationPage(driver);
        registrationPage = new RegistrationPage(driver);
        profilePage = new ProfilePage(driver);
        driver.manage().window().maximize();

        //open main page
        driver.get(ConfProperties.getProperty("url"));
    }

    /**
     * After running all tests, close the browser window
     */
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    /**
     * test method for test case 1 - Email field validation
     */
    @ParameterizedTest
    @ValueSource(strings = {"aizhangmail.com", " aizhan@@gmail.com", "aizhan@gmail"})
    public void errorMsgWhenInvalidEmailTest(String email) {
        //click the 'Sign in' button
        mainPage.signIn();

        //input email value
        authenticationPage.inputEmail(email);

        //click the 'Create an account' button
        authenticationPage.createAccount();

        //wait till error msg appears
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //check if page contains an error message
        assertEquals(ConfProperties.getProperty("invalid_email_msg"),
            authenticationPage.driver.findElement(By.xpath("//*[@id='create_account_error']/ol/li"))
                .getText());
    }

    /**
     * method for preparing to test case 2 - Verifying that the system does not store duplicate
     * email values
     */
    private void setupForDuplicationTest() {
        //In the Main page click the 'Sign in' button.
        mainPage.signIn();

        //Enter email address
        authenticationPage.inputEmail(ConfProperties.getProperty("email"));

        //Click the Create an account button.
        authenticationPage.createAccount();

        //wait for Registration page loading
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Fill in all form fields.
        registrationPage.firstName("Name").lastName("LastName").password("Aizhan").address("Street")
            .city("Washington").state("Washington").postcode("20002").phone("+122322342");

        //Click the Register button.
        registrationPage.register();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Log out by clicking the 'Sign out' button.
        //The Authentication page opens.
        profilePage.signOut();
    }

    /**
     * test method for test case 2 - Verifying that the system does not store duplicate email
     * values
     */
    @Test
    public void errorMsgWhenEmailAlreadyExistsTest() {
        setupForDuplicationTest();

        //In the email field, enter the email of the just created account.
        authenticationPage.inputEmail(ConfProperties.getProperty("email"));

        //Click the Create an account button.
        authenticationPage.createAccount();

        //wait till error msg appears
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //check if page contains an error message
        assertTrue(
            authenticationPage.driver.findElement(By.xpath("//*[@id='create_account_error']/ol/li"))
                .getText().contains(ConfProperties.getProperty("email_already_exists_msg")));
    }
}