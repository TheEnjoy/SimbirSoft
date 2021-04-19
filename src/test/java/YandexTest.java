import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class YandexTest {
    private static YandexMainPage yandexMainPage;
    private static YandexLoginPage yandexLoginPage;
    private static YandexMailPage yandexMailPage;
    private static WebDriver driver;

    @BeforeSuite
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeSuite
    public void setupTest() {
        driver = new ChromeDriver();
        yandexMainPage = new YandexMainPage(driver);
        driver.navigate().to("https://www.yandex.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeTest
    public void loginYandex() {
        yandexLoginPage = new YandexLoginPage(driver);
        yandexMailPage = new YandexMailPage(driver);
        yandexMainPage.loginButton.click();

        boolean inputLoginIsDisplayed = yandexLoginPage.inputLogin.isDisplayed();
        boolean inputLoginIsEnabled = yandexLoginPage.inputLogin.isEnabled();
        while (!yandexLoginPage.inputLogin.isDisplayed()) {
            try {
                wait(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (inputLoginIsDisplayed && inputLoginIsEnabled) {
            yandexLoginPage.inputLogin.sendKeys(YandexMailPage.MY_MAIL);
            yandexLoginPage.buttonLogin.click();
        }
        if (yandexLoginPage.inputPassword.isDisplayed() && yandexLoginPage.inputPassword.isEnabled()) {
            yandexLoginPage.inputPassword.sendKeys(YandexMailPage.MY_PASSWORD);
            yandexLoginPage.buttonLogin.click();
        }
        if(yandexLoginPage.textAddedText.getText().contains("Ваш")) {
            if (yandexLoginPage.textAddedText.isDisplayed() && yandexLoginPage.noMessage.isDisplayed() && yandexLoginPage.noMessage.isEnabled()) {
                yandexLoginPage.noMessage.click();
            }
        }
        if (yandexLoginPage.mailOpenButton.isDisplayed()) {
            yandexLoginPage.mailOpenButton.click();
        }
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.CONTROL, "i"));

        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to new tab
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        if (yandexMailPage.searchInput.isDisplayed() && yandexMailPage.searchInput.isEnabled()) {
            yandexMailPage.searchInput.click();
            yandexMailPage.searchInput.sendKeys(YandexMailPage.THEME_MAIL);
       }

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        if (yandexMailPage.findButton.isDisplayed()) {
            yandexMailPage.findButton.click();
        }


        String countMail = "";
        //#ToDo Custom until for webelement
        if(!driver.findElements(By.cssSelector("span[class*=\"mail-MessagesSearchInfo-Title_misc\"]")).isEmpty()){
            if (yandexMailPage.countMail.isDisplayed()){
                System.out.println(yandexMailPage.countMail.getText());
                System.out.println(yandexMailPage.countMail.getText());
                countMail = yandexMailPage.countMail.getText();

            }
        }
        else {
            //# todo if no mail exist (example first run)
            if (yandexMailPage.notFound.isDisplayed()){
                countMail = "0";
            }
        }

        if(yandexMailPage.writeMailButton.isDisplayed() && yandexMailPage.writeMailButton.isEnabled()){
            yandexMailPage.writeMailButton.click();
        }

        if(yandexMailPage.sendToInput.isDisplayed() && yandexMailPage.sendToInput.isEnabled()){
            yandexMailPage.sendToInput.click();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            if(  yandexMailPage.contactMailForSend.getText().contains(YandexMailPage.MY_MAIL)) {
                yandexMailPage.contactMailForSend.click();
            }
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            yandexMailPage.themeMailForSend.click();
            yandexMailPage.themeMailForSend.sendKeys(YandexMailPage.THEME_MAIL);
            yandexMailPage.bodyMailForSend.click();
            //# toDo set regexp to constant
            yandexMailPage.bodyMailForSend.sendKeys(yandexMailPage.textForSend(countMail.replaceAll("\\D+","")));
            yandexMailPage.buttonForSendMail.click();
            yandexMailPage.linkBackToMainPage.click();

            if (yandexMailPage.searchInput.isDisplayed() && yandexMailPage.searchInput.isEnabled()) {
                yandexMailPage.searchInput.click();
                yandexMailPage.searchInput.sendKeys(YandexMailPage.THEME_MAIL);
            }

            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

            if (yandexMailPage.findButton.isDisplayed()) {
                yandexMailPage.findButton.click();
            }

        }

    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Description("Checking the increase in the number of letters.")
    @Test
    public void caseIncreaseInNumberOfLetters() {
       int wasMailQuantity = Integer.parseInt(yandexMailPage.lastFindMail.getText().replaceAll("\\D+",""));
       int nowMailQuantity =  Integer.parseInt(yandexMailPage.countMail.getText().replaceAll("\\D+",""));
        Assert.assertTrue(wasMailQuantity < nowMailQuantity);
    }

}
