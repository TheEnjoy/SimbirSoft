package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class YandexLoginPage {
//    @FindBy(css = ".passp-title")
//public By titleYandex = By.cssSelector(".passp-title");
    @FindBy(css = "#passp-field-login")
    public WebElement inputLogin;

    @FindBy(css = "div[class$=\"passp-sign-in-button\"]")
    public WebElement buttonLogin;

    @FindBy(css = "input[id=\"passp-field-passwd\"]")
    public WebElement inputPassword;

    @FindBy(css = "h1[class*='passp-title']")
    public WebElement textAddedText;

    @FindBy(css = "div[data-t*=\"email_skip\"]")
    public WebElement dataT;

    @FindBy(css = "div[class*=\"desk-notif-card__mail-title\"]")
    public WebElement mailOpenButton;


    public YandexLoginPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);
    }

//    public boolean isElementPresent(By by) {
//        boolean isPresent = true;
//        //search for elements and check if list is empty
//        if (this.findElements(by).isEmpty()) {
//            isPresent = false;
//        }
//        //rise back implicitly wait time
//        this.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
//        return isPresent;
//    }

//    public WebElement myFindElement(WebDriver driver, WebElement by, int timeoutInSeconds)
//    {
//        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//        wait.until( ExpectedConditions.presenceOfElementLocated(by) ); //throws a timeout exception if element not present after waiting <timeoutInSeconds> seconds
//        return driver.findElement(by);
//    }


}
