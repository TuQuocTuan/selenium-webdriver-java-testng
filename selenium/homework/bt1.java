package homework;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class bt1{
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	public void TC_01_VerifyURL() {
		driver.findElement(By.className("skip-account")).click();
		
		driver.findElement(By.linkText("My Account")).click();
		
	
		String URL = driver.getCurrentUrl();
		
		Assert.assertEquals(URL, "http://live.demoguru99.com/index.php/customer/account/login/" );
		
	
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
	
		String URL1 = driver.getCurrentUrl();
		
		Assert.assertEquals(URL1, "http://live.demoguru99.com/index.php/customer/account/create/" );
		
	}
	
	@Test
	public void TC_02_verifytitle(){
		driver.get("http://live.demoguru99.com");
		sleepInSecond(3);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
	
		String actualTitle = driver.getTitle();
		sleepInSecond(3);
		String expectedTitle="Customer Login";
		sleepInSecond(3);
	}
	
	@Test
	public void TC_03_Navigatefunction(){
		driver.get("http://live.demoguru99.com");
		sleepInSecond(3);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		
		String URL = driver.getCurrentUrl();
	
		Assert.assertEquals(URL, "http://live.demoguru99.com/index.php/customer/account/create/" );
		
		
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		String URL1 = driver.getCurrentUrl();
		
		Assert.assertEquals(URL1, "http://live.demoguru99.com/index.php/customer/account/login/" );
		
		
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		sleepInSecond(3);
		String actualTitle = driver.getTitle();
		
		String expectedTitle="Create New Customer Account";
		assertEquals(actualTitle, expectedTitle);
	
	}
	@Test
	public void TC_04_getpagesource(){
		driver.get("http://live.demoguru99.com");
		sleepInSecond(3);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String actualText = driver.getPageSource();

		String expectedString="Create New Customer Account";
		assertTrue(actualText.contains(expectedString));
		
		}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long timeoutInSecond) {
	try {
		Thread.sleep(timeoutInSecond *1000);
	}catch (InterruptedException e) {
		e.printStackTrace();
	}
	}
}
	
	