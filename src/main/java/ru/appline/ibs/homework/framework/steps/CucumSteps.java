package ru.appline.ibs.homework.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.appline.ibs.homework.framework.managers.PagesManager;

import java.util.List;

public class CucumSteps {

    public PagesManager page = PagesManager.getPagesManager();

    @Когда("^Загружена стартовая страница")
    public void запускСтартовойСтраницы() {

        page.getStartPage();

    }

    @Когда("^Переход в главное меню '(.*)'$")
    public void выбратьМеню(String menuTarget) {

        page.getStartPage().selectMainMenu(menuTarget);

    }

    @Когда("^Выбор подменю '(.*)'$")
    public void выбратьПодменю(String subMenuTarget) {

        page.getStartPage().subMenuIpoteke(subMenuTarget);

    }

    @Когда("^Проверить, что открылась страница '(.*)'$")
    public void проверитьСтраницуИпотеки() {

        page.getIpotekeCompletedHousesPage().checkIpotekePage();

    }

    @Тогда("^Заполнить форму поле/значение$")
    public void заполнитьФорму(DataTable dataTable) {

        for (List<String> list: dataTable.cells()) {
            for (String s: list) {
                page.getIpotekeCompletedHousesPage().fillData(s);
            }
        }

    }

    @Тогда("^Убрать чекбокс у поля '(.*)'$")
    public void убратьЧекбокс(String target) {

        page.getIpotekeCompletedHousesPage().unCheck(target);

    }

    @Тогда("^Отметить чекбокс у поля '(.*)'$")
    public void отметитьЧекбокс(String target) {

        page.getIpotekeCompletedHousesPage().unCheck(target);

    }

    @Тогда("^Проверить значения полей$")
    public void проверитьПоля(DataTable dataTable) {

        int count = 0;
        for (List<String> list: dataTable.asLists()) {
            for (String s: list) {
                switch (count) {
                    case 0:
                        page.getIpotekeCompletedHousesPage().checkCreditSum(s);
                        count++;
                        break;
                    case 1:
                        page.getIpotekeCompletedHousesPage().checkRegularPay(s);
                        count++;
                        break;
                    case 2:
                        page.getIpotekeCompletedHousesPage().checkWealthNeeded(s);
                        count++;
                        break;
                    case 3:
                        page.getIpotekeCompletedHousesPage().checkPercentage(s);
                        count=0;
                        break;
                }
            }
        }

    }

}
