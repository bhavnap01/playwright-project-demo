package com.nielseniq;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FormElements {
	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium()
				.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
		
		//Maximize the window
		BrowserContext browserContext=browser.newContext(new Browser.NewContextOptions().setViewportSize(1664,1110));

		Page page = browserContext.newPage();
		page.navigate("https://demoselsite.azurewebsites.net/webform2.aspx");
		
		Locator num1 = page.locator("#txtno1");
		num1.fill("100");
		Locator num2 = page.locator("#txtno2");
		num2.fill("20");
		Locator oper=page.locator("#rdmul");
		oper.click();
		Locator btn = page.locator("#btnsrcvcalc");
		btn.click();
		
		System.out.println(page.locator("#lblres").textContent());
		
		browser.close();
	}
}
