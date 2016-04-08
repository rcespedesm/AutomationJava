package fundacionjala.org;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class Meeting {
	private WebDriver driver;
	//Variables
	  private String room = "CENTRAL-F1R08";
	  private String start = "13:00:00.000";
	  private String end = "13:45:00.000";
	  private String organizer = "Rodrigo";
	  private String attendees = "Lucero";
	  private String nameMeeting = "CUIT";
	  String meetingCreated = "";

	  @BeforeClass
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    common.login(driver );
	    common.addServer(driver);
	  }
	
	
  @Test
  public void createMeeting() {
	  driver.get(Configuration.baseUrl + "/tablet/#/register");
	    driver.findElement(By.id("service-url-input")).clear();
	    driver.findElement(By.id("service-url-input")).sendKeys(Configuration.baseUrl);
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys(Configuration.roomManagerUsername);
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys(Configuration.roomManagerPassword);
	    driver.findElement(By.xpath("//div[@type='submit']")).click();
	    driver.findElement(By.xpath("//div[@type='button']")).click();
	    
	    WebElement rooms = driver.findElement(By.xpath("//rm-select-item/div/div[2]/div"));
	    rooms.findElement(By.partialLinkText(room)).click();
	    
	    
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    driver.findElement(By.cssSelector("div.meeting-title.ng-binding")).click();
	 
	    driver.findElement(By.xpath("//input[@type='time']")).clear();
	    driver.findElement(By.xpath("//input[@type='time']")).sendKeys(start);
	    driver.findElement(By.xpath("(//input[@type='time'])[2]")).clear();
	    driver.findElement(By.xpath("(//input[@type='time'])[2]")).sendKeys(end);
	    driver.findElement(By.id("txtOrganizer_value")).sendKeys(organizer);
	    driver.findElement(By.xpath("//div[@id='txtOrganizer_dropdown']/div[3]/div")).click();
	    driver.findElement(By.id("txtSubject")).clear();
	    driver.findElement(By.id("txtSubject")).sendKeys(nameMeeting);
	    driver.findElement(By.id("_value")).sendKeys(attendees);
	    driver.findElement(By.xpath("//div[@id='_dropdown']/div[3]/div[2]")).click();
	    driver.findElement(By.id("txtBody")).clear();
	    driver.findElement(By.id("txtBody")).sendKeys("optional");
	    
	    driver.findElement(By.xpath("//button")).click();
	    driver.findElement(By.xpath("//input[@type='password']")).clear();
	    driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Configuration.domainControllerPassword);
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();

	    (new WebDriverWait(driver, 10))
	    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='vis-item-overflow']/div/span[contains(text(), '" + nameMeeting + "')]")));
	      
	    WebElement meetingCreated = driver.findElement(By.xpath("//div[@class='vis-item-overflow']/div/span[contains(text(), '" + nameMeeting + "')]"));
	        
	    driver.findElement(By.xpath("//div[@class='vis-item-overflow']/div/span[contains(text(), '" + nameMeeting + "')]")).click();
	    
	    (new WebDriverWait(driver, 10))
	    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='vis-item-overflow']/div/span[contains(text(), '" + nameMeeting + "')]")));
	    
	    Actions action = new Actions(driver);
	    action.doubleClick(meetingCreated).doubleClick().build().perform();
	    
	    (new WebDriverWait(driver, 15))
	    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='clean item item-btn']/span[contains(text(), 'Remove')]")));
	    
	    System.out.println(meetingCreated.getText().trim());
	    
	    driver.findElement(By.xpath("//button[@class='clean item item-btn']/span[contains(text(), 'Remove')]")).click();
	    
	    driver.findElement(By.xpath("//input[@type='password']")).clear();
	    driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Configuration.domainControllerPassword);
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    
	    Assert.assertEquals(meetingCreated.getText().trim(), nameMeeting);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	common.login(driver);
	common.removeServer(driver);  
    driver.quit();
  }
}
