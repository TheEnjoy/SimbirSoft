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
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(yandexLoginPage.inputLoginBy));
        yandexLoginPage.authorization(Utlis.getPropValues("yandex.login"), Utlis.getPropValues("yandex.password"));
        yandexLoginPage.skipPopupIfExist();
        yandexLoginPage.clickAndOpenInboxMail();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to new tab
        yandexMailPage.searchThemeMail(Utlis.getPropValues("yandex.thememail"));

        int countMail = yandexMailPage.generateAndReturnQuantityMail();
        yandexMailPage.clickWriteMailButton();
        yandexMailPage.sendMail(Utlis.getPropValues("yandex.login"), Utlis.getPropValues("yandex.thememail"), Utlis.generateTextForSend(countMail));

        int wasMailQuantity = countMail;
        int nowMailQuantity = yandexMailPage.getCurrentQuantityMail(Utlis.getPropValues("yandex.thememail"));
        Assert.assertTrue(wasMailQuantity < nowMailQuantity);
    }

}
