package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMailPage {
    @FindBy(css = "input[placeholder*=\"Поиск\"]")
    public WebElement searchInput;

    @FindBy(css = "button[class$=\"search-input__form-button\"]")
    public WebElement findButton;

    @FindBy(css = "div[class*=\"b-search-not-found\"]")
    public WebElement notFound;

    @FindBy(css = "span[class*=\"mail-MessagesSearchInfo-Title_misc\"]")
    public WebElement countMail;

    @FindBy(css = "a[title*=\"Написать\"]")
    public WebElement writeMailButton;

    @FindBy(css = ".ComposeRecipients-ToField > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
    public WebElement sendToInput;

    @FindBy(css = "div.ComposeContactsList-Item:nth-child(1)")
    public WebElement contactMailForSend;

    @FindBy(css = ".composeTextField")
    public WebElement themeMailForSend;

    @FindBy(css = ".cke_wysiwyg_div")
    public WebElement bodyMailForSend;

    @FindBy(css = ".ComposeControlPanelButton-Button_action")
    public WebElement buttonForSendMail;

    @FindBy(xpath = "/html/body/div[2]/div[8]/div/div[3]/div[3]/div[2]/div[5]/div[1]/div/div/div[2]/div/div[2]/div/div/div/a/div/span[2]/div/span/span[2]/span")
    public WebElement lastFindMail;

    @FindBy(css = "body > div.popup2.popup2_view_classic.popup2_direction_bottom-left.popup2_visible_yes.popup2_target_position.popup2_motionless.ComposeDoneScreen > div > div.ComposeDoneScreen-Header > div.ComposeDoneScreen-Actions > a")
    public WebElement linkBackToMainPage;

    public static final String MY_MAIL = "1@yandex.ru";
    public static final String MY_PASSWORD = "123";
    public static final String THEME_MAIL = "Simbirsoft theme";
    public YandexMailPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);
    }

    public String textForSend(String numberText){
        int number = Integer.parseInt(numberText);
        int lastDigit = number % 100 / 10;
        if (lastDigit == 1)
        {
            return String.format("Найдено %s писем", numberText);
        }
        switch (number % 10)
        {
            case 1:
                return String.format("Найдено %s письмо", numberText);
            case 2:
            case 3:
            case 4:
                return String.format("Найдено %s письма", numberText);
            default:
                return String.format("Найдено %s писем", numberText);
        }
    }
}
