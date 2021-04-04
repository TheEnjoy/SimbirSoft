package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearch {
    WebDriver driver;
    //WebDriverWait wait = new WebDriverWait(driver, 10);


    @FindBy(css = "input[title='Поиск']")
    @CacheLookup
    private static WebElement input;

    @FindBy(css = "input[value='Калькулятор']")
    @CacheLookup
    private static WebElement calculatorText;

    @FindBy(css = "input[aria-label=\"Поиск в Google\"]")
    @CacheLookup
    private static WebElement startSearchButton;

    public GoogleSearch(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean waitInput(){
        return input.isDisplayed();
    }

    public void find(String searchString){
      //  wait.until(ExpectedConditions.visibilityOf(input));
        input.click();
        input.sendKeys(searchString);
    //    wait.until(ExpectedConditions.visibilityOf(calculatorText));
       // input.sendKeys(Keys.ENTER);
        startSearchButton.click();
    }
}
