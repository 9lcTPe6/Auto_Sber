package ru.appline.ibs.homework.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static ru.appline.ibs.homework.framework.managers.WebDriverManager.switchFrame;
import static ru.appline.ibs.homework.framework.utils.ParserTrimmer.parserTrimmer;

public class IpotekeCompletedHousesPage extends BasePage {

    @FindBy(xpath = "//iframe[@sandbox]")
    WebElement frameSwitch;

    @FindBy(xpath = "//div[@data-test-id='calculator']")
    WebElement calculator;

    private String checker = "//div[@data-label='%s']";
    private String fillersDestination = null;

    @FindBy(xpath = "//div[@data-test-id='discounts']")
    List<WebElement> discounts;

    private String discButton = "//input[@type='checkbox']";
    private String spans = "//span[contains(text(), '%s')]";
    private String spansForAssert = "//span[@data-e2e-id]/span";


    static int fillCount = 0;

    public IpotekeCompletedHousesPage checkIpotekePage() {

        String pageTitle = getPageName();
        scrollToElement(frameSwitch);
        switchFrame(frameSwitch);
        Assertions.assertEquals("Ипотека на готовое жилье — СберБанк",
                pageTitle,
                "Данная страница не является страницей Ипотеки на готовое жилье.");
        return this;

    }

    public IpotekeCompletedHousesPage fillData(String fillingData) {

        switch (fillCount) {
            case 0:
                fillersDestination = String.format(checker, "Стоимость недвижимости");
                fillCount++;
                findFillTarget(fillingData);
                return this;
            case 1:
                fillersDestination = String.format(checker, "Первоначальный взнос");
                fillCount++;
                findFillTarget(fillingData);
                return this;
            case 2:
                fillersDestination = String.format(checker, "Срок кредита");
                fillCount = 0;
                findFillTarget(fillingData);
                return this;
        }

        Assertions.fail("Не удалось заполнить поле/поле не найдено");
        return this;

    }

    private void findFillTarget(String fillingData) {

        WebElement fillChecker = calculator.findElement(By.xpath(fillersDestination));
        action.moveToElement(fillChecker)
                .click()
                .keyDown(Keys.CONTROL)
                .sendKeys("A")
                .keyUp(Keys.CONTROL)
                .sendKeys(fillingData)
                .build()
                .perform();

    }

    public IpotekeCompletedHousesPage unCheck(String target) {

        String check = String.format(spans, target);
        for (WebElement element : discounts) {
            WebElement button = element.findElement(By.xpath(check + "/../.." + discButton));
            action.moveToElement(button)
                    .click()
                    .build()
                    .perform();
            return this;
        }
        Assertions.fail("Чекбокс не найден");
        return this;

    }

    String creditInfo = "//div[@data-test-id='main-results-block']";

    public IpotekeCompletedHousesPage checkCreditSum(String expected) {

        WebElement creditSum = calculator.findElement(By.xpath(String.format(creditInfo + spans + "/.." + spansForAssert, "Сумма кредита")));
        scrollToElement(creditSum);
        long checkCreditSum = Long.parseLong(parserTrimmer(creditSum.getText()));
        Assertions.assertEquals(expected, checkCreditSum, "Сумма кредита не совпадает с ожидаемой");
        return this;

    }

    public IpotekeCompletedHousesPage checkRegularPay(String expected) {

        WebElement regularPay = calculator.findElement(By.xpath(String.format(creditInfo + spans + "/.." + spansForAssert, "Ежемесячный платеж")));
        long checkRegularPay = Long.parseLong(parserTrimmer(regularPay.getText()));
        Assertions.assertEquals(expected, checkRegularPay, "Сумма ежемесячного платежа не сопадает с ожидаемой");
        return this;

    }

    public IpotekeCompletedHousesPage checkWealthNeeded(String expected) {

        WebElement wealthNeeded = calculator.findElement(By.xpath(String.format(creditInfo + spans + "/.." + spansForAssert, "Необходимый доход")));
        long checkWealthNeeded = Long.parseLong(parserTrimmer(wealthNeeded.getText()));
        Assertions.assertEquals(expected, checkWealthNeeded, "Сумма необходимого дохода отличается от ожидаемой");
        return this;

    }

    public IpotekeCompletedHousesPage checkPercentage(String expected) {

        WebElement percentage = calculator.findElement(By.xpath(String.format(creditInfo + spans + "/.." + spansForAssert, "Процентная ставка")));
        Assertions.assertEquals(expected, percentage.getText(), "Процентная ставка отличается от ожидаемой");
        return this;

    }

}
