package graphic;

import logic.Logger.MyLogger;

public abstract class GraphicPages {

    protected final MyLogger myLogger;

    public GraphicPages() {
        myLogger = MyLogger.getLogger();
    }

    public abstract void run();
}
