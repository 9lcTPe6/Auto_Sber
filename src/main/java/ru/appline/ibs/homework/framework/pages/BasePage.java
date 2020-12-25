package ru.appline.ibs.homework.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.ibs.homework.framework.exceptions.InitDriverException;
import ru.appline.ibs.homework.framework.managers.PagesManager;

import static ru.appline.ibs.homework.framework.managers.WebDriverManager.startDriver;

/**
 * Родительский класс для всех последующих страниц
 */
public class BasePage {

    protected PagesManager page = PagesManager.getPagesManager();

    /**
     * Инициализация класса Action для эмуляции работы мыши
     */
    protected Actions action;
    {
        try {
            action = new Actions(startDriver());
        } catch (InitDriverException e) {
            e.printStackTrace();
        }
    }

    /**
     * Инициализация класса JavascriptExecutor для работы со скриптами
     */
    protected JavascriptExecutor js;
    {
        try {
            js = (JavascriptExecutor) startDriver();
        } catch (InitDriverException e) {
            e.printStackTrace();
        }
    }

    /**
     * Переменная для работы с ожиданиями
     */
    protected WebDriverWait wait;
    {
        try {
            wait = new WebDriverWait(startDriver(), 20, 1000);
        } catch (InitDriverException e) {
            e.printStackTrace();
        }
    }

    public BasePage() {

        try {
            PageFactory.initElements(startDriver(), this);
        } catch (InitDriverException e) {
            e.printStackTrace();
        }

    }

    protected void scrollToElement(WebElement element) {

        js.executeScript("arguments[0].scrollIntoView(true);", element);

    }

    protected void scrollDown() {

        js.executeScript("javascript:window.scrollBy(0,1700)");

    }

    protected WebElement elementToBeClickable(WebElement element) {

        return wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    /**
     * Метод для получения заголовка страницы
     * @return String pageName
     */
    public static String getPageName() {
        String pageName = null;
        try {
             pageName = startDriver().getTitle();
        } catch (InitDriverException e) {
            e.printStackTrace();
        }
        return pageName;
    }

}
