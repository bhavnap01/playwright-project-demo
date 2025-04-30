package com.nielseniq;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

public class DropdownTest {
	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium()
				.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
		
		//Maximize the window
		BrowserContext browserContext=browser.newContext(new Browser.NewContextOptions().setViewportSize(1664,1110));

		Page page = browserContext.newPage();
		page.navigate("https://demoselsite.azurewebsites.net/webform1.aspx");
		page.pause();
		
		Locator num1 = page.locator("#txtno1");
		num1.fill("100");
		Locator num2 = page.locator("#txtno2");
		num2.fill("20");
		Locator oper=page.locator("#cmbop");
		oper.selectOption("Add");  //select by value
		oper.selectOption("Multiply");  //select by label
		oper.selectOption(new SelectOption().setIndex(2));  //select by index
		
		Locator btn = page.locator("#btnsrcvcalc");
		btn.click();
		
		System.out.println(page.locator("#lblres").textContent());
		
		browser.close();
	}
}
