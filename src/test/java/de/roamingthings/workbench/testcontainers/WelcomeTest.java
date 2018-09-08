package de.roamingthings.workbench.testcontainers;


import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.BrowserWebDriverContainer;

import java.io.File;
import java.net.InetAddress;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class WelcomeTest {

    @LocalServerPort
    private int port;

    @Rule
    public BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(RECORD_ALL, new File("."));

    @After
    public void cleanUp() {
        chrome.getWebDriver().manage().deleteAllCookies();
    }

    @Test
    public void greetUser() throws Exception {
        //given
        WebDriver driver = chrome.getWebDriver();
//        String hostIpAddress = chrome.getTestHostIpAddress();
        InetAddress hostIpAddress = InetAddress.getLocalHost();

        //when
        WelcomePage welcomePage = goToWelcomePage(driver, hostIpAddress);

        // then
        assertThat(welcomePage.isInitialized()).isTrue();

        // given
        welcomePage.enterName("Toni");

        // when
        GreetingPage greetingPage = welcomePage.submit();

        // then
        assertThat(greetingPage.isInitialized()).isTrue();

        // and
        assertThat(greetingPage.name()).isEqualTo("Toni");

        sleepForVideoToFinish();
    }

    private void sleepForVideoToFinish() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private WelcomePage goToWelcomePage(WebDriver driver, InetAddress hostIpAddress) {
        driver.get("http://" + hostIpAddress.getHostAddress() + ":" + port);
        return new WelcomePage(driver);
    }

}
