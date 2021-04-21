package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexMailPage {

    //#todo think about it
    @FindBy(css = "input[placeholder*=\"Поиск\"]")
//    @FindBy(xpath = ".//span[@class='textinput__box']")
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

    WebDriver driver;
    public YandexMailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //contains region
    public Boolean contactMailForSendContainsValue(String value) {
        return contactMailForSend.getText().contains(value);
    }

    //enabled region
    public Boolean searchInputIsEnabled() {
        return searchInput.isEnabled();
    }

    public Boolean writeMailButtonIsEnabled() {
        return writeMailButton.isEnabled();
    }

    public Boolean sendToInputIsEnabled() {
        return sendToInput.isEnabled();
    }

    // click region
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


    //send key region
    public void searchThemeMail(String themeMail) {
        searchInput.sendKeys(themeMail);
    }

    public void setThemeMail(String themeMail) {
        themeMailForSend.sendKeys(themeMail);
    }

    public void setBodyMailForSend(String themeMail) {
        bodyMailForSend.sendKeys(themeMail);
    }


    //isDisplayed region
    public Boolean findButtonIsDisplayed() {
        return findButton.isDisplayed();
    }

    public Boolean notFoundIsDisplayed() {
        return notFound.isDisplayed();
    }

    public Boolean countMailIsDisplayed() {
        return countMail.isDisplayed();
    }

    //get text region
    public String getQuantityMail(){
        return countMail.getText();
    }

    public String getWasQuantityMail(){
        return lastFindMail.getText();
    }

    //
    public Boolean checkMailIsPresent() {
       return driver.findElements(By.cssSelector("span[class*=\"mail-MessagesSearchInfo-Title_misc\"]")).isEmpty();
    }

    public int getDigitFromString(String value){
        String digitRegExp = "\\D+";
        return Integer.parseInt(value.replaceAll(digitRegExp,""));
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
