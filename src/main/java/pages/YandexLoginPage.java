package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexLoginPage {

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

    //#ToDo Create custom waiting until WebElement
    public YandexLoginPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);
    }

}
