package graphic;

import graphic.pages.Swing;

import java.util.Stack;

public class GraphicManager {

    private Stack<Swing> swings;

    public GraphicManager() {
        swings = new Stack<>();
    }

    public GraphicManager(Stack<Swing> swings) {
        this.swings = swings;
    }

    public void addSwing(Swing swing) {
        swings.push(swing);
    }

    public void back() {
        if (!swings.isEmpty()) {
            swings.pop();
        }
        if (!swings.isEmpty()) {
            swings.peek().run();
        }
    }

    public Stack<Swing> getSwings() {
        return swings;
    }

    public void setSwings(Stack<Swing> swings) {
        this.swings = swings;
    }
}
