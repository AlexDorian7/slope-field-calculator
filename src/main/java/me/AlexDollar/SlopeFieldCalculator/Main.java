package me.AlexDollar.SlopeFieldCalculator;

import java.util.logging.Logger;

public class Main {
    public static final Logger LOGGER = Logger.getLogger("Slope Field");
    public static void main(String[] args) {
        Window window = new Window();
        CalculatorTab calc = new CalculatorTab();
        window.addTab(calc);
        window.show();
    }
}