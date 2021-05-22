import logic.CommandProcessor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException {
        CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.loadManager();
        commandProcessor.run();
    }
}
