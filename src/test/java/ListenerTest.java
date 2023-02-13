import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.page.Page;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

public class ListenerTest implements ITestListener {



    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }



    public byte[] saveScreenshotPNG(WebDriver driver) {
//        Allure.addAttachment("screenshot",
//                new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        Allure.addAttachment("Données supplémentaires: ", String.valueOf("Heure de fin du test "+ new Date().toString()));
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }




    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
//        iTestContext.setAttribute("Page", WebDriver);
        Allure.addAttachment("Données supplémentaires: ", String.valueOf("Heure de debut du test "+ new Date().toString()));
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
        Allure.addAttachment("Données supplémentaires: ", String.valueOf("Heure de fin du test "+ new Date().toString()));
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
        Allure.addAttachment("Données supplémentaires: ", String.valueOf("Heure de debut du test "+ new Date().toString()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
        Allure.addAttachment("Données supplémentaires: ", String.valueOf("Heure de fin du test "+ new Date().toString()));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((RunTest) testClass).getDriver();

        //Allure ScreenShotRobot and SaveTestLog
        if (driver != null) {
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }

        //Save a log on allure.
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
        Allure.addAttachment("Données supplémentaires: ", String.valueOf("Heure de fin du test "+ new Date().toString()));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
        Allure.addAttachment("Données supplémentaires: ", String.valueOf("Heure de fin du test "+ new Date().toString()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
        Allure.addAttachment("Données supplémentaires: ", String.valueOf("Heure de fin du test "+ new Date().toString()));
    }








}
