package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexLoginPage {
    WebDriver driver;

    @FindBy(css = "#passp-field-login")
    private WebElement inputLogin;

    @FindBy(css = "div[class$=\"passp-sign-in-button\"]")
    private WebElement buttonLogin;

    @FindBy(css = "input[id=\"passp-field-passwd\"]")
    private WebElement inputPassword;

    @FindBy(css = "h1[class*='passp-title']")
    private WebElement textAddedText;

    @FindBy(css = "div[data-t*=\"email_skip\"]")
    private WebElement noMessage;

    @FindBy(css = "div[class*=\"desk-notif-card__mail-title\"]")
    private WebElement mailOpenButton;

    // Locator for send keys to body;
    By body = By.cssSelector("body");

    //#ToDo Create custom waiting until WebElement
    public YandexLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    //enabled region
    public Boolean inputLoginIsEnabled() {
        return inputLogin.isEnabled();
    }

    public Boolean buttonLoginIsEnabled() {
        return buttonLogin.isEnabled();
    }

    public Boolean inputPasswordIsEnabled() {
        return inputPassword.isEnabled();
    }

    public Boolean textAddedTextIsEnabled() {
        return textAddedText.isEnabled();
    }

    public Boolean noMessageIsEnabled() {
        return noMessage.isEnabled();
    }

    public Boolean mailOpenButtonIsEnabled() {
        return mailOpenButton.isEnabled();
    }

    //isDisplayed region
    public Boolean inputLoginIsDisplayed() {
        return inputLogin.isDisplayed();
    }

    //send key region
    public void setLogin(String email) {
        inputLogin.sendKeys(email);
    }

    public void setPassword(String password) {
        inputPassword.sendKeys(password);
    }

    // click region
    public void clickLoginButton() {
        buttonLogin.click();
    }

    public void clickNoMessageButton() {
        noMessage.click();
    }

    public void clickMailOpenButton() {
        mailOpenButton.click();
    }

    //contains region
    public Boolean textAddedTextContainsValue(String value) {
        return textAddedText.getText().contains(value);
    }

    //another methods
    public void openInboxMail() {
        driver.findElement(body).sendKeys(Keys.chord(Keys.CONTROL, "i"));
    }





}
