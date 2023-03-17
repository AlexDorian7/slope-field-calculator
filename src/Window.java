import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;

public class Window {
    private JFrame window;
    private JTabbedPane mainPane;


    public Window() {
        window = new JFrame("Slope Field Calculator");
        window.setSize(400,150);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPane = new JTabbedPane();

        window.getContentPane().add(mainPane, BorderLayout.CENTER);
    }

    public void addTab(Tab tab) {
        mainPane.addTab(tab.getName(), tab.getComponent());
    }

    public void show() {
        Main.LOGGER.log(Level.INFO,"Showing main window.");
        window.setVisible(true);
    }

}