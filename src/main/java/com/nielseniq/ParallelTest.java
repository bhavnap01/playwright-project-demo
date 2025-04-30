package com.nielseniq;

import static java.util.Arrays.asList;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class ParallelTest extends Thread {
	private final String browserName;

	private ParallelTest(String browserName) {
		this.browserName = browserName;
	}

	public static void main(String[] args) throws InterruptedException {
		// Create separate playwright thread for each browser.
		for (String browserName : asList("chromium", "webkit", "firefox")) {
			Thread thread = new ParallelTest(browserName);
			thread.start();
		}
	}

	@Override
	public void run() {
		try (Playwright playwright = Playwright.create()) {
			BrowserType browserType = getBrowserType(playwright, browserName);
			Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
			Page page = browser.newPage();
			page.navigate("https://playwright.dev/");
			page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("user-agent-" + browserName + ".png")));
		}
	}

	private static BrowserType getBrowserType(Playwright playwright, String browserName) {
		switch (browserName) {
		case "chromium":
			return playwright.chromium();
		case "webkit":
			return playwright.webkit();
		case "firefox":
			return playwright.firefox();
		default:
			throw new IllegalArgumentException();
		}
	}
}