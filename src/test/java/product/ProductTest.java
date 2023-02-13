package product;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProductTest {
    WebDriver driver;
    JavascriptExecutor js;

    public ProductTest(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) this.driver;
    }

    public void addProductToBag() {
        WebElement child = this.driver.findElement(By.xpath("//*[@id=\"style_popular_product_wrapper__z6J0h\"]//h5[contains(text(),'T-shirt')]"));
        WebElement parent = child.findElement(By.xpath("./../.."));
        parent.click();
        this.driver.findElement(By.cssSelector("input[class=\"style_input_quantity__xZDIb\"]")).sendKeys(Keys.BACK_SPACE ,"3");
        this.driver.findElement(By.id("style_btn_add_cart__gTXM7")).click();

        this.driver.findElement(By.id("style_content_cart_wrapper__mqNbf")).click();
        WebElement element = this.driver.findElement(By.xpath("//*[@id=\"style_card_wrapper__hrc1I\"]//*[contains(text(),'T-shirt')]"));
       if (element.getText().contains("T-shirt")){
           System.out.println("OK, product add to a bag");
       }else {
           Assert.fail("Product don't add.");
       }
       this.driver.findElement(By.cssSelector("#style_header_home__8t_ie > div >h1")).click();
    }


    public void deleteProductToBag() {
        WebElement div2 = this.driver.findElement(By.xpath("//*[@id=\"style_card_wrapper__hrc1I\"]//*[contains(text(),'T-shirt')]/../../div[4]"));
        this.js.executeScript("arguments[0].click();", div2);
        WebElement element = this.driver.findElement(By.xpath("//*[@id=\"style_card_wrapper__hrc1I\"]//*[contains(text(),'T-shirt')]"));
        if (element.getText().contains("T-shirt")){
            Assert.fail("Product don't add.");
        }else {
            System.out.println("Product delete successfully.");
        }
    }

    public void searchProduct() throws InterruptedException {
        By searchR = By.xpath("//*[@id=\"style_popular_product_wrapper__z6J0h\"]//*");
        By inputSearch = By.cssSelector("input[id=\"style_input_navbar_search__Scaxy\"]");

        this.driver.findElement(inputSearch).sendKeys("Ampoule");
        this.driver.findElement(inputSearch).clear();
        Thread.sleep(3000);
        this.driver.findElement(inputSearch).sendKeys("Ampoule", Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchR));
        String result = this.driver.findElement(searchR).getText();
        if (result.contains("Ampoule")){
            System.out.println("Success search");
        }else {
            Assert.fail("Element doesn't exist.");
        }
    }
}
