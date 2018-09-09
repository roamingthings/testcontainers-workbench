package de.roamingthings.workbench.testcontainers.kt


import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.ClassRule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner
import org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL
import java.io.File
import java.net.InetAddress

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class WelcomeTestKt {

    companion object {
        var chrome = BrowserContainer()
                .withDesiredCapabilities(DesiredCapabilities.chrome())
                .withRecordingMode(RECORD_ALL, File("."))

        @ClassRule
        @JvmField
        val ruleChain = RuleChain.outerRule(chrome)
    }

    @LocalServerPort
    private val port: Int = 0

    @After
    fun cleanUp() {
        chrome.getWebDriver().manage().deleteAllCookies()
    }

    @Test
    @Throws(Exception::class)
    fun greetUser() {
        //given
        val driver = chrome.getWebDriver()
        //        String hostIpAddress = chrome.getTestHostIpAddress();
        val hostIpAddress = InetAddress.getLocalHost()

        //when
        val welcomePage = goToWelcomePage(driver, hostIpAddress)

        // then
        assertThat(welcomePage.isInitialized).isTrue()

        // given
        welcomePage.enterName("Toni")

        // when
        val greetingPage = welcomePage.submit()

        // then
        assertThat(greetingPage.isInitialized).isTrue()

        // and
        assertThat(greetingPage.name()).isEqualTo("Toni")

        sleepForVideoToFinish()
    }

    private fun sleepForVideoToFinish() {
        try {
            Thread.sleep(100)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    private fun goToWelcomePage(driver: WebDriver, hostIpAddress: InetAddress): WelcomePage {
        driver.get("http://" + hostIpAddress.hostAddress + ":" + port)
        return WelcomePage(driver)
    }

}
