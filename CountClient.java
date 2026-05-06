package autotest.buoi10;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import autocom.common.CommonPage;

public class CountClient extends CommonPage {

    By lblTotalClients = By.xpath("//span[contains(text(),'Total clients')]/../h1");

    @BeforeTest
    public void startBrowser() {
        // Mở trình duyệt Chrome và vào trang chủ
        driver = this.startBrower("https://rise.fairsketch.com/", "chrome");
    }

    @Test(priority = 1)
    public void loginByCookies() {
        // Tạo cookie session để đăng nhập
        Cookie cookie = new Cookie.Builder("ci_session", "4f80944916b3d1ff8b00d9c36772410f")
                .isHttpOnly(true)
                .isSecure(false)
                .build();
        // Nhét cookie vào browser
        driver.manage().addCookie(cookie);
        // Điều hướng tới trang Clients
        driver.navigate().to("https://rise.fairsketch.com/index.php/clients");
        pause(5);
    }

    @Test(priority = 2)
    public void demClient() {
        // Lấy số Total clients từ trang Overview
        String total = driver.findElement(lblTotalClients).getText();
        // In ra tổng số client
        System.out.println("Tổng số client: " + total);
    }

    @AfterTest
    public void closeBrowser() {
        closeBrowser(driver);
    }
}