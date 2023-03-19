package me.AlexDollar.SlopeFieldCalculator;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

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

    public double[] getHValues() {
        return hValues;
    }

    private double[] hValues;

    public Expression getExpression() {
        return expression;
    }

    private Expression expression;


    public CalculatorTab(){
        pane = new JPanel();
        pane.setLayout(new GridLayout(0,2));
        inputLabel = new JLabel("Equation: ");
        input = new JTextField();
        input.addActionListener(new equValidator());
        hLabel = new JLabel("(cs) h: ");
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
                String[] hArray = e.getActionCommand().split(",");
                double[] hs = new double[hArray.length];
                for (int i=0; i < hArray.length; i++) {
                    Double h = Double.parseDouble(hArray[i]);
                    if (h <= 0) h = 0.1;
                    hs[i] = h;
                }
                hValues = hs;
                Main.LOGGER.log(Level.FINE, "Validated h value: " + e.getActionCommand());
                Main.LOGGER.log(Level.INFO, "New h value: " + e.getActionCommand());
            } catch (Exception ex) {
                hInput.setText("0.1");
                hValues = new double[] {0.1};
                Main.LOGGER.log(Level.INFO, "Failed to validate inputted h value.");
            }
            Main.LOGGER.log(Level.INFO, "Set new h Value");
        }
    }

    private class equValidator implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String equ = e.getActionCommand();
            try {
                Expression exp = new ExpressionBuilder(equ)
                        .variables("y", "t")
                        .build();
                expression = exp;
                Main.LOGGER.log(Level.INFO, "New equation: " + equ);

            }
            catch (Exception ex) {
                input.setText("t*y");
                expression = new ExpressionBuilder("t*y")
                        .variables("y", "t")
                        .build();
                Main.LOGGER.log(Level.INFO, "Failed to validate equation");
            }
        }
    }
}

