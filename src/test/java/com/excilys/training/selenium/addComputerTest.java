package com.excilys.training.selenium;

import static com.excilys.training.binding.validator.ValidatorUtils.Parse;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
//Generated by Selenium IDE
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.training.core.Company;
import com.excilys.training.core.Computer;
import com.excilys.training.service.ComputerService;
import com.excilys.training.service.conf.ServiceConfig;

import io.github.bonigarcia.wdm.WebDriverManager;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)

public class addComputerTest {
	private WebDriver driver;
	private Map<String, Object> vars;
	
	@Autowired
	private ComputerService computerService;
	
	JavascriptExecutor js;

	@BeforeClass
	static public void setUpClass() throws IOException, SQLException {
		WebDriverManager.phantomjs().setup();

	}
	 
	@Before
	public void setUp() {
		driver = new PhantomJSDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void addComputer() {
		//expected dto create
		String name = "selenium computer";
		String introducedDate = "2019-05-06";
		String discontinuedDate = "2019-05-07";
		String Companyname = "Apple Inc.";
		Company company = new Company(1L,Companyname);
		Computer expected = new Computer(1L, name, Parse(introducedDate), Parse(discontinuedDate), company);
		
		driver.get("http://localhost:8080/CDB/");
		driver.manage().window().setSize(new Dimension(1280, 960));
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("computerName")).click();
		driver.findElement(By.id("computerName")).sendKeys(name);
		driver.findElement(By.id("introduced")).click();
		driver.findElement(By.id("introduced")).click();
		driver.findElement(By.id("introduced")).sendKeys(introducedDate);
		driver.findElement(By.id("discontinued")).click();
		driver.findElement(By.id("discontinued")).click();
		driver.findElement(By.id("discontinued")).sendKeys(discontinuedDate);
		{
			WebElement dropdown = driver.findElement(By.id("companyId"));
			dropdown.findElement(By.xpath("//option[. = '" + Companyname + "']")).click();
		}
		{
			WebElement element = driver.findElement(By.id("companyId"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).clickAndHold().perform();
		}
		{
			WebElement element = driver.findElement(By.id("companyId"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		{
			WebElement element = driver.findElement(By.id("companyId"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).release().perform();
		}
		driver.findElement(By.id("companyId")).click();
		driver.findElement(By.cssSelector(".btn-primary")).click();
		driver.findElement(By.cssSelector(".navbar-brand")).click();
		List<Computer> liste = computerService.getAll();
		Computer actual = liste.get(liste.size()-1);
		expected.setId(actual.getId());
		assertEquals(expected, actual);
		
		
		computerService.delete(actual);
	}
}
