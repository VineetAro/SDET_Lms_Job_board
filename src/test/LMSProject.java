package test;

import org.testng.annotations.Test;

import common.openBrowser;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class LMSProject {

	WebDriver driver;
	// Create object to access common/openBrowser Class
	openBrowser lms = new openBrowser();

	// Verify the website title
	@Test
	@Parameters({ "lmsPassword", "lmsUsername" })
	public void Activity1(String lmsPassword, String lmsUsername) {

		// Get Title of the LMS Website
		String title = lms.webTitle(driver);

		// Assert title
		Assert.assertEquals(title, "Alchemy LMS – An LMS Application");

		// Report log
		Reporter.log("Activity 1 successful");

	}

	// Verify the website Header
	@Test
	@Parameters({ "lmsPassword", "lmsUsername" })
	public void Activity2(String lmsPassword, String lmsUsername) {

		// Get header of the LMS Website
		String header = driver
				.findElement(
						By.xpath("//*[@id='uagb-infobox-74cd79b6-b855-4e72-81bc-e70591de1896']/div/div/div/div[1]/h1"))
				.getText();

		// Assert title
		Assert.assertEquals(header, "Learn from Industry Experts");

		// Report log
		Reporter.log("Activity 2 successful");

	}

	// Verify the First Info box
	@Test
	@Parameters({ "lmsPassword", "lmsUsername" })
	public void Activity3(String lmsPassword, String lmsUsername) {

		// Get First Infobox of the LMS Website
		String infoBox = driver
				.findElement(
						By.xpath("//*[@id='uagb-infobox-f08ebab0-fbf1-40ec-9b2a-c9feeb3d4810']/div/div/div/div[2]/h3"))
				.getText();

		// Assert title
		Assert.assertEquals(infoBox, "Actionable Training");

		// Report log
		Reporter.log("Activity 3 successful");

	}

	// Verify the Second best course
	@Test
	@Parameters({ "lmsPassword", "lmsUsername" })
	public void Activity4(String lmsPassword, String lmsUsername) {

		// Get second best course in the LMS Website
		String infoBox = driver.findElement(By.xpath("//*[@id='post-71']/div[2]/h3")).getText();

		// Assert title
		Assert.assertEquals(infoBox, "Email Marketing Strategies");

		// Report log
		Reporter.log("Activity 4 successful");

	}

	// Click My account and verify the title
	@Test
	public void Activity5() {
		// Login to LMS training portal
		driver.findElement(By.xpath("//*[@id='menu-item-1507']/a")).click();

		// Verify page
		String myaccountPage = lms.webTitle(driver);
		Assert.assertEquals(myaccountPage, "My Account – Alchemy LMS");

		// Report log
		Reporter.log("Activity 5 successful");

	}

	// Login and verify LMS
	@Test
	@Parameters({ "lmsPassword", "lmsUsername" })
	public void Activity6(String lmsPassword, String lmsUsername) {
		// Login to LMS training portal
		lms.webLogin_LMS(driver, lmsUsername, lmsPassword);
		// Verify login
		String loginUser = driver.findElement(By.xpath("//*[@id='wp-admin-bar-my-account']/a/span")).getText();
		Assert.assertEquals(loginUser, lmsUsername);

		// Report log
		Reporter.log("Activity 6 successful");

	}
	
	//Verify All Courses

	@Test
	@Parameters({ "lmsPassword", "lmsUsername" })
	public void Activity7(String lmsPassword, String lmsUsername) {
		// Login to LMS training portal
		lms.webLogin_LMS(driver, lmsUsername, lmsPassword);
		// navigate to all Courses
		driver.findElement(By.xpath("//*[@id='menu-item-1508']/a")).click();

		// Find Webelements Courses

		List<WebElement> courseList = driver.findElements(By.xpath("//*[@class= 'ld_course_grid col-sm-8 col-md-4 ']"));

		System.out.println("Total number of courses are: " + courseList.size());

		// Report log
		Reporter.log("Activity 7 successful");

	}

	// Navigate to Contact and fill the form
	@Test
	@Parameters({ "lmsPassword", "lmsUsername" })
	public void Activity8(String lmsPassword, String lmsUsername) {
		// Login to LMS training portal
		lms.webLogin_LMS(driver, lmsUsername, lmsPassword);
		// navigate to Contactus
		driver.findElement(By.xpath("//*[@id='menu-item-1506']/a")).click();

		// Fill the form
		int length_name = 10;
		String first_Name = lms.randomString(length_name);
		String last_Name = lms.randomString(length_name);

		driver.findElement(By.xpath("//*[@id='wpforms-8-field_0']")).sendKeys(first_Name + " " + last_Name);
		driver.findElement(By.xpath("//*[@id='wpforms-8-field_1']")).sendKeys(first_Name + last_Name + "@gmail.com");
		driver.findElement(By.xpath("//*[@id='wpforms-8-field_3']")).sendKeys("Query Regarding new Courses");
		driver.findElement(By.xpath("//*[@id='wpforms-8-field_2']")).sendKeys("Query Regarding new Courses related to marketing . Its duration and Fees.");
		driver.findElement(By.xpath("//*[@id='wpforms-submit-8']")).submit();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// get the message

		String message = driver.findElement(By.xpath("//*[@id='wpforms-confirmation-8']")).getText();

		System.out.println(message);

		// Report Log
		Reporter.log("Activity 8 successfull. ");

	}
	
	//Complete a Lesson 

		@Test
		@Parameters({ "lmsPassword", "lmsUsername" })
		public void Activity9(String lmsPassword, String lmsUsername) {
			// Login to LMS training portal
			lms.webLogin_LMS(driver, lmsUsername, lmsPassword);
			// navigate to all Courses
			driver.findElement(By.xpath("//*[@id='menu-item-1508']/a")).click();

			// navigate to a Courses
			driver.findElement(By.xpath("//*[@id='post-24042']/div[2]/p[2]/a")).click();
			driver.findElement(By.xpath("//*[@id='ld-expand-button-24042']/span[2]")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			//get lession title anc complete a lesson.
			driver.findElement(By.partialLinkText("Growth Hacking With Your Content")).click();
			String lesson = lms.webTitle(driver);
			System.out.println("Lesson is: "+ lesson);
			Assert.assertEquals(lesson, "Growth Hacking With Your Content – Alchemy LMS");
			
			//Click Submit
			
			try {
				driver.findElement(By.xpath("//*[@id='learndash_post_289']/div/div[3]/div[2]/form/input[4]")).click();
			} catch (Exception e) {

				System.out.println("Lesson was already completed.");
				
				
			}
		
			

		}

	@Parameters({ "browser", "urlLMS" })
	@BeforeMethod
	public void beforeMethod(String browser, String urlLMS) {
		driver = lms.returnDriver(browser);
		lms.getPage(urlLMS, driver);

	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}

}
