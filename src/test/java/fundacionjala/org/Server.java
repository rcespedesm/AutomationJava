package fundacionjala.org;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Server {
	private WebDriver driver;

	  @BeforeClass
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    //preCondition
	    common.login(driver);   
	    driver.findElement(By.linkText("Email Servers")).click();
	    if(isElementPresent(By.partialLinkText(Configuration.exchangeServer))){
	    	driver.findElement(By.xpath("//div[2]/button[2]")).click();
	        driver.findElement(By.cssSelector("div.pull-right > button.btn-clear.info")).click();
	    }
	    else{
	    	driver.findElement(By.linkText("Email Servers")).click();
	    }
	  }
	  
	  @Test
	  public void testAddServer() throws Exception {  
		    driver.findElement(By.xpath("//div[2]/button")).click();
		    driver.findElement(By.id("add-mailserver-hostname")).clear();
		    driver.findElement(By.id("add-mailserver-hostname")).sendKeys(Configuration.exchangeServer);
		    driver.findElement(By.id("add-mailserver-username")).clear();
		    driver.findElement(By.id("add-mailserver-username")).sendKeys(Configuration.domainControllerUsername);
		    driver.findElement(By.id("add-mailserver-password")).clear();
		    driver.findElement(By.id("add-mailserver-password")).sendKeys(Configuration.domainControllerPassword);
		    driver.findElement(By.id("add-mailserver-description")).clear();
		    driver.findElement(By.id("add-mailserver-description")).sendKeys("optional");
		    driver.findElement(By.cssSelector("div.modal-footer.ng-scope > button.btn.btn-primary")).click();    
		    (new WebDriverWait(driver, 5))
		      .until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(Configuration.exchangeServer)));
		    String expectedString = new String("qdv06lrc04001.cba.edu\nMicrosoft Exchange Server 2010 SP3");
		    Assert.assertEquals(expectedString, driver.findElement(By.partialLinkText(Configuration.exchangeServer)).getText());
	  }
	  
	  @AfterClass
	  public void tearDown() throws Exception {
		driver.findElement(By.linkText("Email Servers")).click();
		driver.findElement(By.xpath("//div[2]/button[2]")).click();
	    driver.findElement(By.cssSelector("div.pull-right > button.btn-clear.info")).click();
	    driver.quit();
	    System.out.println("Final");
	  }
	  
	  private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }
}
