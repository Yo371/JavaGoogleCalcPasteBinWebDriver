package pastebin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

public class TestPasteBin {

    private WebDriver driver;
    private CreatedPastPage createdPastPage;
    private final String gitString = "git config --global user.name \"New Sheriff in Town\"" +
            "\ngit reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")" +
            "\ngit push origin master --force";
    private final String title = "How to gain dominance among developers";

    @BeforeMethod
    public void setupPrecondition() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        PasteBinHomePage pasteBinHomePage = new PasteBinHomePage(driver);
        pasteBinHomePage.open();
        createdPastPage = pasteBinHomePage.inputToTextArea(gitString)
                .chooseBashSyntaxHighlight()
                .chooseExpiration10Min()
                .inputToTitleArea(title)
                .createPaste();
    }

    @Test
    public void checkMatchingTitleWithPasteName() {
        //check title matching
        Assert.assertTrue(driver.getTitle().contains(title));

        //check highlighted git text
        int amountOfHighlighted = createdPastPage.getListOfFontColorsOfGitText()
                .stream().filter(e -> e.equals("rgba(185, 202, 74, 1)"))
                .collect(Collectors.toList()).size();

        Assert.assertEquals(amountOfHighlighted,
                createdPastPage.getListOfFontColorsOfGitText().size());

        //check git string equals before and after creating paste
        Assert.assertEquals(gitString, createdPastPage.getTextFromRawPasteData());
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }
}
