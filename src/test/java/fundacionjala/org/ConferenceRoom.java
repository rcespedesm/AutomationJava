package fundacionjala.org;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConferenceRoom {
	private WebDriver driver;
	  private String room = "CENTRAL-F1R08";

	  @BeforeClass
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    //preCondition
	    common.login(driver);
	    //common.addServer(driver, baseUrl);
	    //driver.findElement(By.linkText("Email Servers")).click();
	  }

	  @Test
	  public void testCreateResource() throws Exception {
	    driver.findElement(By.linkText("Conference Rooms")).click();
	    
	    (new WebDriverWait(driver, 10))
	    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='roomsGrid']/div[2]/div[@class='ngCanvas']/div/div[3]")));
	    //WebElement roomsList = driver.findElement(By.xpath("//div[@id='roomsGrid']/div[2]/div[@class='ngCanvas']/div/div[3]"));
	    //System.out.println(driver.findElement(By.xpath("//span[contains(text(), 'CENTRAL-F1R09')][2]")).getText());
	    driver.findElement(By.xpath("//span[contains(text(), 'CENTRAL-F1R09')][2]")).click();
	    Actions action = new Actions(driver);
	    
	    //action.doubleClick(driver.findElement(By.xpath("//span[contains(text(), 'CENTRAL-F1R09')][2]"))).doubleClick().build().perform();
	    
	    doubleclick(driver.findElement(By.xpath("//span[contains(text(), 'CENTRAL-F1R09')][2]")));
	    
	    (new WebDriverWait(driver, 10))
	    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class='ng-binding']")));
	    driver.findElement(By.linkText("Room Info")).click();
	    driver.findElement(By.linkText("Resource Associations")).click();
	    driver.findElement(By.linkText("Out of Order Planning")).click();
	    
	    (new WebDriverWait(driver, 10))
	    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(), 'Send email')]")));
	    
	    driver.findElement(By.xpath("//button/span[contains(text(), 'Save')]")).click();
	    
	    (new WebDriverWait(driver, 10))
	    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='roomsGrid']/div[2]/div[@class='ngCanvas']/div/div[3]")));
	    
	  }

	  private void doubleclick(WebElement element) {
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("var evt = document.createEvent('MouseEvents');" +
				  "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" +
	 		        "arguments[0].dispatchEvent(evt);", element);
	    }

	  @AfterClass
	  public void tearDown() throws Exception {
	    driver.quit();
	  }

}
