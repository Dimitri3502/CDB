package com.excilys.training.selenium;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
// Generated by Selenium IDE
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.training.binding.conf.BindingConfig;
import com.excilys.training.service.IComputerService;
import com.excilys.training.service.conf.ServiceConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class, BindingConfig.class})

public class SearchTest {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;
	
	
	@Autowired
	private IComputerService computerService;

	@BeforeClass
	public static void setUpClass() throws IOException, SQLException {
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
	public void searchTest() {
		final String name = "apple";
		
		// Step # | name | target | value | comment
		// 1 | open | /CDB/dashboard | |
		driver.get("http://localhost:8080/webapp/dashboard");
		// 2 | setWindowSize | 1280x960 | |
		driver.manage().window().setSize(new Dimension(1280, 960));
		// 3 | click | id=searchbox | |
		driver.findElement(By.id("searchbox")).click();
		// 4 | type | id=searchbox | iphone |
		
		driver.findElement(By.id("searchbox")).sendKeys(name);
		// 5 | click | id=searchsubmit | |
		driver.findElement(By.id("searchsubmit")).click();
		String actual = driver.findElement(By.id("homeTitle")).getText();
		String expected = ""+ computerService.count(name);
		assertTrue(actual.toLowerCase().contains(expected.toLowerCase()));
		
	}
}