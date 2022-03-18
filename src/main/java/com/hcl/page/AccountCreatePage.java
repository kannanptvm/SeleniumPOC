package com.hcl.page;

import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hcl.TestBase.TestBase;
import com.hcl.pages.locators.CreateACcountLocators;
import com.hcl.pages.locators.Dashboardlocator;
import com.hcl.pages.locators.LoginPageLocators;
import com.hcl.utilities.TestUtility;

public class AccountCreatePage extends TestBase {
	Dashboard dashboardpage;

	@FindBy(xpath = CreateACcountLocators.cuemail_xpath)

	WebElement email;

	@FindBy(xpath = CreateACcountLocators.createaccount_xpath)

	WebElement accountcreatebtn;

	@FindBy(xpath = CreateACcountLocators.cufname_xpath)

	WebElement fname;

	@FindBy(xpath = CreateACcountLocators.culname_xpath)

	WebElement lname;

	@FindBy(name = CreateACcountLocators.cupassword_name)

	WebElement pass;

	@FindBy(xpath = CreateACcountLocators.cuaddress_xpath)

	WebElement addrs;

	@FindBy(id = CreateACcountLocators.cuaddcompany_id)

	WebElement comp;

	@FindBy(xpath = CreateACcountLocators.cuaddcity_xpath)

	WebElement city;

	@FindBy(name = CreateACcountLocators.cuadd_state_name)

	WebElement State;

	@FindBy(id = CreateACcountLocators.cuaddzip_id)

	WebElement zip;

	@FindBy(name = CreateACcountLocators.cuaddmobile_name)

	WebElement mobile;

	@FindBy(name = CreateACcountLocators.cuaddalias_name)

	WebElement alias;

	@FindBy(xpath = CreateACcountLocators.register_xpath)

	WebElement Register;

	@FindBy(xpath = CreateACcountLocators.cudays_xpath)

	WebElement days;

	@FindBy(xpath = CreateACcountLocators.cumonths_xpath)

	WebElement months;

	@FindBy(xpath = CreateACcountLocators.cuyears_xpath)

	WebElement years;
	
	@FindBy(xpath = CreateACcountLocators.createaccount_xpath)

	WebElement createaccountbtn;

	@FindBy(xpath = CreateACcountLocators.cuaccheadingerror_xpath)

	WebElement cuheadingerror;	
	
	
	
	public void RegisterAcc(String Title, String firstName, String lastName, String Password, String date,
			String cmpany, String addr, String state_details, String cty, String zipcode, String mob, String alis)
			throws InterruptedException {

		List<WebElement> radio = driver.findElements(By.xpath("//div[@class='radio-inline']/label"));

		TestUtility.selectRadioButton(radio, Title);

		fname.sendKeys(firstName);
		lname.sendKeys(lastName);
		pass.sendKeys(Password);
		log.info(date);
		String[] dateParts = date.split("-");
		String day = dateParts[0];
		String month = dateParts[1];
		String year = dateParts[2];

		TestUtility.selectValueFromDropDownByValue(days, day);
		TestUtility.selectValueFromDropDownByText(months, month);
		TestUtility.selectValueFromDropDownByValue(years, year);

		comp.sendKeys(cmpany);
		addrs.sendKeys(addr);
		city.sendKeys(cty);
		TestUtility.selectValueFromDropDownByText(State, state_details);
		zip.sendKeys(zipcode);
		mobile.sendKeys(mob);
		alias.sendKeys(alis);

		clickonButton(Register);

		log.info("Registration Process Completed");
		dashboardpage = new Dashboard();

		String logininfo_details = dashboardpage.validateLogininfo(Dashboardlocator.login_detail_xpath);
		Assert.assertEquals(logininfo_details, (firstName + " " + lastName));
		log.info("Registration Sucessfully Completed!!!");


		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).click();
	}

	public static void clickonButton(WebElement el1) {

		el1.click();
	}

	public AccountCreatePage() {
		PageFactory.initElements(driver, this);
	}

	public static void type(String xpath, String value) {

		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}

	public void clickoncreateAcc(String xpath) {

		driver.findElement(By.xpath(xpath)).click();
	}

	public void ValidateAccount(String mail) throws InterruptedException {

		email.sendKeys(mail);
		clickonButton(createaccountbtn);

	
		if(isErrorDisplayed(cuheadingerror)==true) 
		{
			Assert.fail("Email id already exists");
		}
		
		else {
			
			
			Assert.assertTrue(true);
		}
		
		
	}

	private  boolean isErrorDisplayed(WebElement cuheadingerror) {
		
		boolean elementDisplayed = cuheadingerror.isDisplayed();
		
		
		
		return elementDisplayed;
	}
	}


