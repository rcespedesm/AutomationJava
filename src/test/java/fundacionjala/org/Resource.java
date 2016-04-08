package fundacionjala.org;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Resource {
  private WebDriver driver;
  String name;

  @BeforeClass
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    //preCondition
    common.login(driver);
    name = "Monitor23";
    common.addServer(driver);
    driver.findElement(By.linkText("Email Servers")).click();
  }

  @Test
  public void testCreateResource() throws Exception {
    driver.findElement(By.linkText("Resources")).click();
    driver.findElement(By.xpath("//div/div/button")).click();
    driver.findElement(By.id("convert")).click();
    driver.findElement(By.xpath("//button[@value='fa-desktop']")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys(name);
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys(name);
    driver.findElement(By.xpath("//textarea")).clear();
    driver.findElement(By.xpath("//textarea")).sendKeys("optional");
    driver.findElement(By.cssSelector("button.info")).click();
    Assert.assertEquals("Monitor17", driver.findElement(By.xpath("//div[@id='resourcesGrid']/div[2]/div/div/div[3]/div[2]/div")).getText());
  }

  @AfterClass
  public void tearDown() throws Exception {
    common.login(driver);
    common.removeServer(driver);
	driver.quit();    
  }

}
