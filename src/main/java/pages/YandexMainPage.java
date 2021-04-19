package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMainPage {
    @FindBy(css = "a.desk-notif-card__login-new-item:nth-child(1)")
    public WebElement loginButton;

    public YandexMainPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);
    }
}
