package pages;

import com.sun.org.apache.xpath.internal.operations.Minus;
import com.sun.org.apache.xpath.internal.operations.Plus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCalc {
    WebDriver driver;

    // Number button xpath
//    By zeroButton = By.xpath(".//*[contains(@class, 'XRsWPe ') and contains(., '0')]");
//    By oneButton = By.xpath(".//*[contains(@class, 'XRsWPe ') and contains(., '1')]");
//    By twoButton = By.xpath(".//*[contains(@class, 'XRsWPe ') and contains(., '2')]");
//    By threeButton = By.xpath(".//*[contains(@class, 'XRsWPe ') and contains(., '3')]");
//    By fourButton = By.xpath(".//*[contains(@class, 'XRsWPe ') and contains(., '4')]");
//    By fiveButton = By.xpath(".//*[contains(@class, 'XRsWPe ') and contains(., '5')]");
//    By sixButton = By.xpath(".//*[contains(@class, 'XRsWPe ') and contains(., '6')]");

    @FindBy(xpath = "/html/body/div[7]/div/div[9]/div[1]/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div/div/div[3]/div/table[2]/tbody/tr[5]/td[1]/div/div")
    @CacheLookup
    private static WebElement zeroButton;

    @FindBy(xpath = "//*[@id=\"rso\"]/div[1]/div/div/div[1]/div/div/div[3]/div/table[2]/tbody/tr[4]/td[1]/div/div")
    @CacheLookup
    private static WebElement oneButton;

    @FindBy(css = ".ElumCf > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
    @CacheLookup
    private static WebElement twoButton;

    @FindBy(xpath = ".//*[contains(@class, 'XRsWPe ') and contains(., '3')]")
    @CacheLookup
    private static WebElement threeButton;

    @FindBy(xpath = ".//*[contains(@class, 'XRsWPe ') and contains(., '4')]")
    @CacheLookup
    private static WebElement fourButton;

    @FindBy(xpath = ".//*[contains(@class, 'XRsWPe ') and contains(., '5')]")
    @CacheLookup
    private static WebElement fiveButton;

    @FindBy(xpath = ".//*[contains(@class, 'XRsWPe ') and contains(., '6')]")
    @CacheLookup
    private static WebElement sixButton;


    // Operation css locator
    //#TODO think about localization, for US it`s not work
//    By multButton = By.cssSelector("div[aria-label=\"умножение\"]");
//    By divButton = By.cssSelector("div[aria-label=\"деление\"]");
//    By minusButton = By.cssSelector("div[aria-label=\"вычитание\"]");
//    By sumButton = By.cssSelector("div[aria-label=\"сложение\"]");

    @FindBy(css = "div[aria-label=\"умножение\"]")
    @CacheLookup
    private static WebElement multButton;

    @FindBy(css = "div[aria-label=\"деление\"]")
    @CacheLookup
    private static WebElement divButton;

    @FindBy(css = "div[aria-label=\"вычитание\"]")
    @CacheLookup
    private static WebElement minusButton;

    @FindBy(css = "div[aria-label=\"сложение\"]")
    @CacheLookup
    private static WebElement sumButton;

    // = button
    //By resultButton = By.cssSelector("div[aria-label=\"равно\"]");

    @FindBy(css = "div[aria-label=\"равно\"]")
    @CacheLookup
    private static WebElement resultButton;

    @FindBy(css = "div[aria-label=\"открывающая скобка\"]")
    @CacheLookup
    private static WebElement leftBracket;

    @FindBy(css = "div[aria-label=\"закрывающая скобка\"]")
    @CacheLookup
    private static WebElement rightBracket;

    @FindBy(css = "div[aria-label=\"запятая\"]")
    @CacheLookup
    private static WebElement dotButton;

    @FindBy(css = "div[aria-label=\"синус\"]")
    @CacheLookup
    private static WebElement sinButton;


    // get text for input
    @FindBy(css = "span[id=\"cwos\"]")
    @CacheLookup
    private static WebElement resultText;

    @FindBy(css = "span[class=\"vUGUtc\"]")
    @CacheLookup
    private static WebElement historyText;

    public GoogleCalc(WebDriver driver) {
        this.driver = driver;

//        if (!"calculator".equals(driver.getTitle()) || !"калькулятор".equals(driver.getTitle())) {
//            throw new IllegalStateException("This is not the calculator page");
//        }

        PageFactory.initElements(driver, this);
    }

    public String getResult(){
        resultButton.click();
        return resultText.getText();
    }

    public String getHistory(){
        return historyText.getText();
    }

    public static void inputValue(String equation)
    {
        for (char character : equation.trim().toCharArray())
        {
            switch ((int)character)
            {
                case 115:

                case 105:
                    continue;

                case 110:
                    sinButton.click();
                    break;

                case 40:
                    leftBracket.click();
                    break;

                case 41:
                    rightBracket.click();
                    break;

                case 42:
                    multButton.click();
                    break;

                case 43:
                    sumButton.click();
                    break;

                case 45:
                    minusButton.click();
                    break;

                case 46: // .
                    dotButton.click();
                    break;

                case 47:
                    divButton.click();
                    break;

                case 48:
                    zeroButton.click();
                    break;

                case 49:
                    oneButton.click();
                    break;

                case 50:
                    twoButton.click();
                    break;

                case 51:
                    threeButton.click();
                    break;

                case 52:
                    fourButton.click();
                    break;

                case 53:
                    fiveButton.click();
                    break;

                case 54:
                    sixButton.click();
                    break;

//            case 55:
//                Number7.click();
//                break;
//
//            case 56:
//                Number8.click();
//                break;
//
//            case 57:
//                Number9.click();
//                break;

                case 61:
                    resultButton.click();
                    break;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + (int) character);
            }
        }
    }

    private static void ClickElement(char character)
    {
        switch ((int)character)
        {
            case 73:
//                continue;

            case 40:
                leftBracket.click();
                break;

            case 41:
                rightBracket.click();
                break;

            case 42:
                multButton.click();
                break;

            case 43:
                sumButton.click();
                break;

            case 45:
                minusButton.click();
                break;

            case 46: // .
                dotButton.click();
                break;

            case 47:
                divButton.click();
                break;

            case 48:
                zeroButton.click();
                break;

            case 49:
                oneButton.click();
                break;

            case 50:
                twoButton.click();
                break;

            case 51:
                threeButton.click();
                break;

            case 52:
                fourButton.click();
                break;

            case 53:
                fiveButton.click();
                break;

            case 54:
                sixButton.click();
                break;

//            case 55:
//                Number7.click();
//                break;
//
//            case 56:
//                Number8.click();
//                break;
//
//            case 57:
//                Number9.click();
//                break;

            case 61:
                resultButton.click();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + (int) character);
        }
    }

}
