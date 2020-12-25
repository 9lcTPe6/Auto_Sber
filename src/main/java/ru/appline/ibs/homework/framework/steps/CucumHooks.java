package ru.appline.ibs.homework.framework.steps;

import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.appline.ibs.homework.framework.exceptions.InitDriverException;
import ru.appline.ibs.homework.framework.managers.StartFrameworkManager;
import ru.appline.ibs.homework.framework.managers.WebDriverManager;
import ru.appline.ibs.homework.framework.utils.ConstProps;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CucumHooks {

    @BeforeEach
    public void startTest() {

        StartFrameworkManager.startFramework(ConstProps.CHROME_DRIVER);

    }

    @AfterEach
    public void stopTest(Scenario scenario) {

        if (scenario.isFailed()) {
            Allure.addAttachment("failureScreenshot", "image/png", addScreenshot(), "png");
        }
        StartFrameworkManager.stopFramework();

    }

    private static InputStream addScreenshot() {
        byte[] screenshot = new byte[0];
        try {
            screenshot = ((TakesScreenshot) WebDriverManager.startDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (InitDriverException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(screenshot);
    }

}
