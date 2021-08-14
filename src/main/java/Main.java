import logic.CommandProcessor;

public class Main {

    public static void main(String[] args) {
        CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.loadManager();
        commandProcessor.run();
    }
}