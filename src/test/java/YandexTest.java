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
//        By test = yandexLoginPage.titleYandex;
//        test.wait();
        WebElement dynamicElement;
//        dynamicElement = (new WebDriverWait(driver, 20))
//                .until(ExpectedConditions.presenceOfElementLocated(test));
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
            yandexLoginPage.inputLogin.sendKeys("akuz0419.1");
            yandexLoginPage.buttonLogin.click();
        }
        if (yandexLoginPage.inputPassword.isDisplayed() && yandexLoginPage.inputPassword.isEnabled()) {
            yandexLoginPage.inputPassword.sendKeys("12021966");
            yandexLoginPage.buttonLogin.click();
        }
        if(yandexLoginPage.textAddedText.getText().contains("Ваш")) {
            if (yandexLoginPage.textAddedText.isDisplayed() && yandexLoginPage.dataT.isDisplayed() && yandexLoginPage.dataT.isEnabled()) {
                yandexLoginPage.dataT.click();
            }
        }
        if (yandexLoginPage.mailOpenButton.isDisplayed()) {
            yandexLoginPage.mailOpenButton.click();
        }
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.CONTROL, "i"));

//        while (!yandexMailPage.searchInput.isDisplayed()) {
//            try {
//                wait(20);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        WebDriverWait wait = new WebDriverWait(driver, 20);

        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to new tab

        if (yandexMailPage.searchInput.isDisplayed() && yandexMailPage.searchInput.isEnabled()) {
            yandexMailPage.searchInput.click();
            yandexMailPage.searchInput.sendKeys("Simbirsoft theme");
       }

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        if (yandexMailPage.findButton.isDisplayed()) {
            yandexMailPage.findButton.click();
        }



        if(yandexMailPage.notFound.isDisplayed()){

        }
        else {
            if (yandexMailPage.countMail.isDisplayed()){
                System.out.println(yandexMailPage.countMail.getText());
                System.out.println(yandexMailPage.countMail.getText());
            }
        }



//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(test));
//        yandexLoginPage.find("Калькулятор");
//        dynamicElement.click();
//        dynamicElement.click();

    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Description("Checking the title of the loaded page.")
    @Test
    @Ignore
    public void caseOne() {
        String expression = "(1+2)*3-40/5";
//        GoogleCalc.inputValue(expression);
//        Assert.assertEquals(calculatorGoogle.getResult(), "1" );
//        Assert.assertEquals(calculatorGoogle.getHistory(),"(1 + 2) × 3 - 40 ÷ 5 =");
    }

    @Test
    @Ignore
    public void caseTwo() {
        String expression = "6/0";
        GoogleCalc.inputValue(expression);
//        Assert.assertEquals(calculatorGoogle.getResult(), "Infinity");
//        Assert.assertEquals(calculatorGoogle.getHistory(),"6 ÷ 0 =");
    }

    @Test
    @Ignore
    public void caseThree() {
        String expression = "sin(";
        GoogleCalc.inputValue(expression);
//        Assert.assertEquals(calculatorGoogle.getResult(), "Error");
//        Assert.assertEquals(calculatorGoogle.getHistory(),"sin(()) =");
    }
}
