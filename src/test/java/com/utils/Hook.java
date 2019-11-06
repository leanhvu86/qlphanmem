/*package com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Hook {
	public static WebDriver webDriver;
	
	public void openTest(String webSite) {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		//
		try {
			// Khởi tạo đối tượng trình duyệt chrome bằng driver
			webDriver = new ChromeDriver();

			// Khai báo đường link trang web muốn test
			String baseUrl = webSite;

			// Chạy trình duyệt và điều hướng nó tới đường link muốn test
			webDriver.get(baseUrl);
		} catch (NoClassDefFoundError e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void closeTest() {
		webDriver.close();
	}
	public  void endTest() {
		webDriver.quit();
	}
}
*/