package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigFileReader;

import java.time.Duration;

public class PaymentPage {

    WebDriver driver;

    ConfigFileReader configFileReader;
    @FindBy(xpath = "//n-dropdown[@placeHolder='Select test data']/button[@role='listbox']")
    WebElement dropdownPayment;

    @FindBy(xpath = "//span[contains(text(),'TELECHECK - Telecheck')]")
    WebElement selectTelecheck;

    @FindBy(xpath = "//button[contains(text(),'Checkout')]")
    WebElement checkoutButton;

    @FindBy(xpath = "//body/div[@id='hppBody']/div[2]/div[1]/div[3]/div[3]")
    WebElement chooseBankaccount;

    @FindBy(xpath = "//input[@id='form_DIRDEBIT:routingNumber']")
    WebElement routingnumberInput;

    @FindBy(xpath = "//input[@id='form_DIRDEBIT:accountNumber']")
    WebElement accountnumberInput;

    @FindBy(xpath = "//input[@id='form_DIRDEBIT:dateOfBirth']")
    WebElement dateofbrithInput;

    @FindBy(xpath = "//input[@id='form_DIRDEBIT:scrambledDriversLicenseNumber']")
    WebElement drivinglicenseInput;

    @FindBy(xpath = "//input[@id='form_DIRDEBIT:driversLicenseNumberState']")
    WebElement drivinglicensestateInput;

    @FindBy(xpath = "//a[@id='openDialogBoxAchTermsAndConditions__TermsAndConditions']")
    WebElement termsandconditionsLink;

    @FindBy(xpath = "//span[contains(text(),'Accept')]")
    WebElement acceptButton;

    @FindBy(xpath = "//div[@id='accordionButtonDIRDEBIT']")
    WebElement payButton;

    @FindBy(xpath = "//code")
    WebElement xmlContainer;

    @FindBy(xpath = "//a[@class='top-nav_brand']")
    WebElement homeLink;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        configFileReader = new ConfigFileReader();
    }

    public String getPageUrl() {
        return configFileReader.getApplicationUrl();
    }

    public void selectDropdown() {
        dropdownPayment.click();
    }

    public void selectTelecheck() {
        selectTelecheck.click();
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public void selectBankAccountPayment() {
        driver.switchTo().frame("ipgframe");
        driver.findElement(By.xpath("//body/div[@id='hppBody']/div[2]/div[1]/div[3]/div[3]")).click();
    }

    public void enterRoutingNumber(String routingnumber) {
        routingnumberInput.sendKeys(routingnumber);
    }

    public void enterBankAccount(String accountno) {
        accountnumberInput.sendKeys(accountno);
    }

    public void enterDateOfBirth(String dob) {
        dateofbrithInput.click();
        dateofbrithInput.sendKeys(dob);
    }

    public void enterDrivingLicense(String driverlicense) {
        drivinglicenseInput.sendKeys(driverlicense);
    }

    public void enterDrivingLicensestate(String licensedstate) {
        drivinglicensestateInput.sendKeys(licensedstate);
    }

    public void acceptTermsandConditions() {
        termsandconditionsLink.click();
        acceptButton.click();
    }

    public void payWithBankAccount() {
        payButton.click();
    }

    public String getXMLContent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//code[contains(text(), 'SES')]")));
        System.out.println("xmlContainer");
        System.out.println(xmlContainer);
        System.out.println("xmlContainer.getText()");
        System.out.println(xmlContainer.getText());
        return xmlContainer.getText();
    }

    public void navigateBacktoMarketPay() {
        homeLink.click();
    }
}


