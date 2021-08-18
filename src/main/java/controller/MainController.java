package controller;

import model.Logger.MyLogger;
import model.pages.Page;

public abstract class MainController {
    protected MyLogger myLogger;
    protected Page page;

    public MainController(Page page) {
        this.page = page;
        this.myLogger = MyLogger.getLogger();
    }

    public Page getPage() {
        return page;
    }
}
