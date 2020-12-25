package ru.appline.ibs.homework.framework.managers;

import ru.appline.ibs.homework.framework.pages.BasePage;
import ru.appline.ibs.homework.framework.pages.IpotekeCompletedHousesPage;
import ru.appline.ibs.homework.framework.pages.StartPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Менеджер для работы со страницами
 */
public class PagesManager {

    private static PagesManager pagesManager;

    private static List<BasePage> pageList = new ArrayList<>();

    StartPage startPage;
    IpotekeCompletedHousesPage ipotekeCompletedHousesPage;

    private PagesManager() {

    }

    public static PagesManager getPagesManager() {

        if (pagesManager == null) {
            pagesManager = new PagesManager();
        }
        return pagesManager;

    }

    public StartPage getStartPage() {

        if (startPage == null) {
            startPage = new StartPage();
            pageList.add(startPage);
        }
        return startPage;

    }

    public IpotekeCompletedHousesPage getIpotekeCompletedHousesPage() {

        if (ipotekeCompletedHousesPage == null) {
            ipotekeCompletedHousesPage = new IpotekeCompletedHousesPage();
            pageList.add(ipotekeCompletedHousesPage);
        }
        return ipotekeCompletedHousesPage;

    }

    static void clearPagesList() {

        for (BasePage page: pageList) {
            page = null;
        }
        pageList.clear();

    }

}
