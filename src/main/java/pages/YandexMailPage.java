package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMailPage {
    @FindBy(css = "input[placeholder*=\"Поиск\"")
    public WebElement searchInput;

    @FindBy(css = "button[class$=\"search-input__form-button\"]")
    public WebElement findButton;

    @FindBy(css = "div[class*=\"b-search-not-found\"]")
    public WebElement notFound;

    @FindBy(css = "span[class*=\"mail-MessagesSearchInfo-Title_misc\"]")
    public WebElement countMail;

    public YandexMailPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);
    }
}
