package com.hcl.TestCase;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hcl.TestBase.TestBase;
import com.hcl.page.AccountCreatePage;
import com.hcl.page.Homepage;
import com.hcl.page.LoginPage;
import com.hcl.pages.locators.CreateACcountLocators;
import com.hcl.pages.locators.LoginPageLocators;
import com.hcl.rough.MultiBrowser;
import com.hcl.utilities.DataUtil;
import com.hcl.utilities.TestUtility;

public class CreateAccountTest extends TestBase {

	LoginPage loginPage;
	Homepage homePage;
	AccountCreatePage accountcreate;

	public CreateAccountTest() {
		super();
	}


	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1", priority = 1)
	public void createaccount(Hashtable<String, String> data) throws InterruptedException {

		if (data.get("runmode").equals("N")) {

			throw new SkipException("Skipping the test as the run mode is NO");
		}

		loginPage = new LoginPage();
		String title = LoginPage.validateLoginPageTitle();
		Assert.assertEquals(title, data.get("pageTitle"), "Login Page Title is not Matched");
		log.info("Login Page Title Verified");

		LoginPage.clickonSign(LoginPageLocators.signbtn_xpath);

		accountcreate = new AccountCreatePage();

		accountcreate.ValidateAccount(data.get("Email"));

		accountcreate.RegisterAcc(data.get("Title"), data.get("firstName"), data.get("lastName"), data.get("Password"),
				data.get("date"), data.get("cmpany"), data.get("addr"), data.get("state_details"), data.get("cty"),
				data.get("zipcode"), data.get("mob"), data.get("alis"));

		
		
		
	}

}
