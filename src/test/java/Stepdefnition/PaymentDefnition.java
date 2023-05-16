package Stepdefnition;

import Pages.PaymentPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.ConfigFileReader;
import utils.Hooks;
import utils.XmlParser;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PaymentDefnition {
    static PaymentPage paymentPage;
    static ConfigFileReader configFileReader;
    WebDriver driver = null;

    public PaymentDefnition() {
        this.driver = Hooks.driver;
        paymentPage = PageFactory.initElements(Hooks.driver, PaymentPage.class);
        configFileReader = new ConfigFileReader();
    }

    @Given("I am on the Payment gateway page")
    public void navigation_to_paymentpage() {
        paymentPage = new PaymentPage(driver);
        driver.get(paymentPage.getPageUrl());

    }

    @When("User click on dropdown and select TELECHECK routing")
    public void select_telecheck() {
        paymentPage.selectDropdown();
        paymentPage.selectTelecheck();

    }

    @And("Click on blue Checkout button")
    public void click_checkoutbutton() {
        paymentPage.clickCheckoutButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @When("User should be navigated to new session page")
    public void navigatedtogatewaypage() {
        System.out.println("Reached Payment Page");
    }


    @Then("Click on Bank account payment method")
    public void choose_bankaccount() {
        paymentPage.selectBankAccountPayment();

    }

    @When("^User enters data$")
    public void userEntersData(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String routingNo = row.get("routingno");
            String accountNo = row.get("accountno");
            String dob = row.get("dob");
            String driverLicense = row.get("driverlicense");
            String licenseState = row.get("licensedstate");

            paymentPage.enterRoutingNumber(routingNo);
            paymentPage.enterBankAccount(accountNo);
            paymentPage.enterDateOfBirth(dob);
            paymentPage.enterDrivingLicense(driverLicense);
            paymentPage.enterDrivingLicensestate(licenseState);
        }
    }

    @And("Accepts Terms and Conditions")
    public void accept_terms_and_conditions() {
        paymentPage.acceptTermsandConditions();
    }

    @And("Click on Pay button")
    public void click_on_paybutton() {
        paymentPage.payWithBankAccount();

    }

    @Then("Validate the result xml")
    public void validate_resultxml() {
        String xml = paymentPage.getXMLContent();
        XmlParser xmlparser = new XmlParser();
        String xpathExpressionBRC = "/SES/TRX/RES/BRC ";
        String expectedValueBRC = "A00";
        Assert.assertTrue(xmlparser.isValuePresentInXML(xml, xpathExpressionBRC, expectedValueBRC), "Value not found in XML.");
        String xpathExpressionMSG = "/SES/TRX/RES/MSG ";
        String expectedValueMSG = "Service succeeded";
        Assert.assertTrue(xmlparser.isValuePresentInXML(xml, xpathExpressionMSG, expectedValueMSG), "Value not found in XML.");
    }

    @Then("Navigate to MarketPay Home page")
    public void navigate_backto_marketpay() {
        paymentPage.navigateBacktoMarketPay();
    }


}









