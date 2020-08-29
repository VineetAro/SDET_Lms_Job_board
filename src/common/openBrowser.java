package common;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class openBrowser {

	public WebDriver returnDriver(String browser) {
		WebDriver driver = new ChromeDriver();
		if (browser.toUpperCase() == "CHROME") {
			driver = new ChromeDriver();
			return driver;
		} else if (browser.toUpperCase() == "FIREFOX") {
			driver = new FirefoxDriver();
			return driver;
		}
		return driver;

	}

	public void getPage(String url, WebDriver driver) {
		driver.get(url);
	}

	public String webTitle(WebDriver driver) {
		String title = driver.getTitle();

		return title;

	}

	public String getURL(WebDriver driver) {
		String pageURL = driver.getCurrentUrl();
		return pageURL;

	}

	public void webLogin_LMS(WebDriver driver, String username, String password) {
		// Navigate to LMS Login
		driver.findElement(By.xpath("//*[@id='menu-item-1507']/a")).click();
		driver.findElement(By.xpath("//*[@id='uagb-column-e9d225cb-cee9-4e02-a12d-073d5f051e91']/div[2]/div[2]/a"))
				.click();

		// Login
		driver.findElement(By.id("user_login")).sendKeys(username);
		driver.findElement(By.id("user_pass")).sendKeys(password);
		driver.findElement(By.id("wp-submit")).submit();

	}

	public void webLogin_Job(WebDriver driver, String username, String password) {
		// navigate to Job board
		driver.findElement(By.cssSelector("#menu-item-26 > a:nth-child(1)")).click();
		String currentURL = getURL(driver);
		Assert.assertEquals(currentURL, "https://alchemy.hguy.co/jobs/post-a-job/");

		// Click Sign IN.
		driver.findElement(By.cssSelector("a.button")).click();
		currentURL = getURL(driver);
		Assert.assertEquals(currentURL,
				"https://alchemy.hguy.co/jobs/wp-login.php?redirect_to=https%3A%2F%2Falchemy.hguy.co%2Fjobs%2Fpost-a-job%2F");

		// Login

		driver.findElement(By.id("user_login")).sendKeys(username);
		driver.findElement(By.id("user_pass")).sendKeys(password);
		driver.findElement(By.id("wp-submit")).submit();

	}

	public String randomString(int length) {
		Random randInt = new Random();
		String nameVal = null;
		for (int i = 0; i < length; i++) {
			int a = randInt.nextInt(57) + 65;
			if(a>90 && a<97 ){
		        a -= 15; 
		    }
			char b = (char) (a);

			if (nameVal == null) {
				nameVal = b + "" + b;
			} else {
				nameVal += b;
			}
		}
		return nameVal;
	}

}