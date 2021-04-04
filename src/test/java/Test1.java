import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.GoogleCalc;
import pages.GoogleSearch;

import java.util.concurrent.TimeUnit;

public class Test1 {
    private static GoogleCalc calculatorGoogle;
    private static GoogleSearch searchGoogle;
    private static WebDriver driver;
    //protected GoogleCalc calculatorGoogle;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
        calculatorGoogle = new GoogleCalc(driver);
        driver.navigate().to("https://www.google.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Before
    public void findCalcPage(){
        searchGoogle = new GoogleSearch(driver);
        searchGoogle.find("Калькулятор");
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

//    @Test
//    public void test() {
//
//    }

   @Test
    public void caseOne()
    {
        String expression = "(1+2)*3-40/5";
        GoogleCalc.inputValue(expression);
        Assert.assertEquals(calculatorGoogle.getResult(), "1" );
        Assert.assertEquals(calculatorGoogle.getHistory(),"(1 + 2) × 3 - 40 ÷ 5 =");
    }

    @Test
    public void caseTwo()
    {
        String expression = "6/0";
        GoogleCalc.inputValue(expression);
        Assert.assertEquals(calculatorGoogle.getResult(), "Infinity");
        Assert.assertEquals(calculatorGoogle.getHistory(),"6 ÷ 0 =");
    }

    @Test
    public void caseThree()
    {
        String expression = "sin(";
        GoogleCalc.inputValue(expression);
        Assert.assertEquals(calculatorGoogle.getResult(), "Error");
        Assert.assertEquals(calculatorGoogle.getHistory(),"sin(()) =");
    }
}
