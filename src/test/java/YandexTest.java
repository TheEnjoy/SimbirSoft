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
    private YandexMainPage yandexMainPage;
    private WebDriver driver;

    @BeforeTest
    public void setupClass() {
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
        YandexLoginPage yandexLoginPage = new YandexLoginPage(driver);
        YandexMailPage yandexMailPage = new YandexMailPage(driver);
        yandexMainPage.clickLoginButton();
        while (!yandexLoginPage.inputLoginIsDisplayed()) {
            try {
                wait(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        yandexLoginPage.authorization(Utlis.getPropValues("yandex.login"), Utlis.getPropValues("yandex.password"));
        if (yandexLoginPage.textAddedTextContainsValue("Ваш")) {
            if (yandexLoginPage.noMessageIsEnabled()) {
                yandexLoginPage.clickNoMessageButton();
            }
        }
        yandexLoginPage.clickAndOpenInboxMail();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to new tab
        yandexMailPage.searchThemeMail(Utlis.getPropValues("yandex.thememail"));
        yandexMailPage.clickFindButton();
        String countMail = "";
        if (!yandexMailPage.checkMailIsPresent()) {
            if (yandexMailPage.countMailIsDisplayed()) {
                countMail = yandexMailPage.getQuantityMail();
            }
        } else {
            if (yandexMailPage.notFoundIsDisplayed()) {
                countMail = "0";
            }
        }
        yandexMailPage.clickWriteMailButton();
        if (yandexMailPage.sendToInputIsEnabled()) {
            yandexMailPage.clickSendToInput();
            if (yandexMailPage.contactMailForSendContainsValue(Utlis.getPropValues("yandex.login"))) {
                yandexMailPage.clickContactMailForSend();
            }
            yandexMailPage.clickAndSetThemeMail(Utlis.getPropValues("yandex.thememail"));
            yandexMailPage.clickBodyMailForSend();
            yandexMailPage.clickAndsStBodyMailForSend(Utlis.textForSend(countMail.replaceAll("\\D+", "")));
            yandexMailPage.clickLinkBackToMainPage();
            yandexMailPage.searchMailInInput(Utlis.getPropValues("yandex.thememail"));
            yandexMailPage.clickFindButton();
        }
        int wasMailQuantity = Integer.parseInt(countMail);
        int nowMailQuantity = Utlis.getDigitFromString(yandexMailPage.getQuantityMail());
        Assert.assertTrue(wasMailQuantity < nowMailQuantity);
    }

}
