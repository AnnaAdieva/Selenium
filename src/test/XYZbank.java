import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import static java.util.concurrent.TimeUnit.SECONDS;

public class XYZbank {
    private By customerLogin = By.xpath("//button[normalize-space()='Customer Login']");
    private By yourName = By.cssSelector("#userSelect");
    private By login = By.cssSelector("button[type='submit']");
    private By nevilleLongbottom = By.cssSelector(".fontBig.ng-binding");
    private By currency = By.xpath("//strong[normalize-space()='Dollar']");
    private By deposit = By.xpath("//button[normalize-space()='Deposit']");
    private By amountInput = By.xpath("//input[@placeholder='amount']");
    private By submitBTN = By.cssSelector("button[type='submit']");
    private By message = By.cssSelector(".error.ng-binding");
    private By transactionsBTN = By.xpath("//button[normalize-space()='Transactions']");
    private By transaction1Amount = By.xpath("//td[normalize-space()='1000']");
    private By transaction1Type = By.xpath("//td[normalize-space()='Credit']");
    private By withdrawl = By.xpath("//button[normalize-space()='Withdrawl']");
    private By transaction2Type = By.xpath("//td[normalize-space()='Debit']");

    @Test
    public void testXYZbank() {
        System.setProperty("webdriver.chrome.driver", "C:/Driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);

        String baseUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";
        String expectedTitle = "XYZ Bank";
        String actualTitle = "";
        driver.get(baseUrl);
        actualTitle = driver.getTitle();
        //Check that the expected page was opened
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("XYZ Bank page was opened");
        } else {
            System.out.println("XYZ Bank page wasn't opened");
        }

        driver.findElement(customerLogin).click();

        WebElement dropdown=driver.findElement(yourName);
        Select optionsDD = new Select(dropdown);
        optionsDD.selectByVisibleText("Neville Longbottom");
        optionsDD.selectByValue("5");

        driver.findElement(login).submit();
        String nameSelected = driver.findElement(nevilleLongbottom).getText();
        Assert.assertEquals(nameSelected, "Neville Longbottom");
        System.out.println("Neville Longbottom was selected");

        String actualResult = driver.findElement(currency).getText();
        String expectedResult = "Dollar";
        Assert.assertEquals(actualResult, expectedResult);
        System.out.println(expectedResult);

        driver.findElement(deposit).click();
        driver.findElement(amountInput).sendKeys("1000");
        driver.findElement(submitBTN).submit();

        String depositStatus = driver.findElement(message).getText();
        Assert.assertEquals(depositStatus, "Deposit Successful");
        System.out.println("Deposit Successful");

        driver.findElement(transactionsBTN).click();
        String creditAmount = driver.findElement(transaction1Amount).getText();
        Assert.assertEquals(creditAmount, "1000");
        System.out.println("Credit amount 1000");
        String transactionType = driver.findElement(transaction1Type).getText();
        Assert.assertEquals(transactionType,"Credit");
        System.out.println("Transaction type Credit");

        driver.navigate(). back();
        driver.findElement(withdrawl).click();
        driver.findElement(amountInput).sendKeys("1000");
        driver.findElement(submitBTN).submit();

        String transactionStatus = driver.findElement(message).getText();
        Assert.assertEquals(transactionStatus, "Transaction successful");
        System.out.println("Transaction successful");

        driver.findElement(transactionsBTN).click();
        String transaction2 = driver.findElement(transaction2Type).getText();
        Assert.assertEquals(transaction2,"Debit");
        System.out.println("Transaction type Debit");

        driver.close();
        driver.quit();
    }
}
