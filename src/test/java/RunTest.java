import io.github.bonigarcia.wdm.WebDriverManager;
import auth.AuthTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import product.ProductTest;
import java.time.Duration;


@Listeners({ListenerTest.class})
public class RunTest {
    WebDriver driver;
    ProductTest productTest;
    AuthTest authTest;


    public WebDriver getDriver() {
        return driver;
    }


    @BeforeTest
    public void setup() {

        // Set Up the Chrome browser and  web driver.
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Set up the timeOuts.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test(priority = 0, description = "valid Login Scenario with good username and password.")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Login test with Good username and Good password.")
    @Story("valid username and password login test")
    public void loginTestNg(){
        // Initialising a Login test.
        authTest = new AuthTest(driver);
        // Execute a test.
        authTest.login();
    }

    @Test
    public void AddProductTestNg(){
        productTest = new ProductTest(driver);
        // Execute a test.
        productTest.addProductToBag();
        //        productTest.addProductToBag();
//        productTest.deleteProductToBag();
//        productTest.searchProduct();
    }








    @Test
    public void logout() throws InterruptedException {
        // Initialising a Login test.
        authTest = new AuthTest(driver);
        authTest.logout();
    }



    @AfterTest
    public void tearDown() throws InterruptedException {
        // Close the browser after all test executed and 1s more.
        Thread.sleep(1000);
        driver.close();
        driver.quit();
    }


    }

