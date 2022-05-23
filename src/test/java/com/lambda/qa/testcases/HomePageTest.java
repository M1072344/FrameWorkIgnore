package com.lambda.qa.testcases;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lambda.qa.base.TestBase;
import com.lambda.qa.pages.DragAndDropPage;
import com.lambda.qa.pages.HomePage;
import com.lambda.qa.pages.InputFormSubmitPage;
import com.lambda.qa.pages.SimpleDemoFormPage;
import com.lambda.qa.util.TestUtil;

import junit.framework.Assert;

public class HomePageTest extends TestBase {
	HomePage homepage;
	SimpleDemoFormPage simpleDemoPage;
	DragAndDropPage drag;
	InputFormSubmitPage inputForm;
	TestUtil testutil;
	
	String stringToEnter="Welcome to LambdaTest";
	public HomePageTest(){
		super();
	}
	
	
	@BeforeMethod
	public void setup() {
		intialization();
		homepage =new HomePage();
		simpleDemoPage=new SimpleDemoFormPage();
		drag=new DragAndDropPage();
		inputForm=new InputFormSubmitPage();
		testutil =new TestUtil();
	}
	
	@Test
	public  void validateEnteredValue() {
		homepage.clickOnSimpleDemoLink();
		String actualTitle=simpleDemoPage.validateUrl();
		System.out.println(actualTitle);
		Assert.assertTrue(simpleDemoPage.validateUrl().contains("simple-form-demo"));
		simpleDemoPage.enterMessagevalue(stringToEnter);
		simpleDemoPage.clickOnGetCheckedValue();
		String actualtext=simpleDemoPage.getYourTextMsg();
		Assert.assertEquals(stringToEnter, actualtext);
		
	}
	
	@Test
	public void dragSlider() throws InterruptedException {
		homepage.clickOnDragAndDropLink();
		String str=drag.dragAndGetValue();
		int actualvalue=Integer.parseInt(str);
		Assert.assertEquals(95, actualvalue);
		
		
		
	}
	
	@Test
	public void fillTheform() throws InterruptedException {
		homepage.clickOnInputFormSubmitLink();
		inputForm.clickOnSubmit();
		String validationMessage=inputForm.readValidationMsg();
		Assert.assertEquals("Please fill out this field.", validationMessage);
	   inputForm.fillForm();
	   inputForm.clickOnSubmit();
	   String thankyouMessage=inputForm.validateThankyouMsg();
	   Assert.assertEquals("Thanks for contacting us, we will get back to you shortly.", thankyouMessage);
		
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
}
