package com.hcl.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hcl.TestBase.TestBase;
import com.hcl.pages.locators.LoginPageLocators;

public class LoginPage extends TestBase{
	
	
	@FindBy(xpath=LoginPageLocators.username_xpath)
	
	WebElement email;
	
	@FindBy(xpath=LoginPageLocators.password_xpath)
	
	WebElement password;
	
	@FindBy(xpath=LoginPageLocators.loginbtn_xpath)
	static 
	WebElement loginButton;
	
		
	public  void LoginInfo(String email2, String passWord2) {
		email.sendKeys(email2);
		password.sendKeys(passWord2);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", loginButton);
	
		
	}
	
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public static String validateLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	
	
	


	
	
	public static void clickonSign(String xpath) {
		
		driver.findElement(By.xpath(xpath)).click();
	}


	


	
}
