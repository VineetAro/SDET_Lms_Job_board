package test;

import org.testng.annotations.Test;

import common.openBrowser;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class JobBoardProject {
	WebDriver driver;
	// Create object to access common/openBrowser Class
	openBrowser jobs = new openBrowser();

	String jobTitle;

	// Verify Webpage title
	@Test
	public void activity1() {
		// Get Title of the Jobs Website
		String title = jobs.webTitle(driver);

		// Assert title
		Assert.assertEquals(title, "Alchemy Jobs – Job Board Application");

		// Report log
		Reporter.log("Activity 1 successful");

	}

	// Verify the website Header
	@Test
	public void Activity2() {

		// Get of the Jobs Website
		String header = driver.findElement(By.xpath("//*[@id='post-16']/header/h1")).getText();

		// Assert title
		Assert.assertEquals(header, "Welcome to Alchemy Jobs");

		// Report log
		Reporter.log("Activity 2 successful");

	}

	// Get the url of the header image
	@Test
	public void Activity3() {

		// Get header Image of the Jobs Website
		String headerURL = driver.findElement(By.xpath("//*[@id=\"post-16\"]/header/div/img")).getAttribute("src");

		System.out.println(headerURL);
		// Report log
		Reporter.log("Activity 3 successful");

	}

	// Verify the website’s second heading
	@Test
	public void Activity4() {

		// Get of the Jobs Website
		String header = driver.findElement(By.xpath("//*[@id='post-16']/div/h2")).getText();

		// Assert title
		Assert.assertEquals(header, "Quia quis non");

		// Report log
		Reporter.log("Activity 4 successful");

	}

	// Navigate to another page

	@Test
	public void Activity5() {
		driver.findElement(By.xpath("//*[@id='menu-item-24']/a")).click();

		// Verfiy new page

		Assert.assertEquals(jobs.webTitle(driver), "Jobs – Alchemy Jobs");

		// Report log
		Reporter.log("Activity 5 successful");

	}

	// Apply to a job

	@Test(dependsOnMethods = {"Activity7"})
	public void Activity6() {
		driver.findElement(By.xpath("//*[@id='menu-item-24']/a")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Search Job

		driver.findElement(By.cssSelector("#search_keywords")).sendKeys(jobTitle);
		driver.findElement(By.cssSelector("#search_location")).sendKeys("Bangalore");
		driver.findElement(By.xpath("//*[@id='post-7']/div/div/form/div[1]/div[4]/input")).submit();

		// Apply Job

		driver.findElement(By.xpath("//*[@id='post-7']/div/div/ul/li/a/div[1]/div")).click();
		driver.findElement(By.xpath("//*[@id='post-1451']/div/div/div/div[3]/input")).click();

		// Print to console

		String message = driver.findElement(By.xpath("//*[@id='post-1451']/div/div/div/div[3]/div/p")).getText();
		System.out.println(message);

		// Report log
		Reporter.log("Activity 6 successful");

	}

	// create a job
	@Test
	@Parameters({ "jobsUsername", "jobsPassword" })
	public void Activity7(String username, String password) {
		jobs.webLogin_Job(driver, username, password);

		// Generate random name

		int length = 15;
		jobTitle = jobs.randomString(length);

		// Create a job posting

		driver.findElement(By.id("job_title")).sendKeys(jobTitle);
		driver.findElement(By.id("job_location")).sendKeys("Bangalore");
		Select selection = new Select(driver.findElement(By.id("job_type")));
		selection.selectByValue("2");

		// Switch

		driver.switchTo().frame("job_description_ifr");

		// add description
		driver.findElement(By.xpath("/html/body")).sendKeys("New job posting");

		driver.switchTo().defaultContent();
		
		WebElement buttonSubmit = driver.findElement(By.cssSelector("input.button:nth-child(4)"));
		
		buttonSubmit.click();
		

		// Verify preview and submit
		driver.findElement(By.id("job_preview_submit_button")).submit();
		
		driver.findElement(By.xpath("//*[@id='menu-item-24']/a")).click();
		driver.findElement(By.cssSelector("#search_location")).sendKeys(jobTitle);
		
		
		String jobSave = driver.findElement(By.xpath("//p[text()='New job posting']")).getText();
		Assert.assertEquals(jobSave, "New job posting", "Job Successfully Created");
		Reporter.log("Activity 7 successful");

	}
	
	// Login backend
	@Test 
	@Parameters({"jobsUsername", "jobsPassword"})
	public void activity8(String username, String password) {
		//Login
		driver.get("https://alchemy.hguy.co/jobs/wp-admin");
		driver.findElement(By.id("user_login")).sendKeys(username);
		driver.findElement(By.id("user_pass")).sendKeys(password);
		driver.findElement(By.id("wp-submit")).submit();
		
		//Verify login
		String adminTitle = jobs.webTitle(driver);
		Assert.assertEquals(adminTitle, "Dashboard ‹ Alchemy Jobs — WordPress");
		
		// Report log
		Reporter.log("Activity 8 successful");
		
	}
	
/*	
	// Create a job listing using the backend
		@Test (dependsOnMethods = {"Activity6"})
		@Parameters({"jobsUsername", "jobsPassword"})
		public void activity9(String username, String password) {
			//Login
			driver.get("https://alchemy.hguy.co/jobs/wp-admin");
			driver.findElement(By.id("user_login")).sendKeys(username);
			driver.findElement(By.id("user_pass")).sendKeys(password);
			driver.findElement(By.id("wp-submit")).submit();
			
			// Create Job Posting
			
			Actions action = new Actions(driver);
			WebElement menu = driver.findElement(By.cssSelector("#menu-posts-job_listing > a > div.wp-menu-name"));
			action.moveToElement(menu).build().perform();
			menu.click();
			
			//Add button
			
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div[4]/a")).click();
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			// PopUp Window Handle
			
			driver.switchTo().alert().dismiss();
			
			//
					
			// Report log
			Reporter.log("Activity 9 successful, ");
			
		}
*/
	
	@Parameters({ "browser", "urlJOBS" })
	@BeforeMethod
	public void beforeMethod(String browser, String urlJOBS) {
		driver = jobs.returnDriver(browser);
		jobs.getPage(urlJOBS, driver);
	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}
}
