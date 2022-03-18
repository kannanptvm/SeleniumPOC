package com.hcl.TestCase;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Log;
import com.hcl.Constants.Constants;
import com.hcl.TestBase.TestBase;
import com.hcl.page.Homepage;
import com.hcl.page.LoginPage;
import com.hcl.pages.locators.LoginPageLocators;
import com.hcl.rough.MultiBrowser;
import com.hcl.utilities.DataUtil;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	Homepage homePage;

	public LoginPageTest() {
		super();
	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1", priority = 3)
	public void logintest(Hashtable<String, String> data) throws InterruptedException {

		if (data.get("runmode").equals("N")) {

			throw new SkipException("Skipping the test as the run mode is NO");
		}

		loginPage = new LoginPage();

		String title = LoginPage.validateLoginPageTitle();
		Assert.assertEquals(title, data.get("pageTitle"), "Login Page Title is not Matched");
		log.info("Login Page Title Verified");

		LoginPage.clickonSign(LoginPageLocators.signbtn_xpath);

		loginPage.LoginInfo(data.get("email"), data.get("passWord"));
		log.info("Successfully Logged into Application");

	}

}
