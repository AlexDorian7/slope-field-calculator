
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;


public class CalculatorTab implements Tab {
    private JPanel pane;
    private JLabel inputLabel;
    private JTextField input;
    private JLabel hLabel;
    private JTextField hInput;
    private JButton calculate;
    private JProgressBar progress;


    public CalculatorTab(){
        pane = new JPanel();
        pane.setLayout(new GridLayout(0,2));
        inputLabel = new JLabel("Equation: ");
        input = new JTextField();
        hLabel = new JLabel("h: ");
        hInput = new JTextField();
        hInput.addActionListener(new hValidator());
        hInput.setText("0.1");
        calculate = new JButton("Calculate");
        progress = new JProgressBar(0,100);


        pane.add(inputLabel);
        pane.add(input);
        pane.add(hLabel);
        pane.add(hInput);
        pane.add(calculate);
        pane.add(progress);
    }

    @Override
    public String getName() {
        return "Calculator";
    }

    @Override
    public Component getComponent() {
        return pane;
    }
    private class hValidator implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Double h = Double.parseDouble(e.getActionCommand());
                if (h <= 0) h = 0.1;
                hInput.setText(h.toString());
                Main.LOGGER.log(Level.FINE, "Validated h value: " + h.toString());
            } catch (Exception ex) {
                hInput.setText("0.1");
                Main.LOGGER.log(Level.INFO, "Failed to validate inputted h value.");
            }
        }
    }
}

