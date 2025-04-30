package com.nielseniq;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class CrossBrowserTest {

	Browser browser;
	Page page;
	Playwright playwright;

	@Parameters("BrowserName")
	@BeforeMethod
	public void setup(@Optional("Chrome") String browserName) {
		playwright = Playwright.create();
		BrowserType browserType = null;

		if (browserName.equalsIgnoreCase("Chrome"))
			browserType = playwright.chromium();
		else if (browserName.equalsIgnoreCase("firefox"))
			browserType = playwright.firefox();
		else if (browserName.equalsIgnoreCase("Safari"))
			browserType = playwright.webkit();

		browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
		page = browser.newPage();
	}

	@Test
	public void loginTest() throws InterruptedException {
		page.navigate("https://www.google.com");
		System.out.println(page.title());
	}

	@AfterMethod
	public void tearDown() {
		page.close();
		browser.close();
		playwright.close();
	}
}
