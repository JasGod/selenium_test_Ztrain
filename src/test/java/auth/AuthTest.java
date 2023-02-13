package auth;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


@Feature("Authentication File.")
public class AuthTest {
WebDriver driver;
//ListenerTest listenerTest = new ListenerTest();
    public AuthTest(WebDriver driver){

    this.driver = driver;
    }


    public void login() {
        this.driver.get("https://ztrain-web.vercel.app/auth/login");
        this.driver.findElement(By.id("email_login")).sendKeys("taki@gmail.com");
        this.driver.findElement(By.id("password_login")).sendKeys("chouchou12");
        this.driver.findElement(By.id("btn_login")).click();
        String url = this.driver.getCurrentUrl();
        if (url.equals("https://ztrain-web.vercel.app/home"))
            Assert.fail("Login failed");
    }


    @Epic("Log out from Ztrain")
    public void logout() throws InterruptedException {
        By div1 = By.id("style_avatar_wrapper__pEGIQ");
        this.driver.findElement(div1).click();
        this.driver.findElement(By.cssSelector("a[id=\"logout\"]")).click();
        Thread.sleep(5000);
        String url = this.driver.getCurrentUrl();
        if (url.equals("https://ztrain-web.vercel.app/auth/login")){
            System.out.println("Log out successfully");
        }else {
            Assert.fail("log out failed");

        }
    }
}
