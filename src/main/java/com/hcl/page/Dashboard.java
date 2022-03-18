package com.hcl.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hcl.TestBase.TestBase;
import com.hcl.pages.locators.Dashboardlocator;

public class Dashboard extends TestBase {
	
	
	
	
@FindBy(xpath=Dashboardlocator.login_detail_xpath)
	
	WebElement login_info;


public Dashboard()
{
	PageFactory.initElements(driver, this);
}

public  String validateLogininfo(String xpath)
{
	return driver.findElement(By.xpath(xpath)).getText();
}


}
