package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexMailPage {

    @FindBy(xpath = ".//input[@class='textinput__control']")
    public WebElement searchInput;

    @FindBy(css = "button[class$=\"search-input__form-button\"]")
    public WebElement findButton;

    @FindBy(css = "div[class*=\"b-search-not-found\"]")
    public WebElement notFound;

    @FindBy(css = "span[class*=\"mail-MessagesSearchInfo-Title_misc\"]")
    public WebElement countMail;

    //    @FindBy(css = "a[title*=\"Написать\"]")
    @FindBy(css = "a[href*=\"#compose\"]")
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

    @FindBy(xpath = "(.//span[contains(@class, 'mail-MessageSnippet-Item') and contains(@class, 'mail-MessageSnippet-Item_firstline')])[1]")
    public WebElement lastFindMail;

    @FindBy(xpath = "(.//a[contains(@class, 'link_theme_normal')])[1]")
    public WebElement linkBackToMainPage;

    protected WebDriver driver;

    By messagesSearchInfoTitle = By.cssSelector("span[class*=\"mail-MessagesSearchInfo-Title_misc\"]");

    public YandexMailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean contactMailForSendContainsValue(String value) {
        return contactMailForSend.getText().contains(value);
    }

    public Boolean searchInputIsEnabled() {
        return searchInput.isEnabled();
    }

    public Boolean writeMailButtonIsEnabled() {
        return writeMailButton.isEnabled();
    }

    public Boolean sendToInputIsEnabled() {
        return sendToInput.isEnabled();
    }

    public void clickSendToInput() {
        sendToInput.click();
    }

    public void clickSearchInput() {
        searchInput.click();
    }

    public void clickThemeMailForSend() {
        themeMailForSend.click();
    }

    public void clickContactMailForSend() {
        contactMailForSend.click();
    }

    public void clickWriteMailButton() {
        writeMailButton.click();
    }

    public void clickFindButton() {
        findButton.click();
    }

    public void clickBodyMailForSend() {
        bodyMailForSend.click();
    }

    public void clickButtonForSendMail() {
        buttonForSendMail.click();
    }

    public void clickLinkBackToMainPage() {
        linkBackToMainPage.click();
    }

    public void searchThemeMail(String themeMail) {
        searchInput.sendKeys(themeMail);
        clickFindButton();
    }

    public void setThemeMail(String themeMail) {
        themeMailForSend.sendKeys(themeMail);
    }

    public void setBodyMailForSend(String themeMail) {
        bodyMailForSend.sendKeys(themeMail);
    }

    public Boolean findButtonIsDisplayed() {
        return findButton.isDisplayed();
    }

    public Boolean notFoundIsDisplayed() {
        return notFound.isDisplayed();
    }

    public Boolean countMailIsDisplayed() {
        return countMail.isDisplayed();
    }

    public String getQuantityMail() {
        return countMail.getText();
    }

    public Boolean checkMailIsPresent() {
        return driver.findElements(messagesSearchInfoTitle).isEmpty();
    }

    public void searchMailInInput(String themeMail) {
        searchThemeMail(themeMail);
    }

    public void clickAndSetThemeMail(String themeMail) {
        setThemeMail(themeMail);
    }

    public void clickAndsStBodyMailForSend(String textForSend) {
        setBodyMailForSend(textForSend);
        clickButtonForSendMail();

    }

    public int generateAndReturnQuantityMail() {
        if (!checkMailIsPresent()) {
            if (countMailIsDisplayed()) {
                return Integer.parseInt(getQuantityMail().replaceAll("\\D+", ""));
            }
        } else {
            if (notFoundIsDisplayed()) {
                return 0;
            }
        }
        return 0;
    }

    public void sendMail(String login, String theme, String quantityMail) {
        clickSendToInput();
        if (contactMailForSendContainsValue(login)) {
            clickContactMailForSend();
        }
        clickAndSetThemeMail(theme);
        clickAndsStBodyMailForSend(quantityMail);
        clickLinkBackToMainPage();
    }

    public int getCurrentQuantityMail(String theme) {
        searchMailInInput(theme);
        clickFindButton();
        return generateAndReturnQuantityMail();
    }
}
