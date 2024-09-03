package example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class App {

    public JTextField display;
    public double currentNumber = 0;
    public char lastOperation = ' ';

    public App() {
        // Create and set up the window.
        JFrame frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Display where results and numbers are shown
        display = new JTextField();
        display.setEditable(true);  // Allow user to type numbers
        frame.add(display, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3)); // Layout manager for the buttons

        // Buttons
        JButton addButton = new JButton("Add (+)");
        addButton.addActionListener(e -> processOperator('+'));
        JButton subtractButton = new JButton("Subtract (-)");
        subtractButton.addActionListener(e -> processOperator('-'));
        JButton equalsButton = new JButton("Equals (=)");
        equalsButton.addActionListener(e -> processEquals());

        // Adding buttons to panel
        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(equalsButton);

        // Add panel to frame
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Set up key listeners to handle Enter and numerical keys
        display.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    processEquals();
                } else {
                    // Ensure that only numbers or decimal points are entered
                    char keyChar = e.getKeyChar();
                    if (!Character.isDigit(keyChar) && keyChar != '.') {
                        display.setText(display.getText().replaceAll("[^\\d.]", ""));
                    }
                }
            }
        });

        // Display the window.
        frame.setVisible(true);
    }

    // Methods to handle calculations
    public void processOperator(char operator) {
        try {
            // Try to get the number before changing the operator
            currentNumber = Double.parseDouble(display.getText());
            lastOperation = operator;
            display.setText("");
        } catch (NumberFormatException ex) {
            display.setText("Error");
        }
    }

    public void processEquals() {
        try {
            double newNumber = Double.parseDouble(display.getText());
            double result = currentNumber;
            switch (lastOperation) {
                case '+':
                    result += newNumber;
                    break;
                case '-':
                    result -= newNumber;
                    break;
            }
            display.setText(String.valueOf(result));
            currentNumber = result;  // Update current number to result for new operations
        } catch (NumberFormatException ex) {
            display.setText("Error");
        }
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(App::new);
    }
}
