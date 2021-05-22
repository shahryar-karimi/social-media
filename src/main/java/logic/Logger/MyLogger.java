package logic.Logger;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class MyLogger {
    private static MyLogger logger;
    private final File file;
    private FileWriter fileWriter;

    private MyLogger() {
        file = new File("log");
    }

    public static MyLogger getLogger() {
        if (logger == null) logger = new MyLogger();
        return logger;
    }

    public FileWriter getFileWriter() {
        if (fileWriter == null) {
            try {
                fileWriter = new FileWriter(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileWriter;
    }

    private String date() {
        return LocalDateTime.now().toString().replace('T', ' ').substring(0, 19);
    }

    private void log(Level level, String nameOfClass, String nameOfMethod ,String message) {
        try {
            getFileWriter().write(date() + " - " +
                    level + " - " +
                    nameOfClass + " - " +
                    nameOfMethod + " - " +
                    message + "\n"
                    );
            getFileWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void debug(String nameOfClass, String nameOfMethod ,String message) {
        log(Level.DEBUG, nameOfClass, nameOfMethod, message);
    }

    public void info(String nameOfClass, String nameOfMethod ,String message) {
        log(Level.INFO, nameOfClass, nameOfMethod, message);
    }

    public void warn(String nameOfClass, String nameOfMethod ,String message) {
        log(Level.WARN, nameOfClass, nameOfMethod, message);
    }

    public void error(String nameOfClass, String nameOfMethod ,String message) {
        log(Level.ERROR, nameOfClass, nameOfMethod, message);
    }

    public void fatal(String nameOfClass, String nameOfMethod ,String message) {
        log(Level.FATAL, nameOfClass, nameOfMethod, message);
    }
}
