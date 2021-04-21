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

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class YandexTest {
    private static YandexMainPage yandexMainPage;
    private static YandexLoginPage yandexLoginPage;
    private static YandexMailPage yandexMailPage;
    private static WebDriver driver;

    @BeforeTest
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        yandexMainPage = new YandexMainPage(driver);
        driver.navigate().to("https://www.yandex.com/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Description("Checking the increase in the number of letters.")
    @Test
    public void caseIncreaseInNumberOfLetters() throws IOException {
        yandexLoginPage = new YandexLoginPage(driver);
        yandexMailPage = new YandexMailPage(driver);
        yandexMainPage.clickLoginButton();


        boolean inputLoginIsEnabled = yandexLoginPage.inputLoginIsEnabled();
        while (!yandexLoginPage.inputLoginIsDisplayed()) {
            try {
                wait(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (inputLoginIsEnabled) {
            yandexLoginPage.setLogin(Utlis.getPropValues("yandex.login"));
            yandexLoginPage.clickLoginButton();
        }
        if (yandexLoginPage.inputPasswordIsEnabled()) {
            yandexLoginPage.setPassword(Utlis.getPropValues("yandex.password"));
            yandexLoginPage.clickLoginButton();
        }
        if (yandexLoginPage.textAddedTextContainsValue("Ваш")) {
            if (yandexLoginPage.noMessageIsEnabled()) {
                yandexLoginPage.clickNoMessageButton();
            }
        }
        yandexLoginPage.clickMailOpenButton();
        yandexLoginPage.openInboxMail();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to new tab
        if (yandexMailPage.searchInputIsEnabled()) {
            yandexMailPage.clickSearchInput();
            yandexMailPage.searchThemeMail(Utlis.getPropValues("yandex.thememail"));
        }
        if (yandexMailPage.findButtonIsDisplayed()) {
            yandexMailPage.clickFindButton();
        }
        String countMail = "";
        //#ToDo Custom until for webelement
        if (!yandexMailPage.checkMailIsPresent()) {
            if (yandexMailPage.countMailIsDisplayed()) {
                countMail = yandexMailPage.getQuantityMail();
            }
        } else {
            //# todo if no mail exist (example first run)
            if (yandexMailPage.notFoundIsDisplayed()) {
                countMail = "0";
            }
        }

        if (yandexMailPage.writeMailButtonIsEnabled()) {
            yandexMailPage.clickWriteMailButton();
        }

        if (yandexMailPage.sendToInputIsEnabled()) {
            yandexMailPage.clickSendToInput();
            if (yandexMailPage.contactMailForSendContainsValue(Utlis.getPropValues("yandex.login"))) {
                yandexMailPage.clickContactMailForSend();
            }
            yandexMailPage.clickThemeMailForSend();
            yandexMailPage.setThemeMail(Utlis.getPropValues("yandex.thememail"));
            yandexMailPage.clickBodyMailForSend();
            //# toDo set regexp to constant
            yandexMailPage.setBodyMailForSend(yandexMailPage.textForSend(countMail.replaceAll("\\D+", "")));
            yandexMailPage.clickButtonForSendMail();

            yandexMailPage.clickLinkBackToMainPage();

            if (yandexMailPage.searchInputIsEnabled()) {
                yandexMailPage.clickSearchInput();
                yandexMailPage.searchThemeMail(Utlis.getPropValues("yandex.thememail"));
            }

            yandexMailPage.clickFindButton();


        }


        int wasMailQuantity = yandexMailPage.getDigitFromString(yandexMailPage.getWasQuantityMail());
        int nowMailQuantity = yandexMailPage.getDigitFromString(yandexMailPage.getQuantityMail());
        Assert.assertTrue(wasMailQuantity < nowMailQuantity);
    }

}
