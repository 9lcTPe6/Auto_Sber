package ru.appline.ibs.homework.framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Стартовая страница
 */
public class StartPage extends BasePage {

    /**
     * xPath нижней части меню.
     */
    @FindBy(xpath = "//ul[@class='kitt-top-menu__list kitt-top-menu__list_icons kitt-top-menu__list_center']")
    List<WebElement> mainMenu;

    /**
     * Выбор нужного меню
     * @param menuTarget - Точное имя требующегося раздела меню
     * @return StartPage
     */
    @Step("Выбор {menuTarget} в спике меню")
    public StartPage selectMainMenu(String menuTarget) {

        final String nameMenu = String.format("//a[@role='button' and @aria-label='%s']", menuTarget);
        for (WebElement element : mainMenu) {
            WebElement menu = element.findElement(By.xpath(nameMenu));
            if (menu.isDisplayed()) {
                elementToBeClickable(menu);
                action.moveToElement(menu)
                        .click()
                        .build()
                        .perform();
                return this;
            }
        }
        Assertions.fail("Указанное меню не найдено");
        return this;

    }

    /**
     * Выбор подраздела выпадающего меню
     * @param subMenuTarget - Имя раздела подменю. Рекомендуется вводить несколько слов для точного поиска.
     * @return IpotekeCompletedHousesPage
     */
    @Step("Выбираем подменю {subMenuTarget} раздела 'Ипотека'")
    public IpotekeCompletedHousesPage subMenuIpoteke(String subMenuTarget) {

        final String subMenuName = String.format("//a[contains(text(), '%s')]", subMenuTarget);
        for (WebElement element : mainMenu) {
            WebElement subMenu = element.findElement(By.xpath(subMenuName));
            if (subMenu.isDisplayed()) {
                elementToBeClickable(subMenu);
                action.moveToElement(subMenu)
                        .click()
                        .build()
                        .perform();
                return page.getIpotekeCompletedHousesPage();
            }
        }
        Assertions.fail("Подменю с таким именем не найдено");
        return page.getIpotekeCompletedHousesPage();

    }

}
