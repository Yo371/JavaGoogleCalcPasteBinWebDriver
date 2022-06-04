package googlecloud;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestGoogleCalculator {
    private WebDriver driver;
    private CalculatorGooglePage calculatorGooglePage;

    @BeforeMethod
    public void setupPrecondition() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        ResultGooglePage resultGooglePage = new HomeGoogleCloudPage(driver).open()
                .searchByText("Google Cloud Platform Pricing Calculator");

        calculatorGooglePage = resultGooglePage.chooseCalculatorLink();

        calculatorGooglePage = calculatorGooglePage.inputInstances(4)
                .selectN1Standart8_MachineType()
                .addGPU4NvidiaV100()
                .addSsd375GB()
                .selectFrankfurtLocation()
                .selectCommittedUsage_1Year()
                .estimate();
    }

    @Test
    public void CheckGoogleCalculator() {
        Assert.assertTrue(calculatorGooglePage.getResultInstanceTypeText().contains("n1-standard-8"));
        Assert.assertEquals(calculatorGooglePage.getResultRegion(), "Frankfurt");
        Assert.assertTrue(calculatorGooglePage.getResultCostMonth().contains("USD 1,081.20 per 1 month"));
    }


    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }
}
