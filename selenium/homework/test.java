package homework;

import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class test {
	WebDriver driver;
	String projectPath= System.getProperty("user.dir") ;
	By education= By.id("edu");
	By radiounder18 = By.id("under_18");
	By javaCheckbox = By.id("java");
	By passwordTextbox = By.id("password");
	By disabledButton = By.id("button-disabled");
	By disabledCheckbox = By.id("check-disbaled");
	
	
		
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectPath + "\\browserDrivers\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://login.mailchimp.com/signup/");
		
	}

	
	@Test
	public void TC_01_Newbie(){
		
	if (driver.findElement(By.xpath("//input[@id='java']")).isDisplayed()) {
		driver.findElement(By.xpath("//input[@id='java']")).click();
		System.out.println("javacheckbox is displayed");
	} else {
		System.out.println("javacheckbox is not displayed");
	}
	}
	
	@Test
	public void TC_02_function(){
	
	if(isElementDisplayed(education)) {
		sendKeyToElement(education,"ABC");
	}
	
	if(isElementDisplayed(radiounder18)) {
		clickToElement(radiounder18);
	}
	
	}
	
	@Test
	public void TC_03_isSelected(){
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		clickToElement(radiounder18);
		clickToElement(javaCheckbox);
	
		Assert.assertTrue(isElementSelected(radiounder18));
		Assert.assertTrue(isElementSelected(javaCheckbox));
		
	}
	
	@Test
	public void TC_04_isDisabled() {
		 Assert.assertFalse(isElementEnable(passwordTextbox));
	 Assert.assertFalse(isElementEnable(disabledButton));
		 Assert.assertFalse(isElementEnable(disabledCheckbox));

	}
	
	@Test
	public void TC_05_Register_Validate() {
		
		By passwordMailchimp = By.id("new_password");
		By uppercaseCompleted = By.xpath("//div[@class=\"group size1of2\"]//li[2]");
		By lowercaseCompleted = By.xpath("//div[@class=\"group size1of2\"]//li[1]");
		By numbercaseCompleted = By.xpath("//div[@class=\"group size1of2\"]//li[3]");
		By specialcaseCompleted = By.xpath("//div[@class=\"lastGroup size1of2\"]//li[1]");
		By minimum8Completed = By.xpath("//div[@class=\"lastGroup size1of2\"]//li[2]");
		By registerButton = By.id("create-account");
		By checkbox = By.xpath("//input[@name='marketing_newsletter']");
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("tuan.tuquoc@hotmail.net");
		driver.findElement(By.xpath("//input[@id='new_username']")).sendKeys("nfgjindsvijnw");
		
		//uppercase
		driver.findElement(passwordMailchimp).sendKeys("A");
		Assert.assertTrue(isElementDisplayed(uppercaseCompleted));
		Assert.assertFalse(isElementEnable(registerButton));
		
		//lowercase
		driver.findElement(passwordMailchimp).clear();
		driver.findElement(passwordMailchimp).sendKeys("a");
		Assert.assertTrue(isElementDisplayed(lowercaseCompleted));
		Assert.assertFalse(isElementEnable(registerButton));
		//numbercase
		driver.findElement(passwordMailchimp).clear();
		driver.findElement(passwordMailchimp).sendKeys("1");
		Assert.assertFalse(isElementEnable(registerButton));
		//specialcase
		driver.findElement(passwordMailchimp).clear();
		driver.findElement(passwordMailchimp).sendKeys("@");
		Assert.assertFalse(isElementEnable(registerButton));
		//minimum8case
		driver.findElement(passwordMailchimp).clear();
		driver.findElement(passwordMailchimp).sendKeys("aaaaaaaaa");
		Assert.assertFalse(isElementEnable(registerButton));
		//registerbutton
		driver.findElement(passwordMailchimp).clear();
		driver.findElement(passwordMailchimp).sendKeys("Aa1@aaaaaaa");
		Assert.assertTrue(isElementEnable(registerButton));
		
		clickToElement(registerButton);
		clickToElement(checkbox);
		
		Assert.assertTrue(isElementSelected(checkbox));
		Assert.assertTrue(isElementSelected(registerButton));		
		
		
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public boolean isElementDisplayed(By by) {
		if(driver.findElement(by).isDisplayed()) {
			System.out.println(by + "is Displayed");
			return true;
		}else {
			System.out.println(by + "is not Displayed");
		return false;
		}
	}
	
	public boolean isElementSelected(By by) {
		if(driver.findElement(by).isSelected()) {
			System.out.println(by + "is Selected");
			return true;
		}else {
			System.out.println(by + "is not Selected");
		return false;
		}
	}
	
	public boolean isElementEnable(By by) {
		if(driver.findElement(by).isEnabled()) {
			System.out.println(by + "is Enable");
			return true;
		}else {
			System.out.println(by + "is not Enable");
		return false;
		}
	}
	
	public void sendKeyToElement(By by,String value) {
		driver.findElement(by).clear();
			driver.findElement(by).sendKeys(value);
	}
	
	public void clickToElement(By by) {
		driver.findElement(by).click();
		sleepInSecond(5);
	}
	public void sleepInSecond(long timeoutInSecond)  {
	try {
		Thread.sleep(timeoutInSecond *1000);
	}catch (InterruptedException e) {
		e.printStackTrace();
	}
	}
}